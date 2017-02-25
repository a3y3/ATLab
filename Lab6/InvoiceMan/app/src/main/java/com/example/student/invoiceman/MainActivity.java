package com.example.student.invoiceman;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private String TABLE_PRODUCT = "PRODUCT";
    private String TABLE_PRICE = "PRICE";
    private String DB_NAME = "INVOICE_DB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT + "(PRODUCT_ID INT, PRODUCT_NAME VARCHAR, REGION VARCHAR, IS_AVAILABLE VARCHAR)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCT + "VALUES 12, Toothbrush, Nashik, yes");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCT + "VALUES 13, Fiber Optic Cable, India, no");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCT + "VALUES 14, Gaming Mouse, USA, yes");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCT + "VALUES 15, 16GB RAM, India, yes");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PRICE + "PRODUCT_ID INT, PRICE INT");
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
