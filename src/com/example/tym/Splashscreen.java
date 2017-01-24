package com.example.tym;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class Splashscreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
	

	// METHOD 1     
    
    /****** Create Thread that will SLEEP for X seconds then redirects to our first screen*************/        
   
	Thread background = new Thread() {
       public void run() {
            
           try {
               // Thread will sleep for 5 seconds
               sleep(5*1000);
                
               // After 5 seconds redirect to another intent
               Intent i=new Intent(getBaseContext(),MainActivity.class);
               startActivity(i);
                
               //Remove activity
               finish();
                
           } catch (InterruptedException e) {
            e.printStackTrace();
           }
       }
   };
    
   
   
   // start thread
   background.start();
	}
	
	
	
	
		
   //METHOD 2  
    
   // **** alternatively instead of sleeping, we can ask the app to do something (e.g. download data) whilst we are doing the following
   
  /*  
   new Handler().postDelayed(new Runnable() {
         
       // Using handler with postDelayed called runnable run method

       @Override
       public void run() {
           Intent i = new Intent(Splashscreen.this, FirstScreen.class);
           startActivity(i);

           // close this activity
           finish();
       }
   }, 5*1000); // wait for 5 seconds
   
   
}
*/
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	/* @Override
	protected void onDestroy() {
    
	super.onDestroy();
    
	}
	*/

}
