package com.example.tym;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class LinksPage extends SherlockFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.linkspage, container, false);
        
        Intent i = new Intent(getSherlockActivity(), LinksListViewDemo.class);
        startActivity(i);
        
        return rootView;
    }
 
}