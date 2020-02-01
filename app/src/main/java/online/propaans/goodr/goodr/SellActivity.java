package online.propaans.goodr.goodr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellActivity extends AppCompatActivity {

    public static final String ARTIST_NAME = "pro.artistname";
    public static final String ARTIST_ID = "pro.artistid";


    public static final int NOTIFICATION_ID = 888;


    private static final String BIG_TEXT_STYLE = "BIG_TEXT_STYLE";
    private static final String BIG_PICTURE_STYLE = "BIG_PICTURE_STYLE";
    private static final String INBOX_STYLE = "INBOX_STYLE";
    private static final String MESSAGING_STYLE = "MESSAGING_STYLE";


    EditText sellTextName , sellTextNumber, sellTextQuanity, sellTextAddress , sellTextFood , sellTextPrice;
    Button buttonAddCrop;
    ListView listView;


    DatabaseReference databaseCrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);




        databaseCrop = FirebaseDatabase.getInstance().getReference("Crop_on_Sale");


        sellTextName = (EditText) findViewById(R.id.sellTextName);
        sellTextNumber = (EditText) findViewById(R.id.sellTextNumber) ;
        sellTextFood = (EditText) findViewById(R.id.spinnerGenrescrop);
        sellTextPrice = (EditText) findViewById(R.id.spinnerGenrescrop_price);

        sellTextQuanity = (EditText) findViewById(R.id.sellTextQuantity);
        sellTextAddress = (EditText) findViewById(R.id.sellTextAddress);


        buttonAddCrop = (Button) findViewById(R.id.buttonAddCrop);

        buttonAddCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addCrop();
            }
        });



    }

    private void addCrop() {

        String sellname = sellTextName.getText().toString().trim();
        String sellgenre = sellTextFood.getText().toString();
        String sellnumber = sellTextNumber.getText().toString();
        String sellquantity = sellTextQuanity.getText().toString();
        String selladdress = sellTextAddress.getText().toString();
        String sellPrice = sellTextPrice.getText().toString();


        if (sellname.isEmpty()){
            sellTextName.setError("Name is Required");
            sellTextName.requestFocus();
            return;
        }
        if (sellnumber.isEmpty()){
            sellTextNumber.setError("Mobile Number is Required");
            sellTextNumber.requestFocus();
            return;
        }

        if (sellnumber.length()>10){
            sellTextNumber.setError("Enter a Valid Mobile Number");
            sellTextNumber.requestFocus();
            return;
        }

        if (selladdress.isEmpty()){
            sellTextAddress.setError("Address is Required");
            sellTextAddress.requestFocus();
            return;
        }
        if (sellquantity.isEmpty()){
            sellTextQuanity.setError("Weight is  Required");
            sellTextQuanity.requestFocus();
            return;
        }

        if (sellgenre.isEmpty()){
            sellTextFood.setError("Crop Type is Required is Required");
            sellTextFood.requestFocus();
            return;
        }

        if (sellPrice.isEmpty()){
            sellTextPrice.setError("Price is Required is Required");
            sellTextPrice.requestFocus();
            return;
        }





        if (!TextUtils.isEmpty(sellname)) {


            String id = databaseCrop.push().getKey();


            Sell sell = new Sell(id, sellname, sellgenre , sellnumber ,sellquantity , selladdress , sellPrice);


            databaseCrop.child(id).setValue(sell);



            startActivity(new Intent(SellActivity.this , SoldActivity.class));


        } else {
             Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }


}
