package com.example.contactapp;


import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

//class for database helper
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table on database
        db.execSQL(Constants.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //upgrade table if any structure change in db

        // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);

        // create table again
        onCreate(db);

    }

    // Insert Function to insert data in database
    public long insertContact(String image, String libelle, String code, String price, String radioGroup, String addedTime,String updatedTime){

        //get writable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValue class object to save data
        ContentValues contentValues = new ContentValues();

        // id will save automatically as we write query
        contentValues.put(Constants.C_IMAGE,image);
        contentValues.put(Constants.C_LIBELLE,libelle);
        contentValues.put(Constants.C_CODE,code);
        contentValues.put(Constants.C_PRICE,price);
        contentValues.put(Constants.C_RADIOGROUP,radioGroup);
        contentValues.put(Constants.C_ADDED_TIME,addedTime);
        contentValues.put(Constants.C_UPDATED_TIME,updatedTime);

        //insert data in row, It will return id of record
        long id = db.insert(Constants.TABLE_NAME,null,contentValues);

        // close db
        db.close();

        //return id
        return id;

    }

    // Update Function to update data in database
    public void updateContact(String id,String image,String libelle,String code,String price,String addedTime,String updatedTime){

        //get writable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValue class object to save data
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.C_IMAGE,image);
        contentValues.put(Constants.C_LIBELLE,libelle);
        contentValues.put(Constants.C_CODE,code);
        contentValues.put(Constants.C_PRICE,price);
        contentValues.put(Constants.C_ADDED_TIME,addedTime);
        contentValues.put(Constants.C_UPDATED_TIME,updatedTime);

        //update data in row, It will return id of record
        db.update(Constants.TABLE_NAME,contentValues,Constants.C_ID+" =? ",new String[]{id} );

        // close db
        db.close();

    }

    // delete data by id
    // public void deleteContact(String id){
        public void deleteContact(String id){
        //get writable database
        SQLiteDatabase db =  getWritableDatabase();

        //delete query
        //return db.delete(DATABASE_TABLE, KEY_NAME + "=?", new String[]{name}) > 0;

      db.delete(Constants.TABLE_NAME,"WHERE"+" =? ",new String[]{id}) ;
      db.close();

        }

    public void delete(String id,String image,String libelle,String code,String price,String addedTime,String updatedTime){
        //get writable database
        SQLiteDatabase db =  this.getWritableDatabase();
         int mytable=db.delete("mytable","id+'"+id+"'",null);
        //delete query
        //return db.delete(DATABASE_TABLE, KEY_NAME + "=?", new String[]{name}) > 0;

        Log.e(TAG, "delete"+mytable);

    }


    // delete all data
    public void deleteAllContact(){
        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //query for delete
        db.execSQL("DELETE FROM "+Constants.TABLE_NAME);
        db.close();
    }


    // get data
    public ArrayList<ModelContact> getAllData(){
        //create arrayList
        ArrayList<ModelContact> arrayList = new ArrayList<>();
        //sql command query
        String selectQuery = "SELECT * FROM "+Constants.TABLE_NAME;

        //get readable db
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all record and add to list
        if (cursor.moveToFirst()){
            do {
                ModelContact modelContact = new ModelContact(
                        // only id is integer type
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_LIBELLE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_CODE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_PRICE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_RADIOGROUP)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_ADDED_TIME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_UPDATED_TIME))
                );
                arrayList.add(modelContact);
            }while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

    // search data in sql Database
    public ArrayList<ModelContact> getSearchContact(String query){

        // it will return arraylist of modelContact class
        ArrayList<ModelContact> contactList = new ArrayList<>();

        // get readable database
        SQLiteDatabase db = getReadableDatabase();

        //query for search
        String queryToSearch = "SELECT * FROM "+Constants.TABLE_NAME+" WHERE "+Constants.C_LIBELLE + " LIKE '%" +query+"%'";

        Cursor cursor = db.rawQuery(queryToSearch,null);

        // looping through all record and add to list
        if (cursor.moveToFirst()){
            do {
                ModelContact modelContact = new ModelContact(
                        // only id is integer type
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_LIBELLE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_CODE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_PRICE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_RADIOGROUP)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_ADDED_TIME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_UPDATED_TIME))
                );
                contactList.add(modelContact);
            }while (cursor.moveToNext());
        }
        db.close();
        return contactList;

    }

}
