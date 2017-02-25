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
    private String DB_NAME = "testdblab61";
    private EditText productName;
    private EditText productRegion;
    private EditText productPrice;
    private RadioGroup radioGroup1;
    private RadioButton radioButton;
    private int flag = 0;
    SQLiteDatabase sqLiteDatabase;
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


        sqLiteDatabase= openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        //sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS DOGGIE(NAME VARCHAR)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT + "(PRODUCT_ID INT PRIMARY KEY AUTOINCREMENT, PRODUCT_NAME VARCHAR, REGION VARCHAR, IS_AVAILABLE VARCHAR)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PRICE + "(PRODUCT_ID INT PRIMARY KEY AUTOINCREMENT, PRICE INT)");
        /*sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCT + "VALUES 12, Toothbrush, Nashik, yes");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCT + "VALUES 13, Fiber Optic Cable, India, no");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCT + "VALUES 14, Gaming Mouse, USA, yes");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCT + "VALUES 15, 16GB RAM, India, yes");


        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRICE + "VALUES 12,5000");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRICE + "VALUES 13,40000" );
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRICE + "VALUES 14, 2000");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRICE + "VALUES 15, 6000"); //Discount.*/




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
       // sqLiteDatabase.execSQL("INSERT INTO "+TABLE_PRODUCT+"(PRODUCT_NAME,REGION,IS_AVAILABLE) VALUES('"+productPrice+"','"+productRegion+"','"+isAvailable+"')");

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_PRODUCT+" NATURAL JOIN "+TABLE_PRICE,null);
        Toast.makeText(getApplicationContext(),cursor.getCount(), Toast.LENGTH_SHORT).show();
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
