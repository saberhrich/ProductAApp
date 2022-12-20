package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class ContactDetails extends AppCompatActivity {

    //view
    private TextView libelleTv,codeTv,priceTv,produitTv,addedTimeTv,updatedTimeTv;
    private ImageView profileIv;
    private Button del;

    private String id;
    //database helper
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        //init db
        dbHelper = new DbHelper(this);

        //get data from intent
        Intent intent = getIntent();
        id = intent.getStringExtra("contactId");

        //init view

        libelleTv = findViewById(R.id.LibelleTv);
        codeTv = findViewById(R.id.CodeTv);
        priceTv = findViewById(R.id.PriceTv);
        produitTv=findViewById(R.id.ProduitTv);
        addedTimeTv = findViewById(R.id.addedTimeTv);
        updatedTimeTv = findViewById(R.id.updatedTimeTv);
        del=findViewById(R.id.btnDelete);

        profileIv = findViewById(R.id.profileIv);

        loadDataById();
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void loadDataById() {

        //get data from database
        //query for find data by id
        String selectQuery =  "SELECT * FROM "+Constants.TABLE_NAME + " WHERE " + Constants.C_ID + " =\"" + id + "\"";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                //get data
                String libelle =  ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_LIBELLE));
                String image = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_IMAGE));
                String code = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_CODE));
                String price = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_PRICE));
                String produit=""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_RADIOGROUP));
                String addTime = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_ADDED_TIME));
                String updateTime = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_UPDATED_TIME));

                //convert time to dd/mm/yy hh:mm:aa format
                Calendar calendar = Calendar.getInstance(Locale.getDefault());

                calendar.setTimeInMillis(Long.parseLong(addTime));
                String timeAdd = ""+ DateFormat.format("dd/MM/yy hh:mm:aa",calendar);

                calendar.setTimeInMillis(Long.parseLong(updateTime));
                String timeUpdate = ""+ DateFormat.format("dd/MM/yy hh:mm:aa",calendar);

                //set data
                libelleTv.setText(libelle);
                codeTv.setText(code);
                priceTv.setText(price);
                produitTv.setText(produit);
                addedTimeTv.setText(timeAdd);
                updatedTimeTv.setText(timeUpdate);

                if (image.equals("null")){
                    profileIv.setImageResource(R.drawable.ic_baseline_person_24);
                }else {
                    profileIv.setImageURI(Uri.parse(image));
                }

            }while (cursor.moveToNext());
        }

        db.close();

    }

    //supression de donne



    // run app
    // we have error in profileIv,dbHelper initialization
    // we successfully read our db and show details
}