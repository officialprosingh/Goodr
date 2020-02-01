package online.propaans.goodr.goodr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoldActivity extends AppCompatActivity {

    private Button msold;
    private Button soldshare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold);


        msold = (Button) findViewById(R.id.sold_back);
        msold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SoldActivity.this, MainActivity.class));
            }
        });

        soldshare = (Button) findViewById(R.id.sold_share);
        soldshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = " " +
                        "Goodr Just Helped me to Sell My Crop within Secs,Do the Same !! Click on the Link Below" ;
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent, "Share Using"));
            }
        });
    }
}
