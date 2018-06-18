package com.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.Notifications.Facts.KannadaFact;
import com.Notifications.Pada.KannadaPada;
import com.blackcj.customkeyboard.LatinKeyboardView;
import com.blackcj.customkeyboard.R;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    Button setKeyboard;
    Button setTheme;
    Button keyboard;
    Button kannadanews,kannadafact;
    LatinKeyboardView latinKeyboardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setKeyboard = findViewById(R.id.setKeyboard);
        setKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                j.setClassName("com.android.settings", "com.android.settings.LanguageSettings");
                startActivity(j);
            }
        });


        setTheme = findViewById(R.id.setTheme);
        setTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Theme.class);
                startActivity(intent);
            }
        });

        keyboard = findViewById(R.id.keyboard);
        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mgr =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (mgr != null) {
                    mgr.showInputMethodPicker();
                }
            }
        });

        kannadafact = findViewById(R.id.kannadafact);
        kannadafact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KannadaFact.class);
                startActivity(intent);
            }
        });


        kannadanews = findViewById(R.id.kannadanews);
        kannadanews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=vadeworks.news.duniya"));
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });


    }

}
