package com.coc.ak.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class COCDataHelper extends SQLiteOpenHelper {

	public static final String TABLE_COCMAIN = "cocmaintable";
	public static final String USER_ID = "_id";
	public static final String USER_CLAN_NAME = "clanname";
	public static final String USER_ACTUAL_NAME = "actualname";
	public static final String USER_EMAIL = "email";
	public static final String USER_STATUS = "status";

	private static final String DATABASE_NAME = "cocplannerdatabase.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_COCMAIN + "("
            + USER_ID	+ " integer primary key autoincrement, "
            + USER_CLAN_NAME	+ " text not null, "
            + USER_ACTUAL_NAME + " text not null "
			+ USER_EMAIL + " text not null"
			+ USER_STATUS + " text not null);";

	public COCDataHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(COCDataHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COCMAIN);
		onCreate(db);
	}

}
