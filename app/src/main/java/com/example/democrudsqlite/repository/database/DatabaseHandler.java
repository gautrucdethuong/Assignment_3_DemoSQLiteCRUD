package com.example.democrudsqlite.repository.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.democrudsqlite.model.Customer;

/**
 * Author by HUYNH NHAT MINH (ミン).
 * Email: minhhnce140197@fpt.edu.vn.
 * Date on 6/1/2021.
 * Company: FPT大学.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Set name table
    private static String DATABASE_NAME = "CUSTOMER_MANAGEMENT";

    //Create table Customer
    private static String TABLE_CUSTOMER = "customer";
    private static String ID_CUSTOMER = "id_customer";
    private static String FULL_NAME = "full_name_customer";
    private static String CODE_NUMBER = "code_number_customer";
    private static String LINK_IMAGE = "link_image_customer";
    private static int VERSION = 1;

    // Set ID is unique and auto increment
    private String SQLQuery = "CREATE TABLE "+ TABLE_CUSTOMER +" ( "+ID_CUSTOMER+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +FULL_NAME+" TEXT, "
            +CODE_NUMBER+" INTEGER, "
            +LINK_IMAGE+" TEXT)";
    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Excute query
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Insert Customer
    public void AddCustomer(Customer cs){
        SQLiteDatabase db = this.getWritableDatabase();

        //Data storage through ContentValues
        ContentValues values = new ContentValues();
        values.put(FULL_NAME,cs.getCustomerName());
        values.put(CODE_NUMBER,cs.getCustomerCode());
        values.put(LINK_IMAGE,cs.getCustomerUrl());
        // Inserting Row
        db.insert(TABLE_CUSTOMER,null,values);
        // Closing database connection
        db.close();
    }

    // Query update customer
    public boolean UpdateCustomer(Customer cs, int id){

        SQLiteDatabase db = this.getWritableDatabase();

        //Data storage through ContentValues
        ContentValues values = new ContentValues();

        values.put(FULL_NAME,cs.getCustomerName());
        values.put(CODE_NUMBER,cs.getCustomerCode());
        values.put(LINK_IMAGE,cs.getCustomerUrl());
        // Update Row
        db.update(TABLE_CUSTOMER,values,ID_CUSTOMER+" = "+ id,null);

        return true;
    }

    // Get all list customer
    public Cursor getAllListCustomer(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_CUSTOMER,null);
        return res;
    }

    // Delete customer by id
    public int DeleteCustomer(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_CUSTOMER,ID_CUSTOMER+" = "+i,null);
        return res;
    }

}
