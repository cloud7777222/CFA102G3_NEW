package jdbc.util.CompositeQuery;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class jdbcUtil_CompositeQuery_Post {

	public static String get_aCondition_For_Belovedb(String columnName, String value) {
		String aCondition = null;

		if ("postNo".equals(columnName) || "memberNo".equals(columnName) || "posttype".equals(columnName)
				|| "numOFLike".equals(columnName) || "postState".equals(columnName) || "mesCount".equals(columnName))
			aCondition = columnName + "=" + value;
		else if ("postContent".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("postTime".equals(columnName)) // 用於date
			aCondition = columnName + "=" + "'" + value + "'"; // for 其它DB 的 date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet(); // 取得KEY
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Belovedb(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳
		// java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("postNo", new String[] {});
		map.put("postTypeNo", new String[] {});
		map.put("memberNo", new String[] {});
		map.put("postContent", new String[] {});
		map.put("postTime", new String[] {});
		map.put("postState", new String[] {});
		map.put("mesCount", new String[] {});
		map.put("numOfLike", new String[] {});
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from emp2 " + jdbcUtil_CompositeQuery_Post.get_WhereCondition(map)
				+ "order by empno";
		System.out.println("--finalSQL = " + finalSQL);
	}

}
