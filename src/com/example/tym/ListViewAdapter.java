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
import android.widget.ImageView;
import android.widget.TextView;
 
public class ListViewAdapter extends BaseAdapter {
 
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
 
    public ListViewAdapter(Context context,
        ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
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
        TextView txtdat;
        TextView txtcontnt;
        TextView txtcohort;
        ImageView thumb;
 
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        // Get the position from the results
        HashMap<String, String> resultp = new HashMap<String, String>();
        resultp = data.get(position);
 
        // Locate the TextView in listview_item.xml
        txtdat = (TextView) itemView.findViewById(R.id.dat);
        txtcontnt = (TextView) itemView.findViewById(R.id.contnt);
        txtcohort = (TextView) itemView.findViewById(R.id.coho);   
        // Locate the ImageView in listview_item.xml
        thumb = (ImageView) itemView.findViewById(R.id.thumb);
 
        // Capture position and set results to the TextViews
        txtdat.setText(resultp.get(MainActivity.DATE));
        txtcontnt.setText(resultp.get(MainActivity.CONTENT));
        txtcohort.setText(resultp.get(MainActivity.COHORT));
 
        // Capture position and set results to the ImageView
        // Passes thumbnail images URL into ImageLoader.class to download and
        // cache images
        imageLoader.DisplayImage(resultp.get(MainActivity.PICTURE), thumb);
 
        // Capture button clicks on ListView items
        itemView.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // Get the position from the results
                HashMap<String, String> resultp = new HashMap<String, String>();
                resultp = data.get(position);
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data title
                intent.putExtra("date", resultp.get(MainActivity.DATE));
                // Pass all data description
                intent.putExtra("content", resultp.get(MainActivity.CONTENT));
                // Pass all data description
                intent.putExtra("cohort", resultp.get(MainActivity.COHORT));
                // Pass all data thumbnail
                intent.putExtra("picture", resultp.get(MainActivity.PICTURE));
                intent.putExtra("url", resultp.get(MainActivity.URL));
                // Start SingleItemView Class
                context.startActivity(intent);
 
            }
        });
 
        return itemView;
    }
}