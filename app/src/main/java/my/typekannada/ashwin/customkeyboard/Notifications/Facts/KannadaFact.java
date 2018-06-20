package my.typekannada.ashwin.customkeyboard.Notifications.Facts;

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
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Random;

import my.typekannada.ashwin.customkeyboard.R;

public class KannadaFact extends AppCompatActivity {


    Cursor c=null;
    Cursor csec=null;

    int db_length;
    int n_row;
    int c_row;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kannada_fact);

        android.support.v7.app.ActionBar AB = getSupportActionBar();
        AB.hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        message = (TextView)findViewById(R.id.message_fact);
        //create a DBhelper instance to get cursor
        DatabaseHelper_Fact myDbHelper = new DatabaseHelper_Fact(KannadaFact.this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        //put cursor in allfacts table of facts db
        c = myDbHelper.query("allfacts", null, null, null, null, null, null);
        csec = myDbHelper.query("allfacts", null, null, null, null, null, null);


        //gettotal no of rows in table
        db_length=c.getCount();

        c_row=randnum();
        n_row=randnum();
        next();


        Button feedback=(Button)findViewById(R.id.feedback) ;
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

    public  int randnum()
    {
        Random r = new Random();
        int fact_no = r.nextInt(db_length+1);
        if(fact_no==260){return fact_no-1;}

        return fact_no;
    }




    private void next() {
        c.moveToPosition(c_row);
        csec.moveToPosition(n_row);
        String factString = c.getString(2);
        String imgString = c.getString(3);
        Log.i("Message",factString);
        message.setText(factString);

        ImageView imgView=(ImageView)findViewById(R.id.fact_imgview);


        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;

            Picasso.with(this.getApplicationContext())
                    .load(imgString)
                    .placeholder(R.color.dark_primary)
                    .into(imgView);

            Log.i("ImageURL is",imgString);
        }
        else{
            connected = false;
            Picasso.with(this.getApplicationContext())
                    .load(imgString)
                    .placeholder(R.color.dark_primary_dark)
                    .into(imgView);
        }
    }
}
