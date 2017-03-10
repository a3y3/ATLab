package com.example.student.invoiceman;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String TABLE_PRODUCT = "PRODUCT";
    private String TABLE_PRICE = "PRICE";
    private String DB_NAME = "testdblab64";
    private EditText productName;
    private EditText productRegion;
    private EditText productPrice;
    private RadioGroup radioGroup1;
    private RadioButton radioButton;
    private int flag = 0;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    String pName,pRegion,pAvail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        radioGroup1 = (RadioGroup)findViewById(R.id.radio_group_available);
        radioGroup1.check(R.id.is_available_yes);
        Button goButton = (Button)findViewById(R.id.button_go);
        goButton.setOnClickListener(this);
        Button updateButton = (Button)findViewById(R.id.update_button);
        productName = (EditText)findViewById(R.id.edit_text_1);
        productRegion = (EditText)findViewById(R.id.edit_text_2);

        pName = productName.getText().toString();
        pRegion = productRegion.getText().toString();
        int selectedAvailability = radioGroup1.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(selectedAvailability);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pName = productName.getText().toString();
                pRegion = productRegion.getText().toString();
                sqLiteDatabase.execSQL("UPDATE "+TABLE_PRODUCT+" SET PRODUCT_REGION='"+pRegion+"' WHERE PRODUCT_NAME LIKE '"+pName+"'");
                cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_PRODUCT+" NATURAL JOIN "+TABLE_PRICE,null);
                Toast.makeText(getApplicationContext(),cursor.getCount()+" items", Toast.LENGTH_SHORT).show();
                if(cursor.moveToFirst())
                {
                    do {
                        String name = cursor.getString(1);
                        String region = cursor.getString(2);
                        Toast.makeText(getApplicationContext(),name+" "+region, Toast.LENGTH_SHORT).show();
                    }while(cursor.moveToNext());
                }
            }
        });

        Button deleteButton = (Button)findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                pName = productName.getText().toString();
                sqLiteDatabase.execSQL("DELETE FROM "+TABLE_PRODUCT+" WHERE PRODUCT_NAME LIKE '"+pName+"'");

                cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_PRODUCT+" NATURAL JOIN "+TABLE_PRICE,null);
                Toast.makeText(getApplicationContext(),cursor.getCount()+" items", Toast.LENGTH_SHORT).show();
                if(cursor.moveToFirst())
                {
                    do {
                        String name = cursor.getString(1);
                        String region = cursor.getString(2);
                        Toast.makeText(getApplicationContext(),name+" "+region, Toast.LENGTH_SHORT).show();
                    }while(cursor.moveToNext());
                }
            }
        });



        sqLiteDatabase= openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT + "(PRODUCT_ID INTEGER PRIMARY KEY AUTOINCREMENT, PRODUCT_NAME VARCHAR, REGION VARCHAR, IS_AVAILABLE VARCHAR)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PRICE + "(PRODUCT_ID INTEGER PRIMARY KEY AUTOINCREMENT, PRICE INT)");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View view)
    {
        productName = (EditText)findViewById(R.id.edit_text_1);
        productRegion = (EditText)findViewById(R.id.edit_text_2);
        String pName,pRegion,pAvail;
        pName = productName.getText().toString();
        pRegion = productRegion.getText().toString();
        int selectedAvailability = radioGroup1.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(selectedAvailability);
        if(radioButton.getText().toString().equals("Available"))
        {
            flag = 1;
        }
        else
            flag = 0;
        productPrice = (EditText)findViewById(R.id.edit_text_3);
        String isAvailable;
        if(flag == 1)
        {
            isAvailable = "Yes";
        }
        else
            isAvailable = "No";
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_PRODUCT+"(PRODUCT_NAME,REGION,IS_AVAILABLE) VALUES('"+pName+"','"+pRegion+"','"+isAvailable+"')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_PRICE+"(PRICE) VALUES('"+productPrice+"')");
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_PRODUCT+" NATURAL JOIN "+TABLE_PRICE,null);
        Toast.makeText(getApplicationContext(),cursor.getCount()+" items", Toast.LENGTH_SHORT).show();
        if(cursor.moveToFirst())
        {
            do {
                String name = cursor.getString(1);
                String region = cursor.getString(2);
                Toast.makeText(getApplicationContext(),name+" "+region, Toast.LENGTH_SHORT).show();
            }while(cursor.moveToNext());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
