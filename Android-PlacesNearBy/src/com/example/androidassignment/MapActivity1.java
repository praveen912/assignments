package com.example.androidassignment;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapActivity1 extends MapActivity {

	PlacesList nearPlaces;
	MapView mapView;
	List<Overlay> mapOverlays;
	AddItemizedOverlay itemizedOverlay;
	GeoPoint geoPoint;
	MapController mc;

	double latitude;
	double longitude;
	OverlayItem overlayitem;
	Button zoominb,zoomoutb,eb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.android_mapview);
		Intent i = getIntent();
		zoominb = (Button) findViewById(R.id.zoomInb);
		zoomoutb = (Button) findViewById(R.id.zoomOutb);
		eb = (Button) findViewById(R.id.exitb);
		mapView = (MapView) findViewById(R.id.mapview);
		
		String user_latitude = i.getStringExtra("user_latitude");
		String user_longitude = i.getStringExtra("user_longitude");

		nearPlaces = (PlacesList) i.getSerializableExtra("near_places");
		zoominb.setOnClickListener(zoomListener);
		zoomoutb.setOnClickListener(zoomListener);
		eb.setOnClickListener(exitListener);
		mapView.setBuiltInZoomControls(true);
		mapOverlays = mapView.getOverlays();

		geoPoint = new GeoPoint((int) (Double.parseDouble(user_latitude) * 1E6),(int) (Double.parseDouble(user_longitude) * 1E6));

		Drawable drawable_user = this.getResources().getDrawable(
				R.drawable.curr_loc);

		itemizedOverlay = new AddItemizedOverlay(drawable_user, this);

		overlayitem = new OverlayItem(geoPoint, "Your Location", "You");

		itemizedOverlay.addOverlay(overlayitem);

		mapOverlays.add(itemizedOverlay);
		itemizedOverlay.populateNow();
		
		Drawable drawable = this.getResources().getDrawable(R.drawable.map_marker);

		itemizedOverlay = new AddItemizedOverlay(drawable, this);

		mc = mapView.getController();

		int minLat = Integer.MAX_VALUE;
		int minLong = Integer.MAX_VALUE;
		int maxLat = Integer.MIN_VALUE;
		int maxLong = Integer.MIN_VALUE;

		if (nearPlaces.results != null) {
			for (Place place : nearPlaces.results) {
				latitude = place.geometry.location.lat; // latitude
				longitude = place.geometry.location.lng; // longitude

				geoPoint = new GeoPoint((int) (latitude * 1E6),(int) (longitude * 1E6));
				overlayitem = new OverlayItem(geoPoint, place.name,	place.vicinity);
				itemizedOverlay.addOverlay(overlayitem);

				minLat = (int) Math.min(geoPoint.getLatitudeE6(), minLat);
				minLong = (int) Math.min(geoPoint.getLongitudeE6(), minLong);
				maxLat = (int) Math.max(geoPoint.getLatitudeE6(), maxLat);
				maxLong = (int) Math.max(geoPoint.getLongitudeE6(), maxLong);
			}
			mapOverlays.add(itemizedOverlay);

			itemizedOverlay.populateNow();
		}

		mapView.getController().zoomToSpan(Math.abs(minLat - maxLat),Math.abs(minLong - maxLong));

		mc.animateTo(new GeoPoint((maxLat + minLat) / 2,(maxLong + minLong) / 2));
		mapView.postInvalidate();
	}
	
	private OnClickListener zoomListener = new OnClickListener() {

		public void onClick(View view) {
			if(view == zoomoutb)
			{
				mc.zoomOut();
			}
			if(view == zoominb){
				mc.zoomIn();
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
		return false;
	}

}
