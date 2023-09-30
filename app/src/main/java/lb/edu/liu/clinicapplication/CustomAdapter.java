package lb.edu.liu.clinicapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomAdapter  extends BaseAdapter {
    Context con;
    JSONArray data;
    LayoutInflater inflater;
    public CustomAdapter(Context c, JSONArray data) {
        this.con = c;
        this.data = data;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public class Holder {
        TextView nametxtv,departmenttxtv,phonenbtxtv, roomnbtxtv;
        ImageView deleteImage;

    }
        @Override
    public int getCount() {
            return data.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.row,null);
//initialize the fields in the listview row
        holder.nametxtv = rowView.findViewById(R.id.name);
        holder.departmenttxtv = rowView.findViewById(R.id.department);
        holder.phonenbtxtv = rowView.findViewById(R.id.phonenb);
        holder.roomnbtxtv = rowView.findViewById(R.id.roomnb);
        holder.deleteImage = rowView.findViewById(R.id.imageView2);

        JSONObject obj = data.optJSONObject(i);
        try {
            holder.nametxtv.setText(obj.getString("name"));
            holder.departmenttxtv.setText(obj.getString("department"));
            holder.phonenbtxtv.setText(obj.getString("phone_number"));
            holder.roomnbtxtv.setText(obj.getString("nbOfRoom"));
            String id = obj.getString("id");
            holder.deleteImage.setTag(id);

            holder.deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url =
                            "http://10.0.2.2/clinic/delete_doctor.php?id="+ holder.deleteImage.getTag().toString();
                    RequestQueue queue = Volley.newRequestQueue(con);
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.equals("success")){
                                        ((pageadmin)con).onResume();
                                    }
                                    else {
                                        Toast.makeText(con, "Delete failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void
                                onErrorResponse(VolleyError error) {
                                    Toast.makeText(con,
                                            "Error:"+error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    queue.add(request);
                }
            });



        } catch (JSONException e) {

        }


        return rowView;
    }}
