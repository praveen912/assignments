package com.example.androidassignment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class searchLocation extends MapActivity {

	AutoCompleteTextView loc;
	MapView mapview;
	MapController mc;
	GeoPoint p;
	List<Overlay> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_location);
		loc = (AutoCompleteTextView) findViewById(R.id.location);
		Button sb = (Button) findViewById(R.id.searchb);
		Button eb = (Button) findViewById(R.id.exitb);
		mapview = (MapView) findViewById(R.id.mapview);
		mapview.setBuiltInZoomControls(true);
		mc = mapview.getController();
		sb.setOnClickListener(searchListener);
		eb.setOnClickListener(exitListener);
		loc.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.place_view));
	}

	private OnClickListener searchListener = new OnClickListener() {

		public void onClick(View view) {
			Geocoder gc = new Geocoder(view.getContext(), Locale.getDefault());
			try {
				List<Address> addresses = gc.getFromLocationName(loc.getText().toString(), 5);
				if (addresses.size() > 0) {
					p = new GeoPoint(
							(int) (addresses.get(0).getLatitude() * 1E6),
							(int) (addresses.get(0).getLongitude() * 1E6));
					mc.animateTo(p);
					mc.setZoom(20);
					list = mapview.getOverlays();
					list.clear();
					mapOverlay mo = new mapOverlay();
					list.add(mo);
					mapview.invalidate();
				}

			} catch (IOException e) {
				e.printStackTrace();
				Toast toast = Toast.makeText(getApplicationContext(),
						e.toString(), Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	};

	private OnClickListener exitListener = new OnClickListener() {

		public void onClick(View view) {
			finish();
		}
	};

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	class mapOverlay extends com.google.android.maps.Overlay {

		@Override
		public boolean draw(Canvas canvas, MapView mapview, boolean shadow,
				long when) {
			super.draw(canvas, mapview, shadow);
			Point sp = new Point();
			mapview.getProjection().toPixels(p, sp);
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.map_marker);
			canvas.drawBitmap(bmp, sp.x, sp.y - 50, null);
			return true;
		}
	}

}
