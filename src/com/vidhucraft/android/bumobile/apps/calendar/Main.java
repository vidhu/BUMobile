package com.vidhucraft.android.bumobile.apps.calendar;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.vidhucraft.android.bumobile.R;
import com.vidhucraft.android.bumobile.apps.calendar.feed.DownloadEventsByCategory;
import com.vidhucraft.android.bumobile.apps.calendar.feed.EventItemListAdapter;

public class Main extends Activity {

	DrawerLayout dl_layout;
	ListView category;
	JSONArray events;

	ArrayList<Category> items = new ArrayList<Category>();
	ListView lv_events;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_calender_fragment_calendar);

		lv_events = (ListView) findViewById(R.id.lv_eventList);
		dl_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
		populateSideBar();

		category.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Load Contents
				Category category = items.get(position);
				new DownloadEventsByCategory(Main.this, category.getName(), category.getTitle()).execute();
				
				//Close Drawer
				dl_layout.closeDrawer(findViewById(R.id.left_drawer));
			}
		});
		
		lv_events.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
				getFragmentManager()
				.beginTransaction()
				.replace(R.id.app_calendar_main_container, new ViewEventDetails())
				.addToBackStack(null)
				.commit();
			}
		});
	}

	public void loadEvents(JSONArray events) {
		this.events = events;
		ArrayList<String> eventList = new ArrayList<String>();
		try {
			for (int i = 0; i < events.length(); i++) {
				eventList.add(events.getJSONObject(i).getString("SUMMARY"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		lv_events.setAdapter(new EventItemListAdapter(this, events));
	}

	protected void populateSideBar() {
		
		items.add(new Category("All Categories", "all-categories"));
		items.add(new Category("Featured Events", "featured-events"));
		items.add(new Category("Alumni Association", "alumni"));
		items.add(new Category("Arts", "arts"));
		items.add(new Category("BU Central", "bu-central"));
		items.add(new Category("Center for the Humanities",
				"center-for-the-humanities"));
		items.add(new Category("Charity & Volunteering",
				"charity--volunteering"));
		items.add(new Category("Commencement", "commencement"));
		items.add(new Category("Conferences & Workshops",
				"conferences--workshops"));
		items.add(new Category("Examinations", "examinations"));
		items.add(new Category("Food & Beverage", "food--beverage"));
		items.add(new Category("Global", "global"));
		items.add(new Category("Lectures", "lectures"));
		items.add(new Category("LAW Community", "law-community"));
		items.add(new Category("Meetings", "meetings"));
		items.add(new Category("Orientation", "orientation"));
		items.add(new Category("Other Events", "other-events"));
		items.add(new Category("Religious Services & Activities",
				"religious-services--activities"));
		items.add(new Category("Special Interest to Women",
				"special-interest-to-women"));
		items.add(new Category("Sports & Recreation", "sports--recreation"));
		items.add(new Category("Social Events", "social-events"));
		items.add(new Category("Study Abroad", "study-abroad"));

		category = (ListView) findViewById(R.id.left_drawer);
		category.setAdapter(new SideBarListAdapter(this, items));
	}
}
