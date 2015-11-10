package com.trending.foursquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin-PC on 11/10/2015.
 */
public class SavedList
{
	private static SavedList savedList = new SavedList();
	private  SavedList(){}

	public List<HashMap<String, String>> getList()
	{
		return list;
	}

	public void setList(List<HashMap<String, String>> list)
	{
		this.list = list;
	}

	private List<HashMap<String, String>> list = new ArrayList<>();
	public static SavedList  getInstance()
	{
		return savedList;
	}

}
