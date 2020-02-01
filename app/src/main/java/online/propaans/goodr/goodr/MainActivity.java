package online.propaans.goodr.goodr;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

import rest.Constants;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView money_Text;
    private int counter=10;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();





        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
     if (user == null) {
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }


       Button change = findViewById(R.id.changelanguage);
       change.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showChangeLanguageDialog();
           }
       });




        LinearLayout about = (LinearLayout) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });

        LinearLayout donate = (LinearLayout) findViewById(R.id.donate);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DonateActivity.class));
            }
        });

     LinearLayout food = (LinearLayout) findViewById(R.id.cause);
        food.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, CauseActivity.class));
            }
        });

        LinearLayout faq = (LinearLayout) findViewById(R.id.faq);
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FAQActivity.class));
            }
        });

        LinearLayout share = (LinearLayout) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this , ReedemActivity.class));
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    protected void onPause() {

        super.onPause();
        saveData();
    }
    private void showChangeLanguageDialog(){
        final String[] listItems = {"हिंदी" , "English" , "ਪੰਜਾਬੀ" , "اردو"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){
                    setLocale("hi");
                    recreate();
                }
                else if (i == 1){
                    setLocale("en");
                    recreate();
                }
                else if ( i == 2){
                    setLocale("pa");
                    recreate();
                }
                else if ( i ==3 ){
                    setLocale("ur");
                    recreate();
                }


                dialogInterface.dismiss();
            }
        });


        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("counter",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counterValue", counter);
        editor.apply();
    }

    public void LoadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("counter",MODE_PRIVATE);
        counter = sharedPreferences.getInt("counterValue",MODE_PRIVATE);
        money_Text.setText(" ₹ " + String.valueOf(counter));
    }

    private void setLocale(String Lang) {
        Locale locale = new Locale(Lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", Lang);
        editor.apply();
    }


    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang" , "");
        setLocale(language);
    }
}
