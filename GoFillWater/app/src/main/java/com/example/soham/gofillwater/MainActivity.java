package com.example.soham.gofillwater;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText password1EditText;
    private EditText password2EditText;
    private TextView turnOf;
    private Button goButton;
    private String Soham = "Soham";
    private String Aneesh = "Aneesh";
    private String currentName = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        goButton = (Button)findViewById(R.id.go_button);
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        BufferedReader in;
        try {
            turnOf = (TextView) findViewById(R.id.whose_turn);
            fileInputStream = getApplicationContext().openFileInput("name");
            in = new BufferedReader(new InputStreamReader(fileInputStream));
            String value = in.readLine();
            turnOf.setText(value);
            Log.e("Test","in.readLine in onCreate equals"+value);
            if(value.equals("Soham")||value.equals("Aneesh")){}
            else{
                fileOutputStream = openFileOutput("name", Context.MODE_PRIVATE);
                fileOutputStream.write(Soham.getBytes());
                fileOutputStream.close();
            }
            fileInputStream = getApplicationContext().openFileInput("name");
            in = new BufferedReader(new InputStreamReader(fileInputStream));
            Log.e("Test","in.readLine in onCreate equals"+in.readLine());
            fileInputStream.close();
            goButton.setOnClickListener(this);
        }
        catch(Exception e)
        {
            Log.e("test","File error, damn");
        }
    }

    @Override
    public void onClick(View view)
    {
        password1EditText = (EditText)findViewById(R.id.aneesh_password);
        password2EditText = (EditText)findViewById(R.id.soham_password);
        String password1 = password1EditText.getText().toString();
        String password2 = password2EditText.getText().toString();
        if(password1.equals("apple") && password2.equals("banana")) {
            password1EditText.setText("");
            password2EditText.setText("");

            try {
                FileInputStream fileInputStream = openFileInput("name");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String value = bufferedReader.readLine();
                Log.e("Test", "Text read was" + value);
                if (value.equals("Soham")) {
                    FileOutputStream fileOutputStream = openFileOutput("name", Context.MODE_PRIVATE);
                    fileOutputStream.write(Aneesh.getBytes());
                    fileOutputStream.close();
                    currentName = "Aneesh";
                }
                else {
                    FileOutputStream fileOutputStream = openFileOutput("name", Context.MODE_PRIVATE);
                    fileOutputStream.write(Soham.getBytes());
                    fileOutputStream.close();
                    currentName = "Soham";
                }
                Log.e("Test", "Text read was" + value);
                fileInputStream.close();
            } catch (Exception e) {
                Log.e("test", "The error is" + e.toString());
            }
            turnOf = (TextView) findViewById(R.id.whose_turn);
            turnOf.setText(currentName);
        }
        else {
            turnOf = (TextView) findViewById(R.id.whose_turn);
            turnOf.setText("Chutiya spotted");
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
