package lb.edu.liu.clinicapplication;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;

import lb.edu.liu.clinicapplication.databinding.ActivityPageadminBinding;
import lb.edu.liu.clinicapplication.databinding.ActivityPagepatientBinding;

public class pageadmin extends AppCompatActivity {
    ListView list;
    CustomAdapter cust_adapater;

    private AppBarConfiguration appBarConfiguration;
    private ActivityPageadminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPageadminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = findViewById(R.id.list);
        setSupportActionBar(binding.toolbar);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(pageadmin.this, createdc.class);
                startActivity(i);
            }
        });


    }
    public void lo(View v){
        Intent n = new Intent(pageadmin.this,MainActivity.class);
        startActivity(n);
    }

    @Override
    protected void onResume() {
        getdatafromdb();
        super.onResume();
    }

    public void getdatafromdb(){
        String url2 = "http://10.0.2.2/clinic/get_doctor.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                cust_adapater = new CustomAdapter(pageadmin.this,response);
                                list.setAdapter(cust_adapater);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(pageadmin.this,"Error:"+error.toString(), Toast.LENGTH_SHORT).show();
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
        return super.onOptionsItemSelected(item);}
}