package com.writing.model;

/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class jdbcUtil_CompositeQuery_Writing {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("WRT_NO".equals(columnName) || "SHOP_NO".equals(columnName) || "WRT_TITLE".equals(columnName)
				|| "WRT_CONT".equals(columnName) || "WRT_STA".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";

		else if ("WRT_PHOTO".equals(columnName) || "WRT_PHOTO_BASE64".equals(columnName)
				|| "WRT_TIME".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;

		// else if ("WRT_TIME".equals(columnName))
		// aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value +
		// "'";

		// Oracle的date

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		String command = "";
		for (String key : keys) {
			String value = map.get(key)[0];
			System.out.println("key(欄位名稱) : " + key); // for debug
			System.out.println("使用者輸入查詢關鍵字為 : " + value);// for debug

			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				command = " where WRT_TITLE like '%" + value.trim() + "%' or WRT_CONT like '%" + value.trim() + "%'";
				System.out.println("送出複合查詢的SQL指令為: " + command);// for debug
				System.out.println("有送出查詢資料的欄位數count = " + count);// for debug
			}
		}

		return command;
	}

	public static void main(String args[]) {

		// 配合 req.getParameterMap()方法 回傳
		// java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("WRT_NO", new String[] { "WRT0000001" });
		map.put("SHOP_NO", new String[] { "SHOP000001" });
		map.put("WRT_TITLE", new String[] { "又大又甜的柳橙" });
		map.put("WRT_CONT", new String[] { "奇異果好好吃" });
		map.put("WRT_TIME", new String[] { "0.0" });
		map.put("WRT_PHOTO_BASE64", new String[] { "10" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from WRITING" + jdbcUtil_CompositeQuery_Writing.get_WhereCondition(map)
				+ "order by WRT_NO";

		System.out.println("●●finalSQL = " + finalSQL);

		ArrayList<WritingVO> list = new ArrayList<WritingVO>();

		Collections.sort(list, new Comparator<WritingVO>() {

			@Override
			public int compare(WritingVO o1, WritingVO o2) {
				int result = o1.getWrt_time().compareTo(o2.getWrt_time());
				return result;
			}
		});

	}
}
