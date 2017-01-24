package com.example.tym;

import com.actionbarsherlock.app.SherlockFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
 
public class Home extends SherlockFragment {
  //WU, adapter variable for the ListView 
  	private ListViewAdapter m_adapter;
  	//WU, set adapter function
  	public void setAdapter(ListViewAdapter adapter){
  		m_adapter = adapter;
  	}
  	
  	@Override
  	public View onCreateView(LayoutInflater inflater, ViewGroup container,
  			Bundle savedInstanceState) {
  		View rootView = inflater.inflate(R.layout.home, container, false);
  		
  		//WU, every time when the fragment's view is created
  		//retrieve the listview and set the adapter
  		ListView lView = (ListView) rootView.findViewById(R.id.listview);
  		if(m_adapter!= null)
  			lView.setAdapter(m_adapter);
  		
  		return rootView;
  	}
  	
}