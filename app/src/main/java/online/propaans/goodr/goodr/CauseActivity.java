package online.propaans.goodr.goodr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CauseActivity extends AppCompatActivity {

    public static final String ARTIST_NAME = "pro.artistname";
    public static final String ARTIST_ID = "pro.artistid";


    public static final int NOTIFICATION_ID = 888;


    private static final String BIG_TEXT_STYLE = "BIG_TEXT_STYLE";
    private static final String BIG_PICTURE_STYLE = "BIG_PICTURE_STYLE";
    private static final String INBOX_STYLE = "INBOX_STYLE";
    private static final String MESSAGING_STYLE = "MESSAGING_STYLE";




    EditText editTextName , editTextNumber, editTextQuanity, editTextAddress , editTextFood;
    Button buttonAddArtist;
    private FirebaseAuth mAuth;
    ListView listViewArtists;

    DatabaseReference databaseArtists , datebaseAll , databaseTest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cause);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = mAuth.getCurrentUser();


        databaseArtists = FirebaseDatabase.getInstance().getReference("Causes");
        datebaseAll = FirebaseDatabase.getInstance().getReference("All" );
        databaseTest = FirebaseDatabase.getInstance().getReference("Rewards" );

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber) ;
        editTextFood = (EditText) findViewById(R.id.spinnerGenres);

        editTextQuanity = (EditText) findViewById(R.id.editTextQuantity);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);


        buttonAddArtist = (Button) findViewById(R.id.buttonAddArtist);


        buttonAddArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addArtist();
            }
        });





    }

    private void addArtist() {

        String name = editTextName.getText().toString().trim();
        String genre = editTextFood.getText().toString();
        String number = editTextNumber.getText().toString();
        String quantity = editTextQuanity.getText().toString();
        String address = editTextAddress.getText().toString();
        final String money = "20";

        if (name.isEmpty()){
            editTextName.setError("Name is Required");
            editTextName.requestFocus();
            return;
        }
        if (number.isEmpty()){
            editTextNumber.setError("Mobile Number is Required");
            editTextNumber.requestFocus();
            return;
        }

        if (number.length()>10){
            editTextNumber.setError("Enter a Valid Mobile Number");
            editTextNumber.requestFocus();
            return;
        }

        if (address.isEmpty()){
            editTextAddress.setError("Address is Required");
            editTextAddress.requestFocus();
            return;
        }
        if (quantity.isEmpty()){
            editTextQuanity.setError("Weight is  Required");
            editTextQuanity.requestFocus();
            return;
        }

        if (genre.isEmpty()){
            editTextFood.setError("Food Type is Required is Required");
            editTextFood.requestFocus();
            return;
        }



        if (!TextUtils.isEmpty(name)) {
            String id = databaseArtists.push().getKey();
            String userId = mAuth.getUid();



            Artist artist = new Artist(id, name, genre , number ,quantity , address, money );




            databaseArtists.child(id).setValue(artist);
            datebaseAll.child(id).setValue(artist);
            String uid = mAuth.getUid();
            databaseTest.child(userId).setValue(money);

            startActivity(new Intent(CauseActivity.this , ThanksActivity.class));


        } else {

            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

}

