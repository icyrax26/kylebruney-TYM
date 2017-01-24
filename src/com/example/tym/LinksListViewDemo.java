package com.example.tym;

/* this is the tutorial followed 
   http://hj-shared.orgfree.com/2011/01/android-part-ii-list-view-events-and-webview/
*/


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.Toast;


public class LinksListViewDemo extends Activity {

	// Declare Variables
	// private ProgressDialog mProgressDialog;
	// private ArrayList<HashMap<String, String>> arraylist;
	
	private int[] imageList = { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, 
            R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher }; 
	
	
	private String[] list1 = { "University Student Channel", "Student Life, Advice and Information", "Printer Credits","Password Reset", 
								"IT Service Desk", "Letter Requests","Self Service (PMCs, interruptions, etc)","Disability and Learner Support" };
	private String[] list2 = { "http://students.salford.ac.uk", "http://www.advice.salford.ac.uk/","http://students.salford.ac.uk/uos_student_charter.pdf", 
								"https://identity.salford.ac.uk/StudentPasswordSelfService","http://www.its.salford.ac.uk/servicedesk", 
								"http://www.mystudentinfo.salford.ac.uk/","http://students.salford.ac.uk/selfservice.php", 
								"http://www.advice.salford.ac.uk/disability" };

	// public static String LINKTEXT = "linkText";
    // public static String LINKURL = "linkURL";

	
		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			// Get the view 
			setContentView(R.layout.activity_list_view_demo);
			
			// Execute DownloadJSON AsyncTask
	        // new DownloadJSON().execute();
		
	        
	     // DownloadJSON Asynchronous Task  //
	        /* private class DownloadJSON extends AsyncTask<Void, Void, Void> {
	     
	            @Override	
	            protected void onPreExecute() {
	                super.onPreExecute();
	                // Create a progressdialog
	                mProgressDialog = new ProgressDialog(ListViewDemo.this);
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
	                
	                //  JSON URL
	                String feedUrl = "http://www.firstyearmatters.info/json/links.json";
	             
	                // Retrieve JSON Objects from the given feed in JSONfunctions.class
	                JSONObject json_data = JSONfunctions.getJSONfromURL(feedUrl);   
	                
	                
	                try {
	                    
	                	 // Identify the array name in JSON                 
	                     JSONArray json_result = json_data.getJSONArray("links");
	                     	
	                     // parse JSON
	                     for (int i = 0; i < json_result.length(); i++) {
	                         HashMap<String, String> map = new HashMap<String, String>();
	                        json_data = json_result.getJSONObject(i);
	                         // Retrieve JSON Objects
	                         map.put(LINKTEXT, json_data.getString("linkText"));
	                         map.put(LINKURL, json_data.getString("url"));
	                        
	                         // Set the JSON Objects into the array
	                         arraylist.add(map);
	                     }
	      
	                 } catch (JSONException e) {
	                     Log.e("Error", e.getMessage());
	                     e.printStackTrace();
	                 }
	                 return null;
	            }
	        */
		
	            
	            // protected void onPostExecute(Void args) {
		
	                     // Locate the listview in activity_list_view_demo.xml. lvResult is our end result of what is to be displayed on screen
	                	 ListView lv = (ListView) findViewById(R.id.lvResult);
	                	 
	                     // Pass the results into ListViewAdapter.java.  arraylist is defined at the top of this page.
	                	 LinksListAdapter listAdapter = new LinksListAdapter(LinksListViewDemo.this, imageList, list1, list2);
	                     // ListAdapter listAdapter = new ListAdapter(ListViewDemo.this, arraylist);
	                     
	                     // Binds the Adapter to the ListView
	                	 lv.setAdapter(listAdapter);
	                     
	                	 // Close the progressdialog
	                     // mProgressDialog.dismiss();
			
	                     // moved up: ListView lv = (ListView) findViewById(R.id.lvResult);	
	                     // moved up: ListAdapter listAdapter = new ListAdapter(ListViewDemo.this, list1, list2);
	                     // not used: ListAdapter listAdapter = new ListAdapter(ListViewDemo.this, imageList, list1, list2);
	                     // moved up: lv.setAdapter(listAdapter);
	 
	                     lv.setOnItemClickListener(new OnItemClickListener() {
				
	                    	 @Override
	                    	 public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {			
	                    		 // This part uses Toast as a 'check this is working' test
								/* TextView tvTitle = (TextView) view.findViewById(R.id.item1);
								TextView tvUrl = (TextView) view.findViewById(R.id.item2);
								Toast.makeText(ListViewDemo.this, "Title: " + tvTitle.getText() + "\n" + "Url:" + tvUrl.getText(),
								Toast.LENGTH_LONG).show();
	                    		*/
					
	                    		// Second part, which replaces 1st part above; opens up link in WebView:
	                    		 
	                    		// look for item2, the url 
	                    		TextView tvUrl = (TextView) view.findViewById(R.id.item2);
	                    		
	                    		Intent i = new Intent(LinksListViewDemo.this,WebViewDemo.class);
	                    		i.putExtra("url", tvUrl.getText());
	                    		startActivity(i); 
	                    	 }
	                     });
	            }
	        
	         // end of download JSON async task
	        
	         // need to change the following line to use Sherlock / ActionBarCompat
			 // getActionBar().setDisplayHomeAsUpEnabled(true);
}

