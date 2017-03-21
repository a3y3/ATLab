package com.example.soham.databasehelpertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Soham on 21-Mar-17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "test-db2";
    private static final String TABLE_NAME = "contacts";
    //Column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone_number";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +"("
                                        +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_NAME+" TEXT,"
                                        +KEY_PHONE+" TEXT"+")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addContact(Contact contact)
    {
        Log.e("007","name is"+contact.get_name());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();      //Objects of ContentValues can stores any value in a key-value pair
        contentValues.put(KEY_NAME, contact.get_name());
        contentValues.put(KEY_PHONE, contact.get_phone_number());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public Contact getContact(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{KEY_ID,KEY_NAME,KEY_PHONE},KEY_ID + "=?", new String[]{ String.valueOf(id)},null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        return contact;
    }

    public int updateContact(Contact contact){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, contact.get_name());
        contentValues.put(KEY_PHONE, contact.get_phone_number());

        return  sqLiteDatabase.update(TABLE_NAME, contentValues, KEY_ID+" = ?", new String[]{String.valueOf(contact.get_id())} );
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
        db.close();
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_name(cursor.getString(1));
                contact.set_phone_number(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

}
