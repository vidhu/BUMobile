package com.vidhucraft.android.bumobile.apps.calendar;

import com.vidhucraft.android.bumobile.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewEvents extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.app_calender_fragment_calendar, container, false);
	}
}
