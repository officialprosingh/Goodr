package online.propaans.goodr.goodr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CheckActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;

    DatabaseReference databaseCause;
    ListView listViewCause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        listViewCause = (ListView) findViewById(R.id.listViewCauses);
        databaseCause = FirebaseDatabase.getInstance().getReference("Cause");


    }
}
