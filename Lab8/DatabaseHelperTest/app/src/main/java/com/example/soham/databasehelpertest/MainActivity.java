package com.example.soham.databasehelpertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextName;
    private EditText editTextPhone;
    private Button insertButton;
    private Button deleteButton;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText)findViewById(R.id.edit_text_1);
        editTextPhone = (EditText)findViewById(R.id.edit_text_2);
        insertButton = (Button)findViewById(R.id.button_go);
        deleteButton = (Button)findViewById(R.id.delete_button);
        updateButton = (Button)findViewById(R.id.update_button);

        insertButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        switch (id){
            case R.id.button_go:
                DBHelper dbHelper = new DBHelper(this);
                Contact contact = new Contact(name,phone);
                dbHelper.addContact(contact);
                List<Contact> contactsList = dbHelper.getAllContacts();
                Toast.makeText(this, ""+contactsList.size(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_button:
                DBHelper dbHelper1 = new DBHelper(this);
                Toast.makeText(this, "To DO!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_button:
                DBHelper dbHelper2 = new DBHelper(this);
                Contact contact1 = new Contact(name, phone);
                dbHelper2.updateContact(contact1);

        }
    }
}
