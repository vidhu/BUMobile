package com.vidhucraft.android.bumobile;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class Ico_Button_Handler implements OnClickListener {
	
	Context context;

	public Ico_Button_Handler(Context context){
		this.context = context;
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibtn_home_bus:
			context.startActivity(new Intent(context, com.vidhucraft.android.bumobile.apps.bus.Main.class));
			break;
		case R.id.ibtn_home_butoday:
			context.startActivity(new Intent(context, com.vidhucraft.android.bumobile.apps.butoday.Main.class));
			break;
		case R.id.ibtn_home_calendar:
			context.startActivity(new Intent(context, com.vidhucraft.android.bumobile.apps.calendar.Main.class));
			break;
		case R.id.ibtn_home_buchat:
			context.startActivity(new Intent(context, com.vidhucraft.android.bumobile.apps.buchat.Main.class));
		default:
			break;
		}
	}

}
