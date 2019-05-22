package com.example.ulyaf.cheshirbot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private LinearLayout linearLayout;

    private final int USERID = 6000;
    private int countID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button1);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String color1 = "#FF033E";
                TextView b = new TextView(getApplicationContext());
                b.setBackgroundColor(Color.parseColor(color1));
                EditText editText = (EditText)findViewById(R.id.editText1);
                String phrase = editText.getText().toString();
                editText.setText("");
                b.setText(phrase);
                b.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT)
                );
                b.setId(USERID + countID);
                linearLayout.addView(b);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) b.getLayoutParams();
                params.bottomMargin += 10;
                params.leftMargin += 10;
                params.rightMargin += 10;
                countID++;
            }
        });
    }
/*
    public void onButtonClick(View view) {
        //Почему final
        EditText editText = (EditText)findViewById(R.id.editText1);
        TextView textView = (TextView)findViewById(R.id.textView1);

        String phrase = editText.getText().toString();
        textView.setText(phrase);
    }



    }*/
}
