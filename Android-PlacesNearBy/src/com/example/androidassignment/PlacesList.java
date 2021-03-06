package com.example.androidassignment;

import java.io.Serializable;
import java.util.List;

import com.google.api.client.util.Key;

public class PlacesList implements Serializable {

	private static final long serialVersionUID = 1L;

	@Key
	public String status;

	@Key
	public List<Place> results;
}