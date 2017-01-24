package com.example.tym;

import com.actionbarsherlock.app.SherlockFragment;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
 
public class TimetablePage extends SherlockFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {	
   
    	//setContentView(R.layout.timetablepage);
    	
        View rootView = inflater.inflate(R.layout.timetablepage, container, false);
        
        Intent i = new Intent(getSherlockActivity(), TimetableMain.class);
        startActivity(i);

      //  ImageButton Timetable = (ImageButton) getView().findViewById(R.id.bttn1);
    	//Timetable.setOnClickListener(new View.OnClickListener() {
    	//	@Override
    	//	public void onClick(View v) {
    			
    	//		Intent i = new Intent(getSherlockActivity(), TimetableMain.class);
    			
    		//	startActivity(i);
    	//	}
    //	});
    	
        
        return rootView;
    }
    


 
}
