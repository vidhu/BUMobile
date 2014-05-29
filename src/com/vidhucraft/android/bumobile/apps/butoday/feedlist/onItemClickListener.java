package com.vidhucraft.android.bumobile.apps.butoday.feedlist;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.vidhucraft.android.bumobile.apps.butoday.ViewNewsActivity;
import com.vidhucraft.android.bumobile.apps.butoday.feedlist.RssItemListAdapter.ViewHolder;

public class onItemClickListener implements OnItemClickListener {

	Context context;
	
	public onItemClickListener(Context context){
		this.context = context;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		RssItemListAdapter.ViewHolder viewHolder = (ViewHolder) view.getTag();		
		
		Intent intent = new Intent(this.context, ViewNewsActivity.class);
		intent.putExtra("postData", viewHolder);
		
		this.context.startActivity(intent);
	}

}