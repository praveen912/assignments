package com.example.androidassignment;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SimpleAdapter;

public class viewNearbyPlaces extends Activity {
	ListView mylist;
	GPSTracker gps;
	ProgressDialog pDialog;
	NearbyPlaces places;
	PlacesList plist;
	ArrayList<HashMap<String, String>> placesListItems = new ArrayList<HashMap<String, String>>();
	HashMap<String, String> map;

	DisplayAlert alert = new DisplayAlert();

	public static String KEY_REFERENCE = "reference";
	public static String KEY_NAME = "name";
	public static String KEY_VICINITY = "vicinity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearby_places);
		mylist = (ListView) findViewById(R.id.listView);
		Button mb = (Button) findViewById(R.id.mapb);
		mylist.setOnItemClickListener(itemListener);
		mb.setOnClickListener(mapListener);
		gps = new GPSTracker(this);

		// checking if GPS can get location
		if (gps.canGetLocation()) {
			Log.d("Your Location", "latitude:" + gps.getLatitude()
					+ ", longitude: " + gps.getLongitude());
		} else {
			alert.showAlertDialog(viewNearbyPlaces.this, "GPS Status",
					"Couldn't get location information. Please enable GPS",
					false);
			return;
		}
		new LoadPlaces().execute();
	}
	
	private OnItemClickListener itemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			String reference = ((TextView) view.findViewById(R.id.reference)).getText().toString();
			Intent myIntent = new Intent(getApplicationContext(),SinglePlaceActivity.class);
			myIntent.putExtra(KEY_REFERENCE, reference);
			startActivity(myIntent);			
		}
		
	};

	private OnClickListener mapListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			Intent myIntent = new Intent(viewNearbyPlaces.this,
					MapActivity1.class);
			myIntent.putExtra("user_latitude",Double.toString(gps.getLatitude()));
			myIntent.putExtra("user_longitude",	Double.toString(gps.getLongitude()));
			myIntent.putExtra("near_places", plist);
			viewNearbyPlaces.this.startActivity(myIntent);
		}
	};

	class LoadPlaces extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(viewNearbyPlaces.this);
			pDialog.setMessage(Html
					.fromHtml("<b>Search</b><br/>Loading Places..."));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

			places = new NearbyPlaces();
			try {
				String types = "pizza|restaurant";
				double radius = 1000;
				plist = places.search(gps.getLatitude(), gps.getLongitude(),
						radius, types);
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String file_url) {

			pDialog.dismiss();

			runOnUiThread(new Runnable() {

				public void run() {

					String status = plist.status;
					if (status.equals("OK")) {
						if (plist.results != null) {
							for (Place p : plist.results) {
								map = new HashMap<String, String>();
								map.put(KEY_REFERENCE, p.reference);
								map.put(KEY_NAME, p.name);
								placesListItems.add(map);
							}
							ListAdapter adapter = new SimpleAdapter(
									viewNearbyPlaces.this, placesListItems,
									R.layout.list_item, new String[] {KEY_REFERENCE, KEY_NAME},
									new int[] {R.id.reference, R.id.name});
							mylist.setAdapter(adapter);
						}
					} else if (status.equals("ZERO_RESULTS")) {
						alert.showAlertDialog(
								viewNearbyPlaces.this,
								"Near Places",
								"Sorry no places found. Try to change the types of places",
								false);
					} else if (status.equals("UNKNOWN_ERROR")) {
						alert.showAlertDialog(viewNearbyPlaces.this,
								"Places Error", "Sorry unknown error occured.",
								false);
					} else if (status.equals("OVER_QUERY_LIMIT")) {
						alert.showAlertDialog(
								viewNearbyPlaces.this,
								"Places Error",
								"Sorry query limit to google places is reached",
								false);
					} else if (status.equals("REQUEST_DENIED")) {
						alert.showAlertDialog(viewNearbyPlaces.this,
								"Places Error",
								"Sorry error occured. Request is denied", false);
					} else if (status.equals("INVALID_REQUEST")) {
						alert.showAlertDialog(viewNearbyPlaces.this,
								"Places Error",
								"Sorry error occured. Invalid Request", false);
					} else {
						alert.showAlertDialog(viewNearbyPlaces.this,
								"Places Error", "Sorry error occured.", false);
					}
				}
			});

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
