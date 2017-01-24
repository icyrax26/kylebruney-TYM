// small change to JSON meta tag names 11/11/13 ACJ

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
 
public class ModListViewAdapter extends BaseAdapter {
 
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
 
    public ModListViewAdapter(Context context,
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
 //       TextView txtnam;
   //     TextView txtlea;
     //   TextView txtc;
 //       TextView txtle;
   //     TextView txtse;
     //   TextView txtpr;
 //       TextView txtra;
   //     TextView txtsy;


 
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
        View itemView = inflater.inflate(R.layout.mod_listview_item, parent, false);
        // Get the position from the results
        HashMap<String, String> resultp = new HashMap<String, String>();
        resultp = data.get(position);
 
        // Locate the TextView in listview_item.xml
        txtdat = (TextView) itemView.findViewById(R.id.dat);
//        txtnam = (TextView) itemView.findViewById(R.id.nam);
  //      txtlea = (TextView) itemView.findViewById(R.id.lea);
    //    txtc = (TextView) itemView.findViewById(R.id.c);
//        txtle = (TextView) itemView.findViewById(R.id.le);
  //      txtse = (TextView) itemView.findViewById(R.id.se);
    //    txtpr = (TextView) itemView.findViewById(R.id.pr);
//        txtra = (TextView) itemView.findViewById(R.id.ra);
  //      txtsy = (TextView) itemView.findViewById(R.id.sy);

 
        // Capture position and set results to the TextViews
        txtdat.setText(resultp.get(MainActivity.MTITLE));
 //       txtnam.setText(resultp.get(MainActivity.NAME));
   //     txtlea.setText(resultp.get(MainActivity.LEADER));
     //   txtc.setText(resultp.get(MainActivity.CRN));
//        txtle.setText(resultp.get(MainActivity.LEVEL));
  //      txtse.setText(resultp.get(MainActivity.SEMESTER));
    //    txtpr.setText(resultp.get(MainActivity.PROGRAMME));
//        txtra.setText(resultp.get(MainActivity.RATIO));
  //      txtsy.setText(resultp.get(MainActivity.SYLLABUS));



 
        // Capture button clicks on ListView items
        itemView.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // Get the position from the results
                HashMap<String, String> resultp = new HashMap<String, String>();
                resultp = data.get(position);
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, ModSingleItemView.class);
                // Pass all data title
                intent.putExtra("moduleTitle", resultp.get(MainActivity.MTITLE));
                intent.putExtra("shortName", resultp.get(MainActivity.MNAME));
                intent.putExtra("moduleLeader", resultp.get(MainActivity.MLEADER));
                intent.putExtra("crn", resultp.get(MainActivity.MCRN));
                intent.putExtra("level", resultp.get(MainActivity.MLEVEL));
                intent.putExtra("semester", resultp.get(MainActivity.MSEMESTER));
                intent.putExtra("programmes", resultp.get(MainActivity.MPROGRAMME));
                intent.putExtra("examRatio", resultp.get(MainActivity.MRATIO));
                intent.putExtra("syllabus", resultp.get(MainActivity.MSYLLABUS));


                // Start SingleItemView Class
                context.startActivity(intent);
 
            }
        });
 
        return itemView;
    }
}