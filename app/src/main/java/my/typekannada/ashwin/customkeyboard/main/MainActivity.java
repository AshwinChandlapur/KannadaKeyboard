package my.typekannada.ashwin.customkeyboard.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import org.w3c.dom.Text;


import my.typekannada.ashwin.customkeyboard.Notifications.Facts.KannadaFact;
import my.typekannada.ashwin.customkeyboard.R;
import my.typekannada.ashwin.customkeyboard.blackcj.customkeyboard.LatinKeyboardView;

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

        TextView privacyPolicy = findViewById(R.id.privacyPolicy);
        privacyPolicy.setPaintFlags(privacyPolicy.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("http://ashwinchandlapur.github.io/SVGName/privacy_policy_TypeKannada.html"));
                startActivity(viewIntent);
            }
        });

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


    }

}
