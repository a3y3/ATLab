package com.example.student.quizzy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent intent = getIntent();
        int result = intent.getIntExtra("Answer",999);
        Toast.makeText(getApplicationContext(),"Your score HERE is"+result, Toast.LENGTH_LONG).show();
    }
}
