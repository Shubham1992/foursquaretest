package com.trending.foursquare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin-PC on 11/10/2015.
 */
public class Description extends Activity
{
	LinearLayout frameLayout ;
	TextView tvName, tvRestaurant , tvDesc, tvloc;
	ImageView imgmain;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
 		setContentView(R.layout.description);
		Intent intent = getIntent();
		int pos = intent.getIntExtra("pos",0);
		tvName = (TextView) findViewById(R.id.name);
		tvRestaurant = (TextView) findViewById(R.id.restaurants);
		tvDesc = (TextView) findViewById(R.id.description);
		tvloc = (TextView) findViewById(R.id.loc);
		imgmain = (ImageView) findViewById(R.id.imageViewmain);
		frameLayout = (LinearLayout) findViewById(R.id.framemain);

		List<HashMap<String, String>> list;

		list = SavedList.getInstance().getList();


		tvName.setText(list.get(pos).get("name").toString());
		tvloc.setText(list.get(pos).get("location").toString());


	}
}
