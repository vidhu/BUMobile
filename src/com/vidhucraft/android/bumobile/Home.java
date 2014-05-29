package com.vidhucraft.android.bumobile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;

public class Home extends Activity {

	private ImageButton ibtn_bus;
	private ImageButton ibtn_butoday;
	private ImageButton ibtn_calander;
	private ImageButton ibtn_chat;
	
	private Ico_Button_Handler ico_Button_Handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		//Set button click handler
		ico_Button_Handler = new Ico_Button_Handler(this);
		
		//Set click for BUS
		ibtn_bus = (ImageButton) findViewById(R.id.ibtn_home_bus);
		ibtn_bus.setOnClickListener(ico_Button_Handler);
		
		//Set click for BUToday
		ibtn_butoday = (ImageButton) findViewById(R.id.ibtn_home_butoday);
		ibtn_butoday.setOnClickListener(ico_Button_Handler);
		
		//Set click for Calendar
		ibtn_calander = (ImageButton) findViewById(R.id.ibtn_home_calendar);
		ibtn_calander.setOnClickListener(ico_Button_Handler);
		
		//Set click for buChat
		ibtn_chat = (ImageButton) findViewById(R.id.ibtn_home_buchat);
		ibtn_chat.setOnClickListener(ico_Button_Handler);
	}
}
