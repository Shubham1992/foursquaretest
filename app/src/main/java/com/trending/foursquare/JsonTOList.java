package com.trending.foursquare;

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
	List<HashMap<String, String>> list= new ArrayList<>();
	List<HashMap<String, String>> convertjsontolist(JSONObject jsonObject)
	{

		JSONArray array = jsonObject.optJSONArray("venues");
		for (int i = 0; i < 20; i++)
		{
			JSONObject objectVenues = array.optJSONObject(i);
			HashMap<String, String> hashMap = new HashMap<>();

			String name  = objectVenues.optString("name");
			hashMap.put("name", name);

			JSONArray jsonArrayCategories = objectVenues.optJSONArray("categories");
			for (int j = 0; j < jsonArrayCategories.length(); j++)
			{
				JSONObject objectCategory = jsonArrayCategories.optJSONObject(j);
				String restaurant = objectCategory.optString("name");
				hashMap.put("restaurant" , restaurant);
			}

			JSONObject objectLocation = objectVenues.optJSONObject("location");
			String locationname = objectLocation.optString("address");

			hashMap.put("location", locationname);

			list.add(hashMap);
		}


		return list;
	}

}
