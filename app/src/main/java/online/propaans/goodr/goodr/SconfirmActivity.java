package online.propaans.goodr.goodr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SconfirmActivity extends AppCompatActivity {

    private TextView saddress , sprice ;
    private Button call_farmer;
    private static final int REQUEST_CALL = 1;
    DatabaseReference databaseSold;

    public static final String CROP_NAME = "pro.artistname";
    public static final String CROP_ID = "pro.artistid";

    public static final String CROP_NUMBER = "9284458773";
    public static final String CROP_ADDRESS = "pro.artistid";

    public static final String CROP_WEIGHT = "pro.artistname";

    public static final String CROP_FOOD = "pro.artistname";

    public static final String CROP_PRICE = "pro.artistname";


    ListView listViewCrop;

    List<Sell> sells;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sconfirm);


        Intent intent = getIntent();


        databaseSold = FirebaseDatabase.getInstance().getReference("Crop_on_Sale");


        saddress = (TextView) findViewById(R.id.farmer_address);

        sprice = (TextView) findViewById(R.id.farmer_price);




       call_farmer = (Button) findViewById(R.id.call_farmer);
       call_farmer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               makePhoneCall();
            }
        });





        saddress.setText(intent.getStringExtra(BuyActivity. CROP_ADDRESS));

        sprice.setText(intent.getStringExtra(BuyActivity. CROP_PRICE));



    }



    private void makePhoneCall() {
        String number = BuyActivity.CROP_NUMBER;
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(SconfirmActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SconfirmActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(SconfirmActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


