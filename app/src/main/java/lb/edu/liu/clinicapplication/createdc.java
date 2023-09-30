package lb.edu.liu.clinicapplication;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import lb.edu.liu.clinicapplication.databinding.ActivityCreatedcBinding;

public class createdc extends AppCompatActivity {
    EditText name,department,phonenumber,nbofroom;
    ProgressBar pb;

    private AppBarConfiguration appBarConfiguration;
    private ActivityCreatedcBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatedcBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.INVISIBLE);
        name=findViewById(R.id.namedc);
        department=findViewById(R.id.department);
        phonenumber=findViewById(R.id.phonenumber);
        nbofroom=findViewById(R.id.numberofroom);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {




        if (item.getItemId() == R.id.save) {
            pb.setVisibility(View.VISIBLE);
            String n = name.getText().toString();
            String d = department.getText().toString();
            String phonumber = phonenumber.getText().toString();
            String nr = nbofroom.getText().toString();


                if(n.equals("") || d.equals("")  || phonumber.equals("")|| nr.equals("")){

                    Toast.makeText(getApplicationContext(), "Empty fields!", Toast.LENGTH_SHORT).show();}
                else{
                    String url1 = "http://10.0.2.2/clinic/add_doctor.php?name=" + n + "&department=" + d + "&phone_number=" + phonumber+ "&nbOfRoom=" + nr;
                    RequestQueue queue = Volley.newRequestQueue(this);
                    StringRequest request = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                            pb.setVisibility(View.INVISIBLE);
                           name.setText("");
                            department.setText("");
                            phonenumber.setText("");
                            nbofroom.setText("");
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

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


