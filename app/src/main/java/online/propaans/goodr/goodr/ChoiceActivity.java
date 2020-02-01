package online.propaans.goodr.goodr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ChoiceActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button donator , ngo ;
    String USER_TYPE_1 = "donator";
    String USER_TYPE_2 = "ngo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        donator = (Button) findViewById(R.id.donator);
        ngo = (Button) findViewById(R.id.ngo);

        String user = getResources().getString(R.string.usertype);
        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Reg", 0);


        Intent intent = null;
        if(user.equals(USER_TYPE_1)){
            intent = new Intent(this, MainActivity.class);
        }else if(user.equals(USER_TYPE_2)){
            intent = new Intent(this, MainngoActivity.class);
        }

        if(intent!=null){
            startActivity(intent);
        }

        donator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoiceActivity.this, LoginActivity.class));
            }
        });

        ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoiceActivity.this , NgoActivity.class));
            }
        });
    }


}
