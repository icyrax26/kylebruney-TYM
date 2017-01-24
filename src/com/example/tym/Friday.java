package com.example.tym;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Friday extends ListActivity {
	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jsonParser = new JSONParser();

	ArrayList<HashMap<String, String>> timetableList;

	// products JSONArray
	JSONArray inbox = null;

	// Inbox JSON url
	String TT_URL = TabContainer.PASSED_URL;
	
	// ALL JSON node names
	private static final String FTAG_CLASSES = "classes";
	private static final String FTAG_COHORT = "cohort";
	private static final String FTAG_LECTURER = "Lecturer";
	private static final String FTAG_TYPE = "Type";
	private static final String FTAG_CLASS = "class";
	private static final String FTAG_ROOM = "Room";
	private static final String FTAG_DATE = "date";
	private static final String FTAG_LENGTH = "length";
	private static final String FTAG_START = "start";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friday_list);
		
		// Hashmap for ListView
        timetableList = new ArrayList<HashMap<String, String>>();
 
        // Loading INBOX in Background Thread
        new LoadInbox().execute();
	}

	/**
	 * Background Async Task to Load all INBOX messages by making HTTP Request
	 * */
	class LoadInbox extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Friday.this);
			pDialog.setMessage("Loading Inbox ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting Inbox JSON
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			// getting JSON string from URL
			JSONObject json = jsonParser.makeHttpRequest(TT_URL, "GET",
					params);

			// Check your log cat for JSON reponse
			Log.d("Inbox JSON: ", json.toString());

			try {
				inbox = json.getJSONArray(FTAG_CLASSES);
				// looping through All messages
				for (int i = 0; i < inbox.length(); i++) {
					JSONObject c = inbox.getJSONObject(i);

					// Storing each json item in variable
					String cohort = c.getString(FTAG_COHORT);
					String classes = c.getString(FTAG_CLASS);
					String lecturer = c.getString(FTAG_LECTURER);
					String date = c.getString(FTAG_DATE);
					String room = c.getString(FTAG_ROOM);
					String type = c.getString(FTAG_TYPE);
					String length = c.getString(FTAG_LENGTH)+"hrs";
					String start = c.getString(FTAG_START)+"00hrs";
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					Date convertDate;
					try {
						convertDate = formatter.parse(date);
						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						Date currentdate = new Date();
						Calendar cal = Calendar.getInstance();
				        cal.setTime(convertDate);
				        boolean day = cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
				    
				    if (day){
				    	if (convertDate.compareTo(currentdate) >= 0){
				    	// adding each child node to HashMap key => value
						map.put(FTAG_COHORT, cohort);
						map.put(FTAG_CLASS, classes);
						map.put(FTAG_LECTURER, lecturer);
						map.put(FTAG_DATE, date);
						map.put(FTAG_ROOM, room);
						map.put(FTAG_TYPE, type);
						map.put(FTAG_LENGTH, length);
						map.put(FTAG_START, start);
						

						// adding HashList to ArrayList
						timetableList.add(map);
				    	}
				    }
				   // View.findViewWithTag(R.layout.friday_list);
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}



			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			if(timetableList.isEmpty() ){
			    Toast.makeText(Friday.this,"This is a Lecture Free Day", Toast.LENGTH_LONG).show();
			    
			}
			
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							Friday.this, timetableList,
							R.layout.friday_list_item, new String[] { FTAG_CLASS, FTAG_LECTURER, FTAG_DATE, FTAG_ROOM, FTAG_TYPE, FTAG_LENGTH, FTAG_START },
							new int[] {  R.id.classes, R.id.lecturer, R.id.date, R.id.room, R.id.type, R.id.length, R.id.time });
					// updating listview
					setListAdapter(adapter);
				}
			});

		}

	}
}
