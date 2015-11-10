package com.trending.foursquare;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.trending.foursquare.app.AppController;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
	EditText search;
	RecyclerView recyclerView;
	Button buttonSearch;
	String tag_json_obj = "json_obj_req";
	String url="https://api.foursquare.com/v2/venues/search?" +
			"client_id=5TNW0PCKDCR1G40GKFDAHTAFJVBVNSUAQ3U5FCB14DXLNWSP%20&" +
			"client_secret=IUZCRQPEQV0TNTSXKLQKRPLOHRXD20XMDH3SGW2DJFP1SRAA%20&" +
			"v=20130815%20&ll=40.7,-74%20&" +
			"query=";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		search = (EditText) findViewById(R.id.search);
		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		buttonSearch = (Button) findViewById(R.id.btnsearch);
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});

		buttonSearch.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				if (search.getText() != null && search.getText().length()>0)
				{
					makeRequestJSON(search.getText().toString());
				}
			}
		});

	}


	void makeRequestJSON(String searchtxt)
	{
		Log.e("search for:", searchtxt);
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
				url + searchtxt, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.e("TAG RESPONSE", response.toString());
						JsonTOList jsonTOList = new JsonTOList();
						jsonTOList.convertjsontolist(response);

					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d("TAG", "Error: " + error.getMessage());
				// hide the progress dialog

			}
		});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

	}
}
