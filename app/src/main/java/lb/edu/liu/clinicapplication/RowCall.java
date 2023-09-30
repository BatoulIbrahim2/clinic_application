package lb.edu.liu.clinicapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import lb.edu.liu.clinicapplication.databinding.ActivityRowCallBinding;

public class RowCall extends AppCompatActivity {
    TextView tv, tv1, tv2, tv3;
    int Call_Permission_Request_Code = 100;
    private AppBarConfiguration appBarConfiguration;
    private ActivityRowCallBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRowCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        Intent j = getIntent();
        String department = j.getStringExtra("department");
        String name = j.getStringExtra("name");
        String phone_number = j.getStringExtra("phone_number");
        String nbOfRoom = j.getStringExtra("nbOfRoom");

        tv = findViewById(R.id.tvname);
        tv1 = findViewById(R.id.tvdep);
       tv2 = findViewById(R.id.tvnb);
         tv3 = findViewById(R.id.tvroom);

        tv.setText(name);
        tv1.setText(department);
        tv2.setText(phone_number);
        tv3.setText(nbOfRoom);

    }
    public void callHandler(View v){
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED)
        {
            // not granted before, request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    Call_Permission_Request_Code);
        }
        else{
            // granted before, do your action
            call();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == Call_Permission_Request_Code){
            if(grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED){
                call();
            }
        }}
    public void call(){
        String number = tv2.getText().toString();
        number = "tel:" + number;
        Uri u = Uri.parse(number);
        Intent i = new Intent(Intent.ACTION_CALL, u);
        startActivity(i);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement



        if (id == R.id.action_dial){
            // user click on call icon
            String number = tv2.getText().toString();
            Uri u = Uri.parse("tel:" + number);
            Intent i = new Intent(Intent.ACTION_DIAL, u);
            startActivity(i);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

}