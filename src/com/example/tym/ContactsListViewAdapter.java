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
 
public class ContactsListViewAdapter extends BaseAdapter {
 
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
 
    public ContactsListViewAdapter(Context context,
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
        TextView txttitle;
        TextView txtdesc;
        ImageView thumb;
        TextView txtphone;
        TextView txtroom;
        TextView txtshorty;
 
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
        View itemView = inflater.inflate(R.layout.contacts_listview_item, parent, false);
        // Get the position from the results
        HashMap<String, String> resultp = new HashMap<String, String>();
        resultp = data.get(position);
 
        // Locate the TextView in listview_item.xml
        txttitle = (TextView) itemView.findViewById(R.id.nam);
        txtdesc = (TextView) itemView.findViewById(R.id.emal);
        txtroom = (TextView) itemView.findViewById(R.id.rom);
        txtphone = (TextView) itemView.findViewById(R.id.phon);
        txtshorty = (TextView) itemView.findViewById(R.id.shorty);
        // Locate the ImageView in listview_item.xml
        thumb = (ImageView) itemView.findViewById(R.id.phot);
 
        // Capture position and set results to the TextViews
        txttitle.setText(resultp.get(MainActivity.NAME));
        txtdesc.setText(resultp.get(MainActivity.EMAIL));
        txtroom.setText(resultp.get(MainActivity.ROOM));
        txtphone.setText(resultp.get(MainActivity.PHONE));
        txtshorty.setText(resultp.get(MainActivity.SHORT));
 
        // Capture position and set results to the ImageView
        // Passes thumbnail images URL into ImageLoader.class to download and
        // cache images
        imageLoader.DisplayImage(resultp.get(MainActivity.PHOTO), thumb);
 
        // Capture button clicks on ListView items
        itemView.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // Get the position from the results
                HashMap<String, String> resultp = new HashMap<String, String>();
                resultp = data.get(position);
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleContacts.class);
                // Pass all data name
                intent.putExtra("name", resultp.get(MainActivity.NAME));
                // Pass all data email
                intent.putExtra("email", resultp.get(MainActivity.EMAIL));
             // Pass all data room
                intent.putExtra("room", resultp.get(MainActivity.ROOM));
             // Pass all data phone
                intent.putExtra("phone", resultp.get(MainActivity.PHONE));
             // Pass all data short
                intent.putExtra("shorty", resultp.get(MainActivity.SHORT));
             // Pass all data photo
                intent.putExtra("photo", resultp.get(MainActivity.PHOTO));
                // Start SingleItemView Class
                context.startActivity(intent);
 
            }
        });
 
        return itemView;
    }
}