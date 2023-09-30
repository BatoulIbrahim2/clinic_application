package lb.edu.liu.clinicapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import lb.edu.liu.clinicapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText u1,p1;
    Button li;
    CheckBox c;
    SharedPreferences sp;
    String u2="Admin";
    String p2="Admin";

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        u1=findViewById(R.id.usernameEd);
        p1=findViewById(R.id.passwordEd);
        li=findViewById(R.id.login);
        c=findViewById(R.id.c);
        sp=getSharedPreferences("login",MODE_PRIVATE);




    }
    public void l(View view){
        String usname1 = u1.getText().toString();
        String pass1 = p1.getText().toString();
        if(usname1.equals(u2)  && pass1.equals(p2)){

            Intent s = new Intent(MainActivity.this,pageadmin.class);
            startActivity(s);
        }
        else {




            String url = "http://10.0.2.2/clinic/get_patient.php?username="+ usname1 ;
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {



                    if ( response.equals(pass1)) {
                        if (c.isChecked()) {
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("username", usname1);
                            editor.putString("password", pass1);
                            editor.putBoolean("rememberMe",true);
                            editor.commit();

                        }
                        else{
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("username", "");
                            editor.putString("password", "");
                            editor.putBoolean("rememberMe",false);
                            editor.commit();
                        }
                        Intent i = new Intent(MainActivity.this, patient.class);
                        startActivity(i);
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //  Toast.makeText(this,"error",);

                        }
                    });
            requestQueue.add(stringRequest);


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_register) {
            Intent i = new Intent(this,newReg.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}