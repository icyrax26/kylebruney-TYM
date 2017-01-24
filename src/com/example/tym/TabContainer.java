package com.example.tym;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabContainer extends TabActivity {
	// TabSpec Names
	private static final String MONDAY_SPEC = "MON";
	private static final String TUESDAY_SPEC = "TUE";
	private static final String WEDNESDAY_SPEC = "WED";
	private static final String THURSDAY_SPEC = "THUR";
	private static final String FRIDAY_SPEC = "FRI";
	public static String PASSED_URL = null ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabmain);
        
        //*********** passing a the url**********
        Intent i = getIntent();
        // getting attached intent data
        String programmes = i.getStringExtra("programmes");
        TabContainer.PASSED_URL = null;
        if (programmes.equals("Computer Science 1st Year Group1"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-CC1.1.json";
        }
        else if (programmes.equals("Computer Science 1st Year Group2"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-CC1.2.json";
        }
        else if (programmes.equals("Computer Science 1st Year Group3"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-CC1.3.json";
        }
        else if (programmes.equals("Computer Science 1st Year Group4"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-CC1.4.json";
        }
        else if (programmes.equals("Computer Science 2nd Year Group1"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-CS2.1.json";
        }
        else if (programmes.equals("Computer Science 2nd Year Group2"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-CS2.2.json";
        }
        else if(programmes.equals("Computer Science Final Year"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-CSF.json";
        }
        else if (programmes.equals("Software Engineering 2nd Year Group1"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-SE2.1.json";
        }
        else if (programmes.equals("Software Engineering 2nd Year Group2"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-SE2.2.json";
        }
        else if (programmes.equals("Software Engineering Final Year"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-SEF.json";
        }
        else if (programmes.equals("Managing Information Technology 1st Year"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-MIT1.json";
        }
        else if (programmes.equals("Managing Information Technology 2nd Year"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-MIT2.json";
        }
        else if (programmes.equals("Managing Information Technology Final Year"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-MITF.json";
        }
        else if (programmes.equals("Internet Computing Final Year"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-ICF.json";
        }
        else if (programmes.equals("Computing Final Year"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-COMPF.json";
        }
        else if (programmes.equals("Msc. Software Engineering"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-MSE.json";
        }
        else if (programmes.equals("Msc. Database and Web Based Systems"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-DWBS.json";
        }
        else if (programmes.equals("Msc. Advanced Computing"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-ACS.json";
        }
        else if (programmes.equals("Msc. Information Security"))
        {
        	TabContainer.PASSED_URL = "http://www.firstyearmatters.info/json/tt-InfoSec.json";
        }
        
        
        //*********** passing a the url ends**********
        TabHost tabHost = getTabHost();
        
        // Inbox Tab
        TabSpec mondaySpec = tabHost.newTabSpec(MONDAY_SPEC);
        // Tab Icon
        mondaySpec.setIndicator(MONDAY_SPEC, getResources().getDrawable(R.drawable.icon_inbox));
        Intent mondayIntent = new Intent(this, Monday.class);
        // Tab Content
        mondaySpec.setContent(mondayIntent);
       
        // Outbox Tab
        TabSpec tuesdaySpec = tabHost.newTabSpec(TUESDAY_SPEC);
        tuesdaySpec.setIndicator(TUESDAY_SPEC, getResources().getDrawable(R.drawable.icon_outbox));
        Intent tuesdayIntent = new Intent(this, Tuesday.class);
        tuesdaySpec.setContent(tuesdayIntent);
        
        // Profile Tab
        TabSpec wednesdaySpec = tabHost.newTabSpec(WEDNESDAY_SPEC);
        wednesdaySpec.setIndicator(WEDNESDAY_SPEC, getResources().getDrawable(R.drawable.icon_profile));
        Intent wednesdayIntent = new Intent(this, Wednesday.class);
        wednesdaySpec.setContent(wednesdayIntent);
        
        // Profile Tab
        TabSpec thursdaySpec = tabHost.newTabSpec(THURSDAY_SPEC);
        thursdaySpec.setIndicator(THURSDAY_SPEC, getResources().getDrawable(R.drawable.icon_profile));
        Intent thursdayIntent = new Intent(this, Thursday.class);
        thursdaySpec.setContent(thursdayIntent);
        
        // Profile Tab
        TabSpec fridaySpec = tabHost.newTabSpec(FRIDAY_SPEC);
        fridaySpec.setIndicator(FRIDAY_SPEC, getResources().getDrawable(R.drawable.icon_profile));
        Intent fridayIntent = new Intent(this, Friday.class);
        fridaySpec.setContent(fridayIntent);
        // Adding all TabSpec to TabHost
        tabHost.addTab(mondaySpec); // Adding Inbox tab
        tabHost.addTab(tuesdaySpec); // Adding Outbox tab
        tabHost.addTab(wednesdaySpec); // Adding Profile tab
        tabHost.addTab(thursdaySpec);
        tabHost.addTab(fridaySpec);

    }
}