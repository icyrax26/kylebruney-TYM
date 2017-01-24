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
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Tuesday extends ListActivity {
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
	private static final String TTAG_CLASSES = "classes";
	private static final String TTAG_COHORT = "cohort";
	private static final String TTAG_LECTURER = "Lecturer";
	private static final String TTAG_TYPE = "Type";
	private static final String TTAG_CLASS = "class";
	private static final String TTAG_ROOM = "Room";
	private static final String TTAG_DATE = "date";
	private static final String TTAG_LENGTH = "length";
	private static final String TTAG_START = "start";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tuesday_list);
		
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
			pDialog = new ProgressDialog(Tuesday.this);
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
				inbox = json.getJSONArray(TTAG_CLASSES);
				// looping through All messages
				for (int i = 0; i < inbox.length(); i++) {
					JSONObject c = inbox.getJSONObject(i);

					// Storing each json item in variable
					String cohort = c.getString(TTAG_COHORT);
					String classes = c.getString(TTAG_CLASS);
					String lecturer = c.getString(TTAG_LECTURER);
					String date = c.getString(TTAG_DATE);
					String room = c.getString(TTAG_ROOM);
					String type = c.getString(TTAG_TYPE);
					String length = c.getString(TTAG_LENGTH)+"hrs";
					String start = c.getString(TTAG_START)+"00hrs";
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					Date convertDate;
					try {
						convertDate = formatter.parse(date);
						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						Date currentdate = new Date();
						Calendar cal = Calendar.getInstance();
				        cal.setTime(convertDate);
				        boolean day = cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY;
				    
				    if (day){
				    	if (convertDate.compareTo(currentdate) >= 0){
				    	// adding each child node to HashMap key => value
						map.put(TTAG_COHORT, cohort);
						map.put(TTAG_CLASS, classes);
						map.put(TTAG_LECTURER, lecturer);
						map.put(TTAG_DATE, date);
						map.put(TTAG_ROOM, room);
						map.put(TTAG_TYPE, type);
						map.put(TTAG_LENGTH, length);
						map.put(TTAG_START, start);
						

						// adding HashList to ArrayList
						timetableList.add(map);

				    }
				    }	
						
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
			    Toast.makeText(Tuesday.this,"This is a Lecture Free Day", Toast.LENGTH_LONG).show();
			    
			}
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							Tuesday.this, timetableList,
							R.layout.tuesday_list_item, new String[] { TTAG_CLASS, TTAG_LECTURER, TTAG_DATE, TTAG_ROOM, TTAG_TYPE, TTAG_LENGTH, TTAG_START },
							new int[] {  R.id.classes, R.id.lecturer, R.id.date, R.id.room, R.id.type, R.id.length, R.id.time });
					// updating listview
					setListAdapter(adapter);
				}
			});

		}

	}
}

