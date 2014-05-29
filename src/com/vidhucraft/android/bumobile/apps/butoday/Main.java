package com.vidhucraft.android.bumobile.apps.butoday;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.vidhucraft.android.bumobile.R;
import com.vidhucraft.android.bumobile.apps.butoday.feedlist.DownloadRssString;
import com.vidhucraft.android.bumobile.apps.butoday.feedlist.RssItemListAdapter;
import com.vidhucraft.android.bumobile.apps.butoday.feedlist.onItemClickListener;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.app_butoday_activity_butoday);
		setUpImageHandler();
		
		new DownloadRssString(this).execute();
	}
	
	private void setUpImageHandler(){
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
        .cacheOnDisc(true)
        .build();
		
		ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this)
		.defaultDisplayImageOptions(defaultOptions)
		.build();
		
		ImageLoader.getInstance().init(imageLoaderConfiguration);
	}
	
	public void setRssData(JSONArray feed){
		ListView listview;
		listview = (ListView) findViewById(R.id.lv_rss);
		listview.setDivider(null);
		listview.setDividerHeight(0);
		listview.setAdapter(new RssItemListAdapter(this, feed));
		listview.setOnItemClickListener(new onItemClickListener(this));
	}
}
