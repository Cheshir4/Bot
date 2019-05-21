package com.example.ulyaf.cheshirbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        //Почему final
         final EditText editText = (EditText)findViewById(R.id.editText1);
         TextView textView = (TextView)findViewById(R.id.textView1);

        String phrase = editText.getText().toString();
        textView.setText(phrase);
    }
}
