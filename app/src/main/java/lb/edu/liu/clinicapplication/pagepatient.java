package lb.edu.liu.clinicapplication;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;

import org.json.JSONArray;

import lb.edu.liu.clinicapplication.databinding.ActivityPagepatientBinding;

public class pagepatient extends AppCompatActivity {
    ListView list;
    JSONArray data;
    Custom cust;
    Button button;
    private AppBarConfiguration appBarConfiguration;
    private ActivityPagepatientBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPagepatientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list= findViewById(R.id.listpatient);
 button= findViewById(R.id.button4);
        setSupportActionBar(binding.toolbar);


    }
    public void call(View v){
        Intent i = new Intent(pagepatient.this,RowCall.class);
        startActivity(i);
    }
    @Override
    protected void onResume() {
        getdatafromdb();
        super.onResume();
    }
    public void getdatafromdb(){
        String url3 = "http://10.0.2.2/clinic/get_doctor.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url3, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                cust = new Custom(pagepatient.this, response);
                                list.setAdapter(cust);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(pagepatient.this,"Error:"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonArrayRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
}}