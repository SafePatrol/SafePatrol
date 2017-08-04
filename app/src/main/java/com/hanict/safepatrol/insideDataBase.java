package com.hanict.safepatrol;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;

public class insideDataBase {

	private static insideDataBase instance = null;
	private static Activity activity = null;
	private  SQLiteDatabase db = null;

	private insideDataBase() throws Exception{
		super();
		OpenDatabase();
		// 데이터 베이스 구조체
	}

	public static synchronized insideDataBase GetInstance(Activity act) throws Exception{
	// 데이터 베이스 인스턴스를 만듬.
		if(instance == null) {
			activity = act;
			instance = new insideDataBase();
		}

		return instance;
	}

	private void OpenDatabase() throws Exception {
		// 데이터 베이스를 여는 함수
		try{
			if(db == null){
				db = activity.openOrCreateDatabase("db", Activity.MODE_PRIVATE, null);
				db.execSQL("Create Table IF NOT EXISTS TIME (start_time_hour_1 int, start_time_Minute_1 int, end_time_hour_1 int, end_time_Minute_1 int, start_time_hour_2 int, start_time_Minute_2 int, end_time_hour_2 int, end_time_Minute_2 int) ");
				db.execSQL("insert into TIME values(0,0,0,0,0,0,0,0)");
			}
		}catch(Exception ig){
			throw ig;
		}
	}

	public void TimeAppend(int start_time_hour_1, int start_time_Minute_1, int end_time_hour_1, int end_time_Minute_1, int start_time_hour_2, int start_time_Minute_2, int end_time_hour_2, int end_time_Minute_2) throws Exception{ // 출퇴근 관련 데이터 베이스에 데이터를 저장하는 함수
		db.execSQL("update into TIME values(" + start_time_hour_1 + "," + start_time_Minute_1 + "," + end_time_hour_1 + ","+ end_time_Minute_1 + ","+ start_time_hour_2 + ","+ start_time_Minute_2 + ","+ end_time_hour_2 + ","+ end_time_Minute_2 + ") ");
	}

	public void dropTable() {
		db.execSQL("drop table INSIDE");
	} // 내부 데이터 베이스를 삭제하는 함수

	// 내부 데이터 베이스를 조회하는 함수
	public HashMap<String, Integer> TimeSelect() {
		Cursor cursor = db.rawQuery("Select * From TIME", null);
		HashMap<String, Integer> Items = null;
		if (cursor.moveToFirst()) {
			do {
				Items = new HashMap<String, Integer>();
				Items.put("start_time_hour_1", cursor.getInt(0));
				Items.put("start_time_Minute_1", cursor.getInt(1));
				Items.put("end_time_hour_1", cursor.getInt(2));
				Items.put("end_time_Minute_1", cursor.getInt(3));
				Items.put("start_time_hour_2", cursor.getInt(4));
				Items.put("start_time_Minute_2", cursor.getInt(5));
				Items.put("end_time_hour_2", cursor.getInt(6));
				Items.put("end_time_Minute_2", cursor.getInt(7));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return Items;
	}
}
