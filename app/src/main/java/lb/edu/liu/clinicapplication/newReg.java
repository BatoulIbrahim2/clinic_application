package lb.edu.liu.clinicapplication;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import lb.edu.liu.clinicapplication.databinding.ActivityNewRegBinding;

public class newReg extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityNewRegBinding binding;
    EditText username,password,phonenb;
    RadioButton female,male;
    ProgressBar pb;
    String gender="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewRegBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        setSupportActionBar(binding.toolbar);
        username=findViewById(R.id.namedc);
        password=findViewById(R.id.department);
        phonenb=findViewById(R.id.phonenumber);
        female=findViewById(R.id.female);
        male=findViewById(R.id.male);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save){

                    pb.setVisibility(View.VISIBLE);
            String name = username.getText().toString();
            String pass = password.getText().toString();
            String phone = phonenb.getText().toString();

            if(name.equals("") || pass.equals("") || phone.equals("")){

                Toast.makeText(getApplicationContext(), "Empty fields!", Toast.LENGTH_SHORT).show();
            }
            else {

                    if(female.isChecked()){
                        gender = "F";
                    }else {  gender = "M";

                    }


                String url = "http://10.0.2.2/clinic/add_patient.php?username=" + name + "&password=" + pass + "&phone_number=" + phone +"&gender="+gender;
                RequestQueue queue =
                        Volley.newRequestQueue(this);
                StringRequest request = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                pb.setVisibility(View.INVISIBLE);
                               username.setText("");
                                password.setText("");
                                phonenb.setText("");
                                
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void
                            onErrorResponse(VolleyError error) {

                                Toast.makeText(getApplicationContext(), "Error:" + error.toString(), Toast.LENGTH_SHORT).show();

                                pb.setVisibility(View.INVISIBLE);
                            }
                        }
                );
                queue.add(request);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}




