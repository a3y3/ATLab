package com.example.student.quizzy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity
{
    private RadioGroup rg1;
    private RadioGroup rg2;
    private RadioGroup rg3;

    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;
    private RadioButton r4;
    private RadioButton r5;
    private RadioButton r6;
    private RadioButton r7;
    private RadioButton r8;
    private RadioButton r9;
    private int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg1 = (RadioGroup)findViewById(R.id.radioGroup1);
        rg2 = (RadioGroup)findViewById(R.id.radioGroup2);
        rg3 = (RadioGroup)findViewById(R.id.radioGroup3);

        Button submit = (Button)findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int selectedID1 = rg1.getCheckedRadioButtonId();
                r1 = (RadioButton)findViewById(selectedID1);
                if(r1.getText().toString().equals("Dog"))
                {
                    count++;
                }
                int selectedID2 = rg2.getCheckedRadioButtonId();
                r2 = (RadioButton)findViewById(selectedID2);
                if(r2.getText().toString().equals("Vanessa"))
                {
                    count++;
                }
                int selectedID3 = rg3.getCheckedRadioButtonId();
                r3 = (RadioButton)findViewById(selectedID3);
                if(r3.getText().toString().equals("Both"))
                {
                    count++;
                }
                //Toast.makeText(getApplicationContext(),"Your score is"+count,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                intent.putExtra("Answer",count);
                startActivity(intent);
            }
        });


    }
}
