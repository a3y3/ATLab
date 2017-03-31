package com.example.soham.allaboardtheendsemtrain;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{
    private SQLiteDatabase sqLiteDatabase;
    private EditText nameEditText;
    private EditText numberEditText;
    private Cursor cursor;
    String dataList[];
    ArrayAdapter<String> adapter;
    ListView listView;

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

        listView = (ListView)findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(WelcomeActivity.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure to delete this entry?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });
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
                sqLiteDatabase.execSQL("INSERT INTO DATA(NAME, NUMBER) VALUES('"+name+"','"+ number+"')");
                cursor = sqLiteDatabase.rawQuery("SELECT * FROM DATA", null);
                dataList= new String[cursor.getCount()];
                cursor.moveToFirst();
                for(int i =0; i < cursor.getCount();i++){
                    dataList[i] = cursor.getString(1);
                    cursor.moveToNext();
                }
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1, dataList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
        }
    }
}
