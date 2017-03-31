package com.example.soham.allaboardtheendsemtrain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{
    private SQLiteDatabase sqLiteDatabase;
    private EditText nameEditText;
    private EditText numberEditText;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sqLiteDatabase = openOrCreateDatabase("test.db",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS DATA(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, NUMBER TEXT)");
        nameEditText = (EditText)findViewById(R.id.nameEditText);
        numberEditText = (EditText)findViewById(R.id.numberEditText);

        Button insertButton = (Button)findViewById(R.id.insertButton);
        insertButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.insertButton:
                String name = nameEditText.getText().toString();
                String number = numberEditText.getText().toString();
                sqLiteDatabase.execSQL("INSERT INTO DATA(NAME, NUMBER) VALUES '"+name+"', '"+ number+"'");
                cursor = sqLiteDatabase.rawQuery("SELECT * FROM DATA", null);
                Toast.makeText(this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Okay", Toast.LENGTH_SHORT).show();

        }
    }

}
