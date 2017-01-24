package com.example.tym;


import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.view.GravityCompat;

//libraries for JSON ****1
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.os.AsyncTask;
//import android.os.Bundle;
import android.util.Log;
//import android.widget.ListView;

public class MainActivity extends SherlockFragmentActivity {

	// Declare Variables

	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	MenuListAdapter mMenuAdapter;
	String[] title;
	String[] subtitle;
	int[] icon;
	Fragment home = new Home();
	Fragment timetablepage = new TimetablePage();
	Fragment coursepage = new CoursePage();
	Fragment contacts = new ContactsPage();
	Fragment programmes = new ProgrammesPage();
	Fragment assessmentpage = new AssessmentPage();
	Fragment linkspage = new LinksPage();
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	
	private boolean doubleBackToExitPressedOnce;


	// Declare Variables for json ****2 (news)
	
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    static String DATE = "date";
    static String CONTENT = "content";
    static String PICTURE = "picture";
    static String COHORT = "cohort";
    static String URL = "url";
    
	
    // Declare Variables contacts
    ListView listviewContacts;
    ContactsListViewAdapter adapterContacts;
    ProgressDialog mProgressDialogContacts;
    ArrayList<HashMap<String, String>> arraylistContacts;
    static String NAME = "name";
    static String EMAIL = "email";
    static String PHOTO = "photo";
    static String PHONE = "phone";
    static String ROOM = "room";
    static String SHORT = "short";
    
    
    //Declare Variables Programme
    ListView listviewProg;
    ProgListViewAdapter adapterProg;
    ProgressDialog mProgressDialogProg;
    ArrayList<HashMap<String, String>> arraylistProg;
    static String PTITLE = "ptitle";
    static String PNAME = "pname";
    static String PLEADER = "pleader";
    static String PURL = "purl";
    
    //Declare Variables Modules
    ListView listviewMod;
    ModListViewAdapter adapterMod;
    ProgressDialog mProgressDialogMod;
    ArrayList<HashMap<String, String>> arraylistMod;
    static String MTITLE = "mtitle";
    static String MNAME = "mname";
    static String MLEADER = "mleader";
    static String MCRN = "mcrn";
    static String MLEVEL = "mlevel";
    static String MSEMESTER = "msemester";
    static String MPROGRAMME = "mprogramme";
    static String MRATIO = "mratio";
    static String MSYLLABUS = "msyllabus";
    
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from drawer_main.xml
		setContentView(R.layout.drawer_main);

		// Execute DownloadJSON AsyncTask ****3
		new DownloadJSON().execute();
		// Get the Title
		mTitle = mDrawerTitle = getTitle();

		// Generate title
		title = new String[] { "News", "Timetable", "Course",
				 "Programmes","Contacts", "Assessment Schedules", "Links" };

		// Generate subtitle
		subtitle = new String[] { "Event Feeds", "Find Lectures", "Find Module Info",
				 "Find a Programme", "Staff Address","Workshop Groups", "Useful University Links" };

		// Generate icon
		icon = new int[] { R.drawable.news, R.drawable.timetablecanvas,
				R.drawable.courseseditcanvas, R.drawable.programmeditcanvas, R.drawable.contacticon, R.drawable.action_about, R.drawable.collections_cloud};

		// Locate DrawerLayout in drawer_main.xml
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Locate ListView in drawer_main.xml
		mDrawerList = (ListView) findViewById(R.id.listview_drawer);

		// Set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Pass string arrays to MenuListAdapter
		mMenuAdapter = new MenuListAdapter(MainActivity.this, title, subtitle,
				icon);

		// Set the MenuListAdapter to the ListView
		mDrawerList.setAdapter(mMenuAdapter);

		// Capture listview menu item click
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				// Set the title on the action when drawer open
				getSupportActionBar().setTitle(mDrawerTitle);
				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	// DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Refreshing Feeds");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }
        
        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            arraylist = new ArrayList<HashMap<String, String>>();
            // YQL JSON URL
            String url = "http://www.firstyearmatters.info/json/news.json";
            
         // Retrieve JSON Objects from the given URL in JSONfunctions.class
            JSONObject json_data = JSONfunctions.getJSONfromURL(url);

 
            try {
               // Locate the array name in JSON 
                                
                JSONArray json_result = json_data.getJSONArray("news");
 
                for (int i = 0; i < json_result.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                   json_data = json_result.getJSONObject(i);
                    // Retrieve JSON Objects
                    map.put("date", json_data.getString("date"));
                    map.put("content", json_data.getString("content"));
                    map.put("picture", json_data.getString("picture"));
                    map.put("cohort", json_data.getString("cohort"));
                    map.put("url", json_data.getString("url"));
                  //map.put("flag", jsonobject.getString("flag"));
                    // Set the JSON Objects into the array
                    arraylist.add(map);
                }
 
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
 
        @Override
        protected void onPostExecute(Void args) {
        	// Locate the listview in listview_main.xml
			listview = (ListView) findViewById(R.id.listview);
			// Pass the results into ListViewAdapter.java
			adapter = new ListViewAdapter(MainActivity.this, arraylist);
			
			//WU, remember adapter reference in Fragment1 
			if(home != null)
				((Home)home).setAdapter(adapter);
			
			// Binds the Adapter to the ListView
			listview.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
    }
    
    
  //***** 2nd JSON call for Contacts
	
  	// DownloadJSON AsyncTask
      private class DownloadJSONContacts extends AsyncTask<Void, Void, Void> {
   
          @Override
          protected void onPreExecute() {
              super.onPreExecute();
              // Create a progressdialog
              mProgressDialogContacts = new ProgressDialog(MainActivity.this);
              // Set progressdialog name
              mProgressDialogContacts.setTitle("Refreshing Feeds");
              // Set progressdialog message
              mProgressDialogContacts.setMessage("Loading...");
              mProgressDialogContacts.setIndeterminate(false);
              // Show progressdialog
              mProgressDialogContacts.show();
          }
          
          @Override
          protected Void doInBackground(Void... params) {
              // Create the array
              arraylistContacts = new ArrayList<HashMap<String, String>>();
              //  JSON URL
              String url = "http://www.firstyearmatters.info/json/staff.json";
              
           // Retrieve JSON Objects from the given URL in JSONfunctions.class
              JSONObject json_data = JSONfunctions.getJSONfromURL(url);

   
              try {
                 // Locate the array name in JSON 
                                  
                  JSONArray json_result = json_data.getJSONArray("staff");
   
                  for (int i = 0; i < json_result.length(); i++) {
                      HashMap<String, String> map = new HashMap<String, String>();
                     json_data = json_result.getJSONObject(i);
                      // Retrieve JSON Objects
                      
                      map.put("name", json_data.getString("name"));
                      map.put("email", json_data.getString("email"));
                      map.put("photo", json_data.getString("photo"));
                      map.put("phone", json_data.getString("phone"));
                      map.put("room", json_data.getString("room"));
                      map.put("short", json_data.getString("short"));
                    //  map.put("flag", jsonobject.getString("flag"));
                      // Set the JSON Objects into the array
                      arraylistContacts.add(map);
                  }
   
              } catch (JSONException e) {
                  Log.e("Error", e.getMessage());
                  e.printStackTrace();
              }
              return null;
          }
   
          @Override
          protected void onPostExecute(Void args) {
              // Locate the listview in listview_main.xml
              listviewContacts = (ListView) findViewById(R.id.listviewc);
              // Pass the results into ListViewAdapter.java
              adapterContacts = new ContactsListViewAdapter(MainActivity.this, arraylistContacts);
              // Binds the Adapter to the ListView
              
  			
  			//WU, remember adapter reference in Fragment1 
  			if(contacts != null)
  				((ContactsPage)contacts).setAdapter(adapterContacts);
  			
  			// Binds the Adapter to the ListView
              listviewContacts.setAdapter(adapterContacts);
              // Close the progressdialog
              mProgressDialogContacts.dismiss();
          }
      }

  	
  	//***** 2nd JSON ends
	
      
      
      // ******3rd JSON PROG Starts
   // DownloadJSON AsyncTask
      private class DownloadJSONProg extends AsyncTask<Void, Void, Void> {
   
          @Override
          protected void onPreExecute() {
              super.onPreExecute();
              // Create a progressdialog
              mProgressDialogProg = new ProgressDialog(MainActivity.this);
              // Set progressdialog title
              mProgressDialogProg.setTitle("Refreshing Feeds");
              // Set progressdialog message
              mProgressDialogProg.setMessage("Loading...");
              mProgressDialogProg.setIndeterminate(false);
              // Show progressdialog
              mProgressDialogProg.show();
          }
          
          @Override
          protected Void doInBackground(Void... params) {
              // Create the array
              arraylistProg = new ArrayList<HashMap<String, String>>();
              // YQL JSON URL
              String url = "http://www.firstyearmatters.info/json/programmes.json";
              
           // Retrieve JSON Objects from the given URL in JSONfunctions.class
              JSONObject json_data = JSONfunctions.getJSONfromURL(url);

   
              try {
                 // Locate the array name in JSON 
                                  
                  JSONArray json_result = json_data.getJSONArray("programmes");
   
                  for (int i = 0; i < json_result.length(); i++) {
                      HashMap<String, String> map = new HashMap<String, String>();
                     json_data = json_result.getJSONObject(i);
                      // Retrieve JSON Objects
                      map.put("ptitle", json_data.getString("programmeTitle"));
                      map.put("pname", json_data.getString("shortName"));
                      map.put("pleader", json_data.getString("programmeLeader"));
                      map.put("purl", json_data.getString("url"));
                    //map.put("flag", jsonobject.getString("flag"));
                      // Set the JSON Objects into the array
                      arraylistProg.add(map);
                  }
   
              } catch (JSONException e) {
                  Log.e("Error", e.getMessage());
                  e.printStackTrace();
              }
              return null;
          }
   
          @Override
          protected void onPostExecute(Void args) {
              // Locate the listview in listview_main.xml
              listviewProg = (ListView) findViewById(R.id.listviewprog);
              // Pass the results into ListViewAdapter.java
              adapterProg = new ProgListViewAdapter(MainActivity.this, arraylistProg);
              // Binds the Adapter to the ListView
              
            //WU, remember adapter reference in Fragment1 
    			if(programmes != null)
    				((ProgrammesPage)programmes).setAdapter(adapterProg);
    			
              listviewProg.setAdapter(adapterProg);
              // Close the progressdialog
              mProgressDialogProg.dismiss();
          }
      }
      
      //*****3rd JSON Prog ends.
      
      //***** 4th JSON Mod starts.
      
   // DownloadJSON AsyncTask
      private class DownloadJSONMod extends AsyncTask<Void, Void, Void> {
   
          @Override	
          protected void onPreExecute() {
              super.onPreExecute();
              // Create a progressdialog
              mProgressDialogMod = new ProgressDialog(MainActivity.this);
              // Set progressdialog title
              mProgressDialogMod.setTitle("Refreshing Feeds");
              // Set progressdialog message
              mProgressDialogMod.setMessage("Loading...");
              mProgressDialogMod.setIndeterminate(false);
              // Show progressdialog
              mProgressDialogMod.show();
          }
          
          @Override
          protected Void doInBackground(Void... params) {
              // Create the array
              arraylistMod = new ArrayList<HashMap<String, String>>();
              // YQL JSON URL
              String url = "http://www.firstyearmatters.info/json/modules.json";
              
           // Retrieve JSON Objects from the given URL in JSONfunctions.class
              JSONObject json_data = JSONfunctions.getJSONfromURL(url);

   
              try {
                 // Locate the array name in JSON 
                                  
                  JSONArray json_result = json_data.getJSONArray("modules");
   
                  for (int i = 0; i < json_result.length(); i++) {
                      HashMap<String, String> map = new HashMap<String, String>();
                     json_data = json_result.getJSONObject(i);
                      // Retrieve JSON Objects
                      map.put("mtitle", json_data.getString("moduleTitle"));
                      map.put("mname", json_data.getString("shortName"));
                      map.put("mleader", json_data.getString("moduleLeader"));
                      map.put("mcrn", json_data.getString("crn"));
                      map.put("mlevel", json_data.getString("level"));
                      map.put("msemester", json_data.getString("semester"));
                      map.put("mprogramme", json_data.getString("programmes"));
                      map.put("mratio", json_data.getString("examRatio"));
                      map.put("msyllabus", json_data.getString("syllabus"));
                      
                    //map.put("flag", jsonobject.getString("flag"));
                      // Set the JSON Objects into the array
                      arraylistMod.add(map);
                  }
   
              } catch (JSONException e) {
                  Log.e("Error", e.getMessage());
                  e.printStackTrace();
              }
              return null;
          }
   
          @Override
          protected void onPostExecute(Void args) {
              // Locate the listview in listview_main.xml
              listviewMod = (ListView) findViewById(R.id.mod_listview);
              // Pass the results into ListViewAdapter.java
              adapterMod = new ModListViewAdapter(MainActivity.this, arraylistMod);
              
            //WU, remember adapter reference in Fragment1 
  			if(coursepage != null)
  				((CoursePage)coursepage).setAdapter(adapterMod);
  			
              // Binds the Adapter to the ListView
              listviewMod.setAdapter(adapterMod);
              // Close the progressdialog
              mProgressDialogMod.dismiss();
          }
      }
      
      //*****4th JSON Mod ends.
      
      
      
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {

			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}

		return super.onOptionsItemSelected(item);
	}

	// ListView click listener in the navigation drawer
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// Locate Position
		switch (position) {
		case 0:
			ft.replace(R.id.content_frame, home);
			break;
		case 1:
			ft.replace(R.id.content_frame, timetablepage);
			break;
		case 2:
			ft.replace(R.id.content_frame, coursepage);
			new DownloadJSONMod().execute();
			break;
		case 3:
			ft.replace(R.id.content_frame, programmes);
			new DownloadJSONProg().execute();
			break;
		case 4:
			ft.replace(R.id.content_frame, contacts);
			new DownloadJSONContacts().execute();
			break;
		case 5:
			ft.replace(R.id.content_frame, assessmentpage);
			break;
		case 6:
			ft.replace(R.id.content_frame, linkspage);
			break;		
		}
		ft.commit();
		mDrawerList.setItemChecked(position, true);

		// Get the title followed by the position
		setTitle(title[position]);
		// Close drawer
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}
	
	@Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
             doubleBackToExitPressedOnce=false;   

            }
        }, 2000);
    } 
}


