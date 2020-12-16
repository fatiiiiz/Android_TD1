package agourram.insa.firstapplication;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class BackTask extends AsyncTask<Object, Void, JSONObject> {

    ListeActivity activity;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(Object... params)
    {
        activity = (ListeActivity)params[0];
        String str="http://10.0.2.2:8003/courses";
        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;

        try {
            URL url = new URL(str);
            urlConn = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            return new JSONObject(stringBuffer.toString());
        }
        catch(Exception ex) {
            Log.e("App", "yourDataTask", ex);
            return null;
        }
        finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onPostExecute(JSONObject response) {
        if(response != null) {
            Log.i("CIO", "produit: ");
            TextView txt = activity.findViewById(R.id.LoginList);
            JSONArray listprod = null;
            String name = txt.getText().toString();
            try {
                Log.e("App", "Success: " + response.getJSONArray(name));
                listprod = response.getJSONArray(name);
                System.out.println(listprod);
            } catch (JSONException ex) {
                Log.e("App", "Failure", ex);
            }
            List<String> items = new ArrayList<>();
            if(listprod!=null) {
                for (int i = 0; i < listprod.length(); i++) {
                    try {
                        items.add(listprod.getString(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, items);
            ListView list = activity.findViewById(R.id.courses_list);
            list.setAdapter(arrayAdapter);

        }

    }


}
