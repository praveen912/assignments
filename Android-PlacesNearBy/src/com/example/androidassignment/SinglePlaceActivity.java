package com.example.androidassignment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

public class SinglePlaceActivity extends Activity {

	DisplayAlert alert = new DisplayAlert();
	NearbyPlaces places;
	PlaceDetails pdetails;
	ProgressDialog pDialog;
	public static String KEY_REFERENCE = "reference";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_place);
		Intent i = getIntent();
		String reference = i.getStringExtra(KEY_REFERENCE);
		new LoadSinglePlaceDetails().execute(reference);
	}

	class LoadSinglePlaceDetails extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(SinglePlaceActivity.this);
			pDialog.setMessage("Loading profile ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

			String reference = args[0];
			places = new NearbyPlaces();

			try {
				pdetails = places.getPlaceDetails(reference);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String file_url) {

			pDialog.dismiss();
			runOnUiThread(new Runnable() {
				public void run() {
					if (pdetails != null) {
						String status = pdetails.status;
						Log.d("Places Details Status", status);
						if (status.equals("OK")) {
							Log.d("Places Result", "Result" + pdetails.result);
							if (pdetails.result != null) {
								String name = pdetails.result.name;
								String address = pdetails.result.formatted_address;
								String phone = pdetails.result.formatted_phone_number;
								String latitude = Double.toString(pdetails.result.geometry.location.lat);
								String longitude = Double.toString(pdetails.result.geometry.location.lng);
								Log.d("Place ", name + address + phone
										+ latitude + longitude);
								TextView lbl_name = (TextView) findViewById(R.id.name);
								TextView lbl_address = (TextView) findViewById(R.id.address);
								TextView lbl_phone = (TextView) findViewById(R.id.phone);
								TextView lbl_location = (TextView) findViewById(R.id.location);

								name = name == null ? "Not present" : name;
								address = address == null ? "Not present"
										: address;
								phone = phone == null ? "Not present" : phone;
								latitude = latitude == null ? "Not present"
										: latitude;
								longitude = longitude == null ? "Not present"
										: longitude;

								lbl_name.setText(name);
								lbl_address.setText(address);
								lbl_phone.setText(Html
										.fromHtml("<b>Phone:</b> " + phone));
								lbl_location.setText(Html
										.fromHtml("<b>Latitude:</b> "
												+ latitude
												+ ", <b>Longitude:</b> "
												+ longitude));
							}
						} else if (status.equals("ZERO_RESULTS")) {
							alert.showAlertDialog(SinglePlaceActivity.this,
									"Near Places", "Sorry no place found.",
									false);
						} else if (status.equals("UNKNOWN_ERROR")) {
							alert.showAlertDialog(SinglePlaceActivity.this,
									"Places Error",
									"Sorry unknown error occured.", false);
						} else if (status.equals("OVER_QUERY_LIMIT")) {
							alert.showAlertDialog(
									SinglePlaceActivity.this,
									"Places Error",
									"Sorry query limit to google places is reached",
									false);
						} else if (status.equals("REQUEST_DENIED")) {
							alert.showAlertDialog(SinglePlaceActivity.this,
									"Places Error",
									"Sorry error occured. Request is denied",
									false);
						} else if (status.equals("INVALID_REQUEST")) {
							alert.showAlertDialog(SinglePlaceActivity.this,
									"Places Error",
									"Sorry error occured. Invalid Request",
									false);
						} else {
							alert.showAlertDialog(SinglePlaceActivity.this,
									"Places Error", "Sorry error occured.",
									false);
						}
					} else {
						alert.showAlertDialog(SinglePlaceActivity.this,
								"Places Error", "Sorry error occured.", false);
					}

				}
			});

		}

	}

}