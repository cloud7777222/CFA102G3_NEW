package com.memTime.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;

public class ObjToJSON {
	String day = null;
	String time = null;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStr() {
		Jedis jedis = new Jedis("localhost", 6379);

		String men = "men" + ":1";
		String day = java.sql.Date.valueOf("2020-03-11").toString();
		String time = "000000000000000000000000";
		String day2 = java.sql.Date.valueOf("2020-03-12").toString();
		String time2 = "000000000003330000000000";

		jedis.hset(men, day, time);
		jedis.hset(men, day2, time2);

		System.out.println("men 1's 行程 is: " + jedis.hget(men, day));
		System.out.println("men 1's 行程 is: " + jedis.hget(men, day2));
		System.out.println(jedis.hgetAll(men).toString().replace("=", ":"));

		String str = jedis.hgetAll(men).toString().replace("=", ":");
		return str;
	}

//	public static void main(String[] args) {
//		Jedis jedis = new Jedis("localhost", 6379);
//		
//		String men="men"+":1";
//		String day = java.sql.Date.valueOf("2020-03-11").toString();
//		String time ="000000000000000000000000";
//		String day2 = java.sql.Date.valueOf("2020-03-12").toString();
//		String time2 ="000000000003330000000000";
////		List all = new ArrayList();
////		
////		List datingList = new ArrayList();
////		List activityList = new ArrayList();
////		List lessonList = new ArrayList();
////		datingList.add("999");
////		activityList.add("888");
////		lessonList.add("666");
////		datingList.add("999");
////		activityList.add("888");
////		lessonList.add("666");
////		
////		all.add(datingList);
////		all.add(activityList);
////		all.add(lessonList);
//		
//		
////		jedis.hset(men, day, datingList.toString());
////		jedis.hset(men, day, datingList.toString());
//		
//		jedis.hset(men, day, time);
//		jedis.hset(men, day2, time2);
//		
//		
//		System.out.println("men 1's 行程 is: " + jedis.hget(men, day));
//		System.out.println("men 1's 行程 is: " + jedis.hget(men, day2));
//		System.out.println(jedis.hgetAll(men).toString().replace("=", ":"));
//		TestJ aa=new TestJ();
//		aa.str=jedis.hgetAll(men).toString().replace("=", ":");
////		HashMap<String, String> data = new HashMap<>();
////		data.put("brand", "Pentel");
////		data.put("price", "50");
////		// 無須對應ORM設計，可自由為key增減自己需要的欄位與欄位值
////		data.put("color", "blue");
////		
////		jedis.hmset("pen:2", data);
////		List<String> penData = jedis.hmget("pen:2", "brand", "price", "color");
////		System.out.println("HMGET: ");
////		for (String str : penData)
////			System.out.println(str);
////		
////		Map<String, String> hAll = jedis.hgetAll("pen:2");
////		for (String str : hAll.keySet())
////			System.out.println(str + " = " + hAll.get(str));
//		
//		// 註：Hash沒有提供hincr(遞增)指令，只有hincrby指令可用
//		
//		// List to JSON
////				jsonStr = new JSONArray(bookList).toString();
////				System.out.println("List to JSON: " + jsonStr);
//////				// JSON to List
////				List<Book> books = new ArrayList<Book>();
////				JSONArray jsonArray = new JSONArray(jsonStr);
////				for (int i = 0; i < jsonArray.length(); i++) {
////					JSONObject json_book = jsonArray.getJSONObject(i);
////					String book_name = json_book.getString("name");
////					double book_price = json_book.getDouble("price");
////					String book_author = json_book.getString("author");
////					Book book = new Book(book_name, book_price, book_author);
////					books.add(book);
////				}
////				for (Book book : books) {
////					book.show();
////				}
////				System.out.println();
//		
//		jedis.close();
//	}

}
