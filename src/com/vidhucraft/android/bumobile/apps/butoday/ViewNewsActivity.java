package com.vidhucraft.android.bumobile.apps.butoday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.vidhucraft.android.bumobile.R;
import com.vidhucraft.android.bumobile.apps.butoday.feedlist.RssItemListAdapter.ViewHolder;

public class ViewNewsActivity extends Activity {

	private String title;
	private String description;
	private String imgURL;
	private String fullContent;
	/* private Long dataTimeStamp; */
	private String newsURL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_butoday_activity_view_news);
		
		//Setup action bar
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		//Get post data
		Bundle data = getIntent().getExtras();
		ViewHolder viewHolder = (ViewHolder) data.getParcelable("postData");
		this.title = viewHolder.title;
		this.description = viewHolder.description;
		this.imgURL = viewHolder.imgUrl;
		this.fullContent = viewHolder.fullcontent;
		/* this.dataTimeStamp = Long.parseLong(viewHolder.dataTimeStamp); */
		this.newsURL = viewHolder.newsURL;
		
		//Display PostData
		TextView title = (TextView) findViewById(R.id.viewNews_tv_title);
		title.setText(this.title);
		
		ResizableImageView image = (ResizableImageView) findViewById(R.id.view_news_image);
		ImageLoader.getInstance().displayImage(this.imgURL, image);
		
		TextView fullContent = (TextView) findViewById(R.id.viewNews_tv_news);
		fullContent.setText(Html.fromHtml(this.fullContent.replaceAll("<img.+?>", "")));
		fullContent.setMovementMethod(LinkMovementMethod.getInstance());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.app_butoday_view_news, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		 switch (item.getItemId()) {
	        case android.R.id.home:
	        	finish();
	        	NavUtils.navigateUpFromSameTask(this);
	        case R.id.app_butoday_shareNews:
	        	finish();
	        	shareNews();
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void shareNews(){
		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
	    sharingIntent.setType("text/plain");
	    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BU Today: " + this.title);
	    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, generateShareText());
	    startActivity(Intent.createChooser(sharingIntent, "Share via"));
	}
	
	private String generateShareText(){
		return "Check this out at BU Today:\n" + this.newsURL + "\n\n" + this.description;
	}
}
