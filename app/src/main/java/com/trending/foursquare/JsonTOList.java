package com.trending.foursquare;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin-PC on 11/9/2015.
 */

public class JsonTOList
{
	Context context;
	public  JsonTOList(Context context)
	{
		this.context = context;
	}

	List<HashMap<String, String>> list= new ArrayList<>();
	String namefordb , locationnamefordb, restfordb;
	List<HashMap<String, String>> convertjsontolist(JSONObject jsonObject)
	{

		JSONObject objectResponse = jsonObject.optJSONObject("response");
		JSONArray array = objectResponse.optJSONArray("venues");

		for (int i = 0; i < 20; i++)
		{
			JSONObject objectVenues = array.optJSONObject(i);
			HashMap<String, String> hashMap = new HashMap<>();

			String name  = objectVenues.optString("name");
			hashMap.put("name", name);
			namefordb = name;

			JSONArray jsonArrayCategories = objectVenues.optJSONArray("categories");
			for (int j = 0; j < jsonArrayCategories.length(); j++)
			{
				JSONObject objectCategory = jsonArrayCategories.optJSONObject(j);
				String restaurant = objectCategory.optString("name");
				hashMap.put("restaurant" , restaurant);
				restfordb = restaurant;
			}

			JSONObject objectLocation = objectVenues.optJSONObject("location");
			String locationname = objectLocation.optString("address");

			hashMap.put("location", locationname);
			locationnamefordb = locationname;

			SqlExecutor sqlExecutor = new SqlExecutor(context);
			sqlExecutor.open();
			sqlExecutor.addRow(namefordb, locationnamefordb , restfordb);
			sqlExecutor.close();

			list.add(hashMap);
		}



		SavedList.getInstance().setList(list);
		return list;
	}

}
