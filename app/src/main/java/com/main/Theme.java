package com.main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.blackcj.customkeyboard.R;

public class Theme extends Activity {


    RadioGroup radioGroup;
    SharedPreferences theme;
    SharedPreferences.Editor edit;
    String selectedTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        radioGroup = (RadioGroup) findViewById(R.id.radio);

        theme=getSharedPreferences("app", Context.MODE_PRIVATE);
        selectedTheme = theme.getString("theme","blue");
        switch (selectedTheme){
            case "dark":
                radioGroup.check(R.id.dark);
                break;
            case "blue":
                radioGroup.check(R.id.blue);
                break;
            case "green":
                radioGroup.check(R.id.green);
                break;
            case "orange":
                radioGroup.check(R.id.orange);
                break;
            case "red":
                radioGroup.check(R.id.red);
                break;
            case "cyan":
                radioGroup.check(R.id.cyan);
                break;
            case "brown":
                radioGroup.check(R.id.brown);
                break;
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.dark:
                        edit=theme.edit();
                        edit.putString("theme","dark");
                        edit.commit();
                        break;
                    case R.id.blue:
                        edit=theme.edit();
                        edit.putString("theme","blue");
                        edit.commit();
                        break;
                    case R.id.green:
                        edit=theme.edit();
                        edit.putString("theme","green");
                        edit.commit();
                        break;
                    case R.id.orange:
                        edit=theme.edit();
                        edit.putString("theme","orange");
                        edit.commit();
                        break;
                    case R.id.red:
                        edit=theme.edit();
                        edit.putString("theme","red");
                        edit.commit();
                        break;
                    case R.id.brown:
                        edit=theme.edit();
                        edit.putString("theme","brown");
                        edit.commit();
                        break;
                    case R.id.cyan:
                        edit=theme.edit();
                        edit.putString("theme","cyan");
                        edit.commit();
                        break;
                }
            }
        });
    }
}
