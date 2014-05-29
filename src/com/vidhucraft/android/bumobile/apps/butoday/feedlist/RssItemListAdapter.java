package com.vidhucraft.android.bumobile.apps.butoday.feedlist;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.vidhucraft.android.bumobile.R;

public class RssItemListAdapter extends BaseAdapter {

	Context context;
	JSONArray data;
	private static LayoutInflater inflater;
	private ImageLoader imageLoader;
	private Calendar date;
	
	public RssItemListAdapter(Context context, JSONArray data) {
		this.context = context;
		this.data = data;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.imageLoader = ImageLoader.getInstance();
		this.date = Calendar.getInstance(Locale.US);
	}

	@Override
	public int getCount() {
		return data.length();
	}

	@Override
	public Object getItem(int position) {
		try {
			return data.get(position);
		} catch (JSONException e) {
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null){
			convertView = inflater.inflate(R.layout.app_butoday_row, null);
			holder = new ViewHolder();
			holder.position = position;
			holder.image = (ImageView) convertView.findViewById(R.id.post_image);
			holder.tv_title = (TextView) convertView.findViewById(R.id.post_title);
			holder.tv_description = (TextView) convertView.findViewById(R.id.post_description);
			holder.tv_postTimeStamp = (TextView) convertView.findViewById(R.id.post_date);
			
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}	
		

		try {
			//Set Title
			String title = data.getJSONObject(position).getString("title");
			holder.tv_title.setText(title);
			holder.title = title;
			
			
			//Set Description
			String description = data.getJSONObject(position).getString("description");
			holder.tv_description.setText(description);
			holder.description = description;

			
			//Set Time Stamp
			String postTimeStamp = String.valueOf(Long.parseLong(data.getJSONObject(position).getString("postTimeStamp"))*1000);
			date.setTimeInMillis(Long.parseLong(postTimeStamp));
			holder.tv_postTimeStamp.setText(new SimpleDateFormat("d MMMM yyyy").format(date.getTime()));
			holder.dataTimeStamp = postTimeStamp;
			
			
			//Set Item Image
			String imgUrl = data.getJSONObject(position).getString("image");
			if(!imgUrl.equals("null")){
				imageLoader.displayImage(imgUrl, holder.image);
			}else{
				holder.image.setImageBitmap(null);
			}
			holder.imgUrl = imgUrl;
			
			//Store full content
			holder.fullcontent = data.getJSONObject(position).getString("content");
			
			//Set Post URL
			String postURL = data.getJSONObject(position).getString("link");
			holder.newsURL = postURL;
		} catch (JSONException e) {
		}

		return convertView;
	}

	public static class ViewHolder implements Parcelable{
		int position;
		
		ImageView image;
		TextView tv_title;
		TextView tv_description;
		TextView tv_postTimeStamp;
		
		public String title;
		public String description;
		public String imgUrl;
		public String dataTimeStamp;
		public String fullcontent;
		public String newsURL;
		
		public ViewHolder(){};
		
		public ViewHolder(Parcel in){
			String[] data = new String[6];
			
			in.readStringArray(data);
			this.title = data[0];
			this.description = data[1];
			this.imgUrl = data[2];
			this.dataTimeStamp = data[3];
			this.fullcontent = data[4];
			this.newsURL = data[5];
		}
		
		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeStringArray(new String[] {this.title, this.description, this.imgUrl, this.dataTimeStamp, this.fullcontent, this.newsURL});
		}
		
		public static final Parcelable.Creator<ViewHolder> CREATOR = new Parcelable.Creator<ViewHolder>() {

			@Override
			public ViewHolder createFromParcel(Parcel in) {
				return new ViewHolder(in);
			}

			@Override
			public ViewHolder[] newArray(int size) {
				return new ViewHolder[size];
			}
			
		};
	}
}
