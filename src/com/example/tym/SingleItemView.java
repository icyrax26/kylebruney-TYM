package com.example.tym;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 
public class SingleItemView extends Activity {
    // Declare Variables
	String URL;
    String date;
    String content;
    String cohort;
    String picture;
    String url;
    ImageLoader imageLoader = new ImageLoader(this);
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);
 
        Intent i = getIntent();
        // Get the result of title
        date = i.getStringExtra("date");
        // Get the result of description
        content = i.getStringExtra("content");
        // Get the result of description
        cohort = i.getStringExtra("cohort");
        
        url = i.getStringExtra("url");
        // Get the result of thumbnail
        picture = i.getStringExtra("picture");
 
        // Locate the TextView in singleitemview.xml
        TextView txtdate = (TextView) findViewById(R.id.date);
        TextView txtcontent = (TextView) findViewById(R.id.content);
        TextView txtcohort = (TextView) findViewById(R.id.cohort);
        // Locate the ImageView in singleitemview.xml
        ImageView img = (ImageView) findViewById(R.id.thumbnail);
 
        // Set results to the TextView
        txtdate.setText(date);
        txtcontent.setText(content);
        txtcohort.setText(cohort);
        imageLoader.DisplayImage(picture, img);
        
        URL =  url;
        
        Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener(){
        	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
						 startActivity(intent);	
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(SingleItemView.this,"No Futher Article", Toast.LENGTH_SHORT).show();
				}

			}
			

    
        });
    }
}