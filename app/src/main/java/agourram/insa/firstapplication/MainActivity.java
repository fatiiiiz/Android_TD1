package agourram.insa.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    EditText editText;
    String userLogin;
    TextView result;
    TimePicker tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app);

        editText = (EditText) findViewById(R.id.login);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(false);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String log = pref.getString("login", "");
        TextView login = (TextView)findViewById(R.id.login);
        login.setText(log);


        //Msg view button
        Button b = (Button)findViewById(R.id.action);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get text from EditText view
                userLogin = editText.getText().toString();

                //Get time
                tp = (TimePicker) findViewById(R.id.timePicker);
                int hour = tp.getHour();
                int min = tp.getMinute();

                //Display information
                result = (TextView) findViewById(R.id.textView3);
                result.setText(userLogin + " doit faire les courses à " + hour + ":" + min);
            }
        });

        //Quit button
        Button quit = (Button)findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        //Course button
        Button courses = (Button)findViewById(R.id.courses);
        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin = editText.getText().toString();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("login", userLogin);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, ListeActivity.class);
                intent.putExtra("login", userLogin);
                startActivity(intent);
            }
        });

        //Preferences button
        Button preference = (Button)findViewById(R.id.pref);
        preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Preference.class);
                startActivity(intent2);
            }
        });
    }



}