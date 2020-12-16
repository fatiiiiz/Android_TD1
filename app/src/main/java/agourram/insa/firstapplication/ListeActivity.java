package agourram.insa.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        Intent intent = getIntent();
        if (intent != null) {
            String log ="";
            if (intent.hasExtra("login")){
                log = intent.getStringExtra("login");
                TextView textView = (TextView)findViewById(R.id.LoginList);
                textView.setText(log);
                new BackTask().execute(this);
            }
        }

        //BackTask backtask=new BackTask(this);
        //backtask.execute();


        ListView courseList = (ListView)findViewById(R.id.courses_list);
        //String[] array = new String[] {"Pain", "Fromage", "Coca", "Beurre"};
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                //android.R.layout.simple_list_item_1,
                //array);

        //courseList.setAdapter(arrayAdapter);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) courseList.getItemAtPosition(position);
                Intent intent = new Intent(ListeActivity.this, BuyActivity.class);
                intent.putExtra("item", item);
                startActivity(intent);
            }
        });


    }
}