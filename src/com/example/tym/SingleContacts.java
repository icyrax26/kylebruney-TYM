package com.example.tym;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.PorterDuff;
 
public class SingleContacts extends Activity {
    // Declare Variables
    String name;
    String email;
    String room;
    String phone;
    String photo;
    String shorty;
    ImageLoader imageLoader = new ImageLoader(this);
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.contsingle_view);
 
        Intent i = getIntent();
        // Get the result of name
        name = i.getStringExtra("name");
        // Get the result of email
        email = i.getStringExtra("email");
        // Get the result of room
        room = i.getStringExtra("room");
        // Get the result of short
        shorty = i.getStringExtra("short");
        // Get the result of email
        phone = i.getStringExtra("phone");
        // Get the result of photo
      photo = i.getStringExtra("photo");
 
        // Locate the TextView in singleitemview.xml
        TextView txttitle = (TextView) findViewById(R.id.name);
        TextView txtdescription = (TextView) findViewById(R.id.email);
        TextView txtroom = (TextView) findViewById(R.id.room);
        TextView txtphone = (TextView) findViewById(R.id.phone);
        TextView txtshort = (TextView) findViewById(R.id.shorty);
        // Locate the ImageView in singleitemview.xml
        ImageView img = (ImageView) findViewById(R.id.photo);
 
        // Set results to the TextView
        txttitle.setText(name);
        txtdescription.setText(email);
        txtroom.setText(room);
        txtphone.setText(phone);
        txtshort.setText(shorty);
 
        imageLoader.DisplayImage(photo, img);

        //Call Button
        
        Button b1 = (Button) findViewById(R.id.button1);
        b1.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
        b1.setOnClickListener(new OnClickListener(){
        	
			@Override
			public void onClick(View v) {
			
				String phoneNumber = phone;
			  Uri parsedPhoneNumber = Uri.parse("tel:"+phoneNumber); 
			 Intent i = new Intent(Intent.ACTION_CALL,parsedPhoneNumber);
				  startActivity(i);

				 // super.onbutton1Click(l, v, position, id);
								
			}
        });
        
        //Email Button
        
        Button b2 = (Button) findViewById(R.id.button2);
        b2.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        b2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				/* Create the Intent */
				final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

				/* Fill it with Data */
				emailIntent.setType("text/plain");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Enquiries");
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hello ");

				/* Send it off to the Activity-Chooser */
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
				
			}
        });
        	
        }
        
    }
