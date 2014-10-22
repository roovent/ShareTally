package me.qingy.sharetally;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

import me.qingy.sharetally.model.Person;


public class FriendEditActivity extends Activity {
    private Person mPerson;
    private EditText mEtName;
    private EditText mEtEmail;
    private Mode mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_edit);

        mEtName = (EditText) findViewById(R.id.person_name);
        mEtEmail = (EditText) findViewById(R.id.person_email);

        mPerson = ObjectHolder.getPerson();
        if (mPerson == null) { /* Create */
            mMode = Mode.CREATE;
            getActionBar().setTitle(getResources().getString(R.string.title_add_friend));
            mPerson = new Person();
        } else { /* Edit */
            mMode = Mode.EDIT;
            getActionBar().setTitle(getResources().getString(R.string.edit));
            mEtName.setText(mPerson.getName());
            mEtEmail.setText(mPerson.getEmail());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.friend_edit, menu);
        if (mMode == Mode.CREATE) {
            menu.findItem(R.id.action_delete).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                if (StringUtils.isEmpty(mEtName.getText().toString())) {
                    return true;
                }
                mPerson.setName(mEtName.getText().toString());
                mPerson.setEmail(mEtEmail.getText().toString());
                mPerson.submit();
                onBackPressed();
                break;
            case R.id.action_delete:
                new ConfirmationDialog().setArguments(getText(R.string.warning_delete_friend), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPerson.remove();
                        ;
                        mPerson.submit();
                        onBackPressed();
                    }
                }).show(getFragmentManager(), null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private enum Mode {
        CREATE, EDIT
    }
}
