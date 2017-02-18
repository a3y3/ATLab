package com.example.student.database;

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
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {

    private String DBNAME = "test.db";
    private String TABLE = "MY_TABLE1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SQLiteDatabase db = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);

        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE+"(email VARCHAR, firstName VARCHAR, lastName VARCHAR);");
        db.execSQL("INSERT INTO " + TABLE + "(email, firstName,lastName) VALUES('xyz.abc1@manipal.edu','xyz','abc1')");
        db.execSQL("INSERT INTO " + TABLE + "(email, firstName,lastName) VALUES('def2.ghi@manipal.edu','def2','ghi')");
        db.execSQL("INSERT INTO " + TABLE + "(email, firstName,lastName) VALUES('aaaa.cccc@manipal.edu','aaaa','cccc')");

        Cursor allRows = db.rawQuery("SELECT * FROM "+ TABLE,null);
        Integer cIndex = allRows.getColumnIndex("email");
        Integer cIndex1 = allRows.getColumnIndex("firstname");
        Toast.makeText(getApplicationContext(),allRows.getCount()+" rows found",Toast.LENGTH_SHORT).show();

        if(allRows.moveToFirst())
        {
            do {
                String email = allRows.getString(0);
                String FNAME = allRows.getString(1);
                Toast.makeText(getApplicationContext(),email+""+FNAME, Toast.LENGTH_SHORT).show();
            }while(allRows.moveToNext());
        }
        db.close();


        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
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
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
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
