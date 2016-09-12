package com.example.red_six.disasterapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 11/09/2016.
 */

public class SQLLiteDBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME ="AdressInfo";
    // Contacts table name
    private static final String TABLE_NAME ="Adresses";
    // Addreess Table Columns names
    private static final String KEY_ID ="id";
    private static final String KEY_NAME ="name";
    private static final String KEY_STREET_NAME="streetAddress";
    private static final String KEY_CITY_NAME="city";
    private static final String KEY_REGION_NAME="region";

    public SQLLiteDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
        + KEY_STREET_NAME + " TEXT" + KEY_CITY_NAME + " TEXT" + KEY_REGION_NAME + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Creating tables again
        onCreate(db);
    }

    /** add a new address */
    public void addAddress(Address address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, address.getName());//addess name
        values.put(KEY_STREET_NAME, address.getAddress());//address st address
        values.put(KEY_CITY_NAME, address.getCity());//address city;
        values.put(KEY_REGION_NAME, address.getRegion());//address region
        /*INSERT ROW*/
        db.insert(TABLE_NAME, null, values);
        db.close();//Close connection
    }

    /**get an address via ID number*/
    public Address getAddressID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        //get address
        Cursor cursor = db.query(TABLE_NAME, new String [] {
             KEY_ID, KEY_NAME, KEY_STREET_NAME, KEY_CITY_NAME, KEY_REGION_NAME},KEY_ID+"=?",
             new String[] {String.valueOf(id)},null,null,null,null);
            if (cursor!=null) {
                cursor.moveToFirst();
            }
        //save details into an address object and return it
            Address address = new Address(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return address;
    }

    /**Get all of the addresses**/
    public List<Address> getAllAddresses() {
        List<Address> addressList = new ArrayList<Address>();
        //select query
        String selectQuery = "SELECT*FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //loop through rows, get data and add to list
        if(cursor.moveToFirst()) {
            do {
                //get the data
                Address address = new Address();
                address.setId(Integer.parseInt(cursor.getString(0)));
                address.setName(cursor.getString(1));
                address.setAddress(cursor.getString(2));
                address.setCity(cursor.getString(3));
                address.setRegion(cursor.getString(4));
                //add to list
                addressList.add(address);
            }
            while (cursor.moveToNext());
        }
        return addressList;
    }
    //update an address
    public int updateAddress(Address address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, address.getName());
        values.put(KEY_STREET_NAME, address.getAddress());
        values.put(KEY_CITY_NAME, address.getCity());
        values.put(KEY_REGION_NAME, address.getRegion());
        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
        new String[]{String.valueOf(address.getId())});
    }

    // Deleting an address
    public void deleteAddress(Address address) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
        new String[] { String.valueOf(address.getId()) });
        db.close();
    }
}
