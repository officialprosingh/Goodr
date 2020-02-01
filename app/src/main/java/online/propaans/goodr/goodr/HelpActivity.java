package online.propaans.goodr.goodr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    private Button help1;
    private Button help2;
    private Button help3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        help1 = (Button)findViewById(R.id.farmerhelp);
        help2 = (Button) findViewById(R.id.buyhelp);
        help3 = (Button) findViewById(R.id.donationhelp);

        help3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HelpActivity.this, FAQActivity.class));
            }
        });

        help1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HelpActivity.this, FarmerActivity.class));
            }
        });

        help2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HelpActivity.this , BuyerActivity.class));
            }
        });
    }
}
