package agourram.insa.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Intent intent = getIntent();
        if (intent != null) {
            String article ="";
            if (intent.hasExtra("item")){
                article = intent.getStringExtra("item");
                TextView textView = (TextView)findViewById(R.id.article);
                textView.setText(article);
            }
        }

        //Validate button
        Button accept = (Button) findViewById(R.id.valider);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHandler db = new DataBaseHandler(BuyActivity.this);
                String article = intent.getStringExtra("item");
                db.insertData(article);
                db.readData();
                finish();
            }
        });

        //Reject button
        Button rejeter = (Button) findViewById(R.id.rejeter);
        rejeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}