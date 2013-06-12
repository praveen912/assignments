package com.example.androidassignment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button slocb = (Button) findViewById(R.id.searchlocb);
		Button vb = (Button) findViewById(R.id.viewPlacesb);
		Button eb = (Button) findViewById(R.id.exitb);
		slocb.setOnClickListener(searchListener);
		vb.setOnClickListener(viewPlacesListener);
		eb.setOnClickListener(exitListener);
	}

	private OnClickListener searchListener = new OnClickListener() {

		public void onClick(View view) {
			Intent myIntent = new Intent(MainActivity.this,
					searchLocation.class);
			MainActivity.this.startActivity(myIntent);
		}
	};

	private OnClickListener viewPlacesListener = new OnClickListener() {

		public void onClick(View view) {
			Intent myIntent = new Intent(MainActivity.this,
					viewNearbyPlaces.class);
			MainActivity.this.startActivity(myIntent);
		}
	};

	private OnClickListener exitListener = new OnClickListener() {

		public void onClick(View view) {
			finish();
		}
	};
}
