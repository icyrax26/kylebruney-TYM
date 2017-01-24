package com.example.tym;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
 
public class ProgSingleItemView extends Activity {
    // Declare Variables
    String ptitle;
    String pname;
    String pleader;
    String purl;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.prog_singleitemview);
 
        Intent i = getIntent();
        // Get the result of title
        ptitle = i.getStringExtra("programmeTitle");
        // Get the result of description
        pname = i.getStringExtra("shortName");
        // Get the result of description
        pleader = i.getStringExtra("programmeLeader");
        // Get the result of thumbnail
        purl = i.getStringExtra("url");
 
        // Locate the TextView in singleitemview.xml
        TextView txtleads = (TextView) findViewById(R.id.titl);
        TextView txtnamm = (TextView) findViewById(R.id.namm);
        TextView txttitl = (TextView) findViewById(R.id.leads);

 
        // Set results to the TextView
        txtleads.setText(ptitle);
        txtnamm.setText(pname);
        txttitl.setText(pleader);
        
        Button ora1 = (Button) findViewById(R.id.button1);
		ora1.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent oracle = new Intent(Intent.ACTION_VIEW, Uri.parse(purl));
				startActivity(oracle);
			}

		});
        
    }
}