package com.example.ulyaf.cheshirbot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.io.Console;

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
                String color2 = "#FFFFFF";
                String color3 = "#000000";

                EditText editText = (EditText)findViewById(R.id.editText1);
                String phrase = editText.getText().toString();
                editText.setText("");
                addPhrase(color1, color2, phrase, true);




                String answer = getAnswer(phrase);






                addPhrase(color2, color3, answer, false);

            }
        });
    }

    private void addPhrase(String colorBack, String colorText, String phrase, boolean who) { // who - true: человек, false: бот
        TextView b = new TextView(getApplicationContext());
        b.setBackgroundColor(Color.parseColor(colorBack));
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
        b.setTextColor(Color.parseColor(colorText));
        b.setTextSize(22);
        b.setTextAlignment(who ? View.TEXT_ALIGNMENT_TEXT_END : View.TEXT_ALIGNMENT_TEXT_START);
        countID++;
    }

    private String getAnswer(String phrase) {
        String answer = "";
        try {

            XmlPullParser parser = getResources().getXml(R.xml.phrases);
            boolean human = false;
            boolean a = false;
            boolean bot = false;
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (parser.getEventType()) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        human = name.equals("human_phrase");
                        bot = a && name.equals("bot_answer");
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        String text = parser.getText();
                        if ((text.equals(phrase)) && (human)) {
                            a = true;
                        }
                        if (bot) {
                            answer = text;
                            a = false;
                            bot = false;
                        }
                        break;
                    default:
                        break;
                }
                parser.next();
            }


        } catch (Throwable t) {
            Toast.makeText(this,
                    "Ошибка при загрузке XML-документа: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
        return answer;
    }
}
