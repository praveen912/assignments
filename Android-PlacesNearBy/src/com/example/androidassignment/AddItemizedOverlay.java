package com.example.androidassignment;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class AddItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	private Context context;

	public AddItemizedOverlay(Drawable marker) {
		super(boundCenterBottom(marker));
	}

	public AddItemizedOverlay(Drawable marker, Context context) {
		this(marker);
		this.context = context;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {

		if (event.getAction() == 1) {
			GeoPoint geopoint = mapView.getProjection().fromPixels(
					(int) event.getX(), (int) event.getY());
			double lat = geopoint.getLatitudeE6() / 1E6;
			double lon = geopoint.getLongitudeE6() / 1E6;
			Toast.makeText(context, "Lat: " + lat + ", Lon: " + lon,
					Toast.LENGTH_SHORT).show();
		}
		return false;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mapOverlays.get(i);
	}

	@Override
	public int size() {
		return mapOverlays.size();
	}

	@Override
	protected boolean onTap(int index) {
		OverlayItem item = mapOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(this.context);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		dialog.show();
		return true;
	}

	public void addOverlay(OverlayItem overlay) {
		mapOverlays.add(overlay);
	}

	public void populateNow() {
		this.populate();
	}
}