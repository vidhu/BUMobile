package com.vidhucraft.android.bumobile.apps.calendar;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vidhucraft.android.bumobile.R;

public class ViewEventDetails extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.app_calendar_event_detail, container, false);
	}
}
