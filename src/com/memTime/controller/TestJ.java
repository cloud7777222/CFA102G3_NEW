package com.memTime.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dateappoorder.model.DateappoorderDAO;
import com.dateappoorder.model.DateappoorderVO;

import redis.clients.jedis.Jedis;


public class TestJ {
	String str = null;
	public static String strFormat(String str) {
		return "\"" + str +"\"" ;
	}

	public static void writeDatingdata(int memberNoA,int memberNoB,String dateAppoDate) {
		Jedis jedis = new Jedis("localhost", 6379);

		String memA = "mem" + ":" + memberNoA ;
		String memB = "mem" + ":" + memberNoB ;
		
//		2002-01-01 11:11:11
		String day = dateAppoDate.substring(0,11);
		String time = dateAppoDate.substring(12,13)=="0"?dateAppoDate.substring(13,14):dateAppoDate.substring(12,14);
		StringBuilder sbA= (null==jedis.hget(memA, day)) ? new StringBuilder("444444000000000000000000"):new StringBuilder(jedis.hget(memA, day).replace("\"", ""));
//		sbA.replace(start, end, str)
		if(null==jedis.hget(memA, day)) {
			jedis.hset(memA, day, time);
		}else {
			
		}
		jedis.hset(memB, day, time);
		
		jedis.close();

	}
	
	public String getJsonData() {
		Jedis jedis = new Jedis("localhost", 6379);

		String men = "men" + ":2";
		String day = strFormat(java.sql.Date.valueOf("2020-03-17").toString());
		String time =strFormat("000000000000000000000000");
		String day2 = strFormat(java.sql.Date.valueOf("2020-03-19").toString());
		String time2 =strFormat("000000000000777700000000");
		
		HashMap<String, String> data = new HashMap<>();
		data.put(day,time);
		data.put(day2,time2);
		// 無須對應ORM設計，可自由為key增減自己需要的欄位與欄位值
		

		jedis.hmset(men, data);
		
		String jsonData = jedis.hgetAll(men).toString().replace("=", ":");
		System.out.println("jsonData="+jsonData);
		jedis.close();
		return jsonData;
	}

	public String getStr() {
		Jedis jedis = new Jedis("localhost", 6379);

		String men = "men" + ":1";
		String day = "\"" + java.sql.Date.valueOf("2020-03-11").toString() + "\"";
		String time = "\"" + "000000000000000000000000" + "\"";
		String day2 = "\"" + java.sql.Date.valueOf("2020-03-12").toString() + "\"";
		String time2 = "\"" + "000000000003330000000000" + "\"";

		jedis.hset(men, day, time);
		jedis.hset(men, day2, time2);

//		System.out.println("men 1's 行程 is: " + jedis.hget(men,day));
//		System.out.println("men 1's 行程 is: " + jedis.hget(men,day2));
		System.out.println(jedis.hgetAll(men).toString().replace("=", ":"));
		
		

		String str = jedis.hgetAll(men).toString().replace("=", ":");
		jedis.close();
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public static void main(String[] args) {
		StringBuilder sbA= new StringBuilder("99999999999");
		System.out.println(sbA.replace(0, 1, "3"));
		System.out.println(sbA.charAt(0));
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
		DateappoorderDAO dateappoorderDAO = new DateappoorderDAO();
		List<DateappoorderVO> orderList=dateappoorderDAO.getAll();
		System.out.println("allOrder:"+orderList.size());
		System.out.println("===========");
		List<DateappoorderVO> newList=orderList.stream()
			.filter(i -> i.getMemberNoA().toString().equals("3") || i.getMemberNoB().toString().equals("3")) //會員的訂單
			.filter(i -> i.getDateAppoDate().getTime() > new java.util.Date().getTime()) //約會時間大於現在
			.collect(Collectors.toList());
		for(DateappoorderVO i : newList) {
			System.out.println(i);
		}
		System.out.println("filter後");
		System.out.println("===========");
		String jsonData = new JSONArray(newList).toString();
		System.out.println("List to JSON: " + jsonData);//會員的訂單
		
		System.out.println("===========");
		List<DateappoorderVO> newListB=orderList.stream()
			.filter(i -> i.getMemberNoA().toString().equals("3") || i.getMemberNoB().toString().equals("3")) //會員的訂單
			.filter(i -> i.getDateAppoDate().getTime() > new java.util.Date().getTime()) //約會時間大於現在
			.collect(Collectors.toList());
		for(DateappoorderVO i : newListB) {
			System.out.println(i);
		}

//		jsonStr = new JSONObject(book1).toString();
//		System.out.println("Object to JSON: " + jsonStr);
//		// JSON to Object
//		JSONObject jsonObj = new JSONObject(jsonStr);
//		String name = jsonObj.getString("name");
//		double price = jsonObj.getDouble("price");
//		String author = jsonObj.getString("author");
//		Book myBook = new Book(name, price, author);
//		myBook.show();
//		System.out.println();
//		
//		jedis.close();
		TestJ aa= new TestJ();
		aa.getStr();
	}

}
