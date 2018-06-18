package com.Notifications.Pada;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blackcj.customkeyboard.R;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Random;

public class KannadaPada extends Activity {


    Cursor c=null;
    Cursor csec=null;

    //to get db length
    int db_length;

    int n_row;
    int c_row;
    TextView message;
    Button feedback;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kannada_pada);

        message = findViewById(R.id.message_pada);
        imgView = findViewById(R.id.imgView);
        feedback = findViewById(R.id.feedback);

        Intent intent = getIntent();
        if(intent!=null){
            String bigText = intent.getExtras().getString("bigText");
            message.setText(bigText);

            String imgUrl = intent.getExtras().getString("imgUrl");
            Picasso.with(this).load(imgUrl).placeholder(R.drawable.wordaday).error(R.drawable.wordaday).into(imgView);
        }




        final MaterialStyledDialog dialogHeader_1 = new MaterialStyledDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .withDialogAnimation(true)
                .setTitle("Awesome!")
                .setDescription("Glad to see you liked Type Kannada! Your 5 Star Rating will help us Serve Better.")
                .setHeaderColor(R.color.orange_primary)
                .setPositive("Give Us a Five", new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        final String appPackageName = getPackageName();
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                    }
                })
                .setNegative("Suggestions", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "ashwinchandlapur@gmail.com"));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Type Kannada Feedback");
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .build();


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogHeader_1.show();
            }
        });


    }
}
