package me.qingy.sharetally.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by qing on 10/22/14.
 */

@DatabaseTable
public class Person {

    public static final String FIELD_ID = "id";

    @DatabaseField(generatedId = true, columnName = FIELD_ID)
    private int id;

    @DatabaseField
    private String objId;

    @DatabaseField
    private Date updateAt;

    @DatabaseField
    private String name;

    @DatabaseField
    private String email;

    @DatabaseField
    private boolean removed;

    public Person() {
        /* for ORMLite */
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String  getEmail() {
        return email;
    }

    public void remove() {
        removed = true;
    }
}
