package lb.edu.liu.clinicapplication;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Custom extends BaseAdapter {
    Context con1;
    JSONArray data1;
    LayoutInflater inflater;
    public Custom(Context c1, JSONArray data) {
        this.con1 = c1;
        this.data1 = data;
        inflater = (LayoutInflater) c1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public class Holder1 {
        TextView nametv,departmenttv,phonenbtv, roomnbtv;
        ImageView sendImage;

    }
    @Override
    public int getCount() {
        return data1.length();}

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
        Holder1 holder = new Holder1();
        final View rowView1;
        rowView1 = inflater.inflate(R.layout.row1,null);
       holder.nametv = rowView1.findViewById(R.id.namedoctor);
        holder.departmenttv = rowView1.findViewById(R.id.departmentdc);
        holder.phonenbtv = rowView1.findViewById(R.id.nbofph);
        holder.roomnbtv = rowView1.findViewById(R.id.room);
        holder.sendImage = rowView1.findViewById(R.id.imageView3);

        JSONObject obj = data1.optJSONObject(i);
        try {
            holder.nametv.setText(obj.getString("name"));
            holder.departmenttv.setText(obj.getString("department"));
            holder.phonenbtv.setText(obj.getString("phone_number"));
            holder.roomnbtv.setText(obj.getString("nbOfRoom"));
            String id1 = obj.getString("id");
            holder.sendImage.setTag(id1);
            holder.sendImage.setOnClickListener(new View.OnClickListener() {
                  @Override
                public void onClick(View view) {
                   Intent j = new Intent(con1, RowCall.class);
                     try {
                          j.putExtra("name",obj.getString("name"));
                        j.putExtra("department",obj.getString("department"));
                        j.putExtra("phone_number",obj.getString("phone_number"));
                        j.putExtra("nbOfRoom",obj.getString("nbOfRoom"));
                      } catch (JSONException e) {
                         e.printStackTrace();
                      }
                      con1.startActivity(j);
               }

            });

    }catch (JSONException e) {
            e.printStackTrace();
        }
       return rowView1;
    }
}
