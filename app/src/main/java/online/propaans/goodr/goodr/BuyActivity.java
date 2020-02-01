package online.propaans.goodr.goodr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BuyActivity extends AppCompatActivity {

    public static final String CROP_NAME = "pro.artistname";
    public static final String CROP_ID = "pro.artistid";

    public static final String CROP_NUMBER = "9284458773";
    public static final String CROP_ADDRESS = "pro.artistid";

    public static final String CROP_WEIGHT = "pro.artistname";

    public static final String CROP_FOOD = "pro.artistname";

    public static final String CROP_PRICE = "pro.artistname";
    private static final int REQUEST_CALL = 1;

    ListView listViewCrop;

    List<Sell> sells;


    DatabaseReference databaseCrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);



        listViewCrop = (ListView) findViewById(R.id.listViewCrop);
        databaseCrop = FirebaseDatabase.getInstance().getReference("Crop_on_Sale");

        listViewCrop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Sell sell = sells.get(i);


                Intent intent = new Intent(getApplicationContext(), SconfirmActivity.class);


                intent.putExtra(CROP_ID, sell.getSelluserId());
                intent.putExtra(CROP_NAME, sell.getSelluserName());
                intent.putExtra(CROP_ADDRESS, sell.getSelluserAddress());
                intent.putExtra(CROP_NUMBER , sell.getSelluserNumber());
                intent.putExtra(CROP_PRICE , sell.getSellPrice());



                startActivity(intent);
            }
        });


        listViewCrop.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Sell sell = sells.get(i);
                showUpdateDeleteDialog(sell.getSelluserId(), sell.getSelluserName() , sell.getSelluserAddress());
                return true;
            }
        });


        sells = new ArrayList<>();




    }
    private void showUpdateDeleteDialog(final String selluserId, String selluserName , String selluserAddress) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog_sell, null);
        dialogBuilder.setView(dialogView);

        final TextView textView = (TextView) dialogView.findViewById(R.id.sell_TextName);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.ButtonUpdateCrop);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteCrop);

        textView.setText(selluserAddress);

        dialogBuilder.setTitle(selluserName);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = BuyActivity.CROP_NUMBER;
                if (number.trim().length() > 0) {

                    if (ContextCompat.checkSelfPermission(BuyActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(BuyActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                    } else {
                        String dial = "tel:" + number;
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                    }

                } else {
                    Toast.makeText(BuyActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteArtist(selluserId);
                b.dismiss();
            }
        });
    }

    private boolean deleteArtist(String id) {

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Crop_on_Sale").child(id);


        dR.removeValue();

        Toast.makeText(getApplicationContext(), "Thanks For Using GOODr", Toast.LENGTH_LONG).show();

        return true;
    }






    @Override
    protected void onStart() {
        super.onStart();

        databaseCrop.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                sells.clear();


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Sell sell = postSnapshot.getValue(Sell.class);

                    sells.add(sell);
                }

                Selllist selllistAdapter = new Selllist(BuyActivity.this, sells);

                listViewCrop.setAdapter(selllistAdapter);


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}

