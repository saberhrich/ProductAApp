package com.example.contactapp;

public class Constants {

    // database or db name
    public static final String DATABASE_NAME = "CONTACT_DB";
    //database version
    public static final int DATABASE_VERSION = 1;

    // table name
    public static final String TABLE_NAME = "CONTACT_TABLE";

    // table column or field name
    public static final String C_ID = "ID";
    public static final String C_LIBELLE = "LIBELLE";
    public static final String C_IMAGE = "IMAGE";
    public static final String C_CODE = "CODE";
    public static final String C_PRICE = "PRICE";
    public static final String C_RADIOGROUP = "RADIOGROUP";
    public static final String C_ADDED_TIME = "ADDED_TIME";
    public static final String C_UPDATED_TIME = "UPDATED_TIME";

    // query for create table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_LIBELLE + " TEXT, "
            + C_IMAGE + " TEXT, "
            + C_CODE + " TEXT, "
            + C_PRICE + " TEXT, "
            + C_RADIOGROUP + " TEXT, "
            + C_ADDED_TIME + " TEXT, "
            + C_UPDATED_TIME + " TEXT"
            + " );";


    // Create database helper class for CRUD Query And Database Creation


}
