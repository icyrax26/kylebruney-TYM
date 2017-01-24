package com.example.tym;

import java.util.ArrayList;
import java.util.HashMap;
 
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
 
public class ProgListViewAdapter extends BaseAdapter {
 
    // Declare Variables
    Context context1;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
 
    public ProgListViewAdapter(Context context,
        ArrayList<HashMap<String, String>> arraylist) {
        this.context1 = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
 
    }
 
    @Override
    public int getCount() {
        return data.size();
    }
 
    @Override
    public Object getItem(int position) {
        return null;
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView txttit;
 //       TextView txtcontnt;
   //     TextView txtcohort;
     //   TextView txtthumb;
 
        inflater = (LayoutInflater) context1
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
        View itemView = inflater.inflate(R.layout.prog_listview_item, parent, false);
        // Get the position from the results
        HashMap<String, String> resultp = new HashMap<String, String>();
        resultp = data.get(position);
 
        // Locate the TextView in listview_item.xml
        txttit = (TextView) itemView.findViewById(R.id.title);
//        txtcontnt = (TextView) itemView.findViewById(R.id.contnt);
  //      txtcohort = (TextView) itemView.findViewById(R.id.coho);   
    //    txtthumb = (TextView) itemView.findViewById(R.id.thumb);
 
        // Capture position and set results to the TextViews
        txttit.setText(resultp.get(MainActivity.PTITLE));
//        txtcontnt.setText(resultp.get(MainActivity.NAME));
  //      txtcohort.setText(resultp.get(MainActivity.LEADER));
    //    txtthumb.setText(resultp.get(MainActivity.URL));
 
        // Capture position and set results to the ImageView
        // Passes thumbnail images URL into ImageLoader.class to download and
        // cache images
 
        // Capture button clicks on ListView items
        itemView.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // Get the position from the results
                HashMap<String, String> resultp = new HashMap<String, String>();
                resultp = data.get(position);
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context1, ProgSingleItemView.class);
                // Pass all data title
                intent.putExtra("programmeTitle", resultp.get(MainActivity.PTITLE));
                // Pass all data description
                intent.putExtra("shortName", resultp.get(MainActivity.PNAME));
                // Pass all data description
                intent.putExtra("programmeLeader", resultp.get(MainActivity.PLEADER));
                // Pass all data thumbnail
                intent.putExtra("url", resultp.get(MainActivity.PURL));
                // Start SingleItemView Class
                context1.startActivity(intent);
 
            }
        });
 
        return itemView;
    }
}