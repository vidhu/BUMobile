package com.vidhucraft.android.bumobile.apps.calendar;

import java.util.ArrayList;

import com.vidhucraft.android.bumobile.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SideBarListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	
	private ArrayList<Category> items;
	
	public SideBarListAdapter(Context context, ArrayList<Category> items){
		this.context = context;
		this.items = items;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView item;	
		if (convertView == null){
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.app_calendar_row, null);
		}	
		item = (TextView) convertView.findViewById(R.id.app_calendar_title);
		item.setText(this.items.get(position).getTitle());
		
		return convertView;
	}

}
