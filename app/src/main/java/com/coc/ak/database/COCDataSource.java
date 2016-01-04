package com.coc.ak.database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class COCDataSource {

	// Database fields
	private SQLiteDatabase database;
	private COCDataHelper dbHelper;
	private String[] allColumns = { COCDataHelper.USER_ID,
            COCDataHelper.USER_CLAN_NAME, COCDataHelper.USER_ACTUAL_NAME,
            COCDataHelper.USER_EMAIL, COCDataHelper.USER_STATUS};

	public COCDataSource(Context context) {
		dbHelper = new COCDataHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void deletefullDatabase() {

	}

    public String createUser (String cName, String aName, String em, String status) {
        //first check if this record is already present in database
        List<COCData> list = this.getEverything();
        COCData data;
        Iterator<COCData> iterator = list.iterator();
        while (iterator.hasNext()) {
            data = iterator.next();
            if(data.getClanName().equals(cName) && data.getActualName().equals(aName)
                    && data.getEmail().equals(em) && data.getStatus().equals(status)) {
                return data.getClanName();
            }
        }
        ContentValues values = new ContentValues();
        values.put(COCDataHelper.USER_CLAN_NAME, cName);
        values.put(COCDataHelper.USER_ACTUAL_NAME, aName);
        values.put(COCDataHelper.USER_EMAIL, em);
        values.put(COCDataHelper.USER_STATUS, status);

        Log.w("Parameter 'clanName'", String.valueOf(cName));
        Log.w("Parameter 'actualName", String.valueOf(aName));
        Log.w("Parameter 'emailID", String.valueOf(em));
        Log.w("Parameter 'Status", String.valueOf(status));

        Log.w("ContentValues:", String.valueOf(values));
        this.open();
        long insertId = database.insert(COCDataHelper.TABLE_COCMAIN, null,
                values);
        Log.w("Insert ID:", String.valueOf(insertId));
        database.close();
        this.open();
        Cursor cursor = database.query(COCDataHelper.TABLE_COCMAIN,
                allColumns, COCDataHelper.USER_ID + "=" + insertId,
                null, null, null, null);
        cursor.moveToFirst();
        COCData newData = cursorToData(cursor);
        Log.w("User:", String.valueOf(newData));
        Log.w("User ID:", String.valueOf(newData.getId()));
        Log.w("ClanName :", String.valueOf(newData.getClanName()));
        Log.w("ActualName :", String.valueOf(newData.getActualName()));
        Log.w("EmailID :", String.valueOf(newData.getEmail()));
        Log.w("Status :", String.valueOf(newData.getStatus()));

        cursor.close();
        database.close();
        return newData.getClanName();
    }
/*
	public String createAcronym(String acronym, String value) {
		//first check if this record is already present in database
		List<COCData> list = this.getEverything();
		COCData data;
		Iterator<COCData> iterator = list.iterator();
		while (iterator.hasNext()) {
			data = iterator.next();
			if(data.getAcronym().equals(acronym) && data.getValue().equals(value)) {
				return data.getAcronym();
			}
		}
		
		ContentValues values = new ContentValues();
		values.put(COCDataHelper.ACRONYM_VALUE, acronym);
		values.put(COCDataHelper.ACRONYM, value);
		Log.w("Parameter 'acronym'", String.valueOf(acronym));
		Log.w("Parameter 'value", String.valueOf(value));

		Log.w("ContentValues:", String.valueOf(values));
		this.open();
		long insertId = database.insert(COCDataHelper.TABLE_ACRONYMS, null,
				values);
		Log.w("Insert ID:", String.valueOf(insertId));
		database.close();
		this.open();
		Cursor cursor = database.query(COCDataHelper.TABLE_ACRONYMS,
				allColumns, COCDataHelper.ACRONYM_ID + "=" + insertId,
				null, null, null, null);
		cursor.moveToFirst();
		COCData newAcronym = cursorToData(cursor);
		Log.w("Receipt:", String.valueOf(newAcronym));
		Log.w("Acronym ID:", String.valueOf(newAcronym.getId()));
		Log.w("Acronym :", String.valueOf(newAcronym.getAcronym()));
		Log.w("Acronym Value:",
				String.valueOf(newAcronym.getValue()));

		cursor.close();
		database.close();

		return newAcronym.getValue();
	}*/

/*	public void deleteAcronym(COCData acronym) {
		long id = acronym.getId();
		if (!database.isOpen()) {
			this.open();
		}
		Log.w("Delete:", "acronym deleted with id: " + id);
		database.delete(COCDataHelper.TABLE_ACRONYMS,
				COCDataHelper.ACRONYM_ID + " = " + id, null);
		database.close();
	}

	public void deleteAcronym(long id) {
		if (!database.isOpen()) {
			this.open();
		}
		try {
			Log.w("Delete:", "acronym deleted with id: " + id);
			database.delete(COCDataHelper.TABLE_ACRONYMS,
					COCDataHelper.ACRONYM_ID + " = " + id, null);
			database.close();
		} catch (Exception e) {
			Log.w("Delete:" , "acronym deletion operation failed on id:" + id);
		}
	}*/

    public void deleteUser(COCData data) {
        long id = data.getId();
        if (!database.isOpen()) {
            this.open();
        }
        Log.w("Delete:", "user deleted with id: " + id);
        database.delete(COCDataHelper.TABLE_COCMAIN,
                COCDataHelper.USER_ID + " = " + id, null);
        database.close();
    }

    public void deleteUser (long id) {
        if (!database.isOpen()) {
            this.open();
        }
        try {
            Log.w("Delete:", "User deleted with id: " + id);
            database.delete(COCDataHelper.TABLE_COCMAIN,
                    COCDataHelper.USER_ID + " = " + id, null);
            database.close();
        } catch (Exception e) {
            Log.w("Delete:" , "User deletion operation failed on id:" + id);
        }
    }

    public List<COCData> getEverything() {
        List<COCData> allData = new ArrayList<COCData>();
        if (! database.isOpen()) {
            this.open();
        }
        Cursor cursor = database.query(COCDataHelper.TABLE_COCMAIN,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            COCData data = cursorToData(cursor);
            allData.add(data);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return allData;
    }

/*
	public List<COCData> getEverything() {
		List<COCData> acronyms = new ArrayList<COCData>();

		if (! database.isOpen()) {
			this.open();
		}
		Cursor cursor = database.query(COCDataHelper.TABLE_ACRONYMS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			COCData data = cursorToData(cursor);
			acronyms.add(data);
			cursor.moveToNext();
		}
		cursor.close();
		database.close();
		return acronyms;
	}

	public List<String> getAllValues() {
		List<String> values = new ArrayList<String>();
		if (!database.isOpen()) {
			this.open();
		}
		Cursor cursor = database.query(COCDataHelper.TABLE_ACRONYMS,
				allColumns, null, null, null, null, null);
		// String one = cursor.getString(0);
		// String two = cursor.getString(1);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			COCData receipt = cursorToData(cursor);
			String desc = receipt.getValue();
			values.add(desc);
			cursor.moveToNext();
		}
		cursor.close();
		database.close();
		return values;
	}

	public List<String> getAllAcronyms() {
		List<String> values = new ArrayList<String>();
		if (!database.isOpen()) {
			this.open();
		}
		Cursor cursor = database.query(COCDataHelper.TABLE_ACRONYMS,
				allColumns, null, null, null, null, null);
		// String one = cursor.getString(0);
		// String two = cursor.getString(1);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			COCData receipt = cursorToData(cursor);
			String desc = receipt.getAcronym();
			values.add(desc);
			cursor.moveToNext();
		}
		cursor.close();
		database.close();
		return values;
	}*/

	/*public long findIdForValue(String value) {
		long id = 0;
		boolean flag_loop = true;
		if (!database.isOpen()) {
			this.open();
		}
		Cursor cursor = database.query(COCDataHelper.TABLE_ACRONYMS,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast() && flag_loop == true) {
			COCData acronym = cursorToData(cursor);
			String d = acronym.getValue();
			if (d.equals(value)) {
				id = acronym.getId();
				flag_loop = false;
			}
			cursor.moveToNext();
		}
		cursor.close();
		database.close();
		return id;
	}*/

    public long findIdForClanName(String name) {
        long id = 0;
        boolean flag_loop = true;
        if (!database.isOpen()) {
            this.open();
        }
        Cursor cursor = database.query(COCDataHelper.TABLE_COCMAIN,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast() && flag_loop == true) {
            COCData data = cursorToData(cursor);
            String d = data.getClanName();
            if (d.equalsIgnoreCase(name)) {
                id = data.getId();
                flag_loop = false;
            }
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return id;
    }

    public String findAnyStringFromClanName (String clanName, String interestedString) {
        String desiredString = "";
        boolean flag_loop = true;
        if (!database.isOpen()) {
            this.open();
        }
        Cursor cursor = database.query(COCDataHelper.TABLE_COCMAIN,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast() && flag_loop == true) {
            COCData data = cursorToData(cursor);
            String req = data.getClanName();
            if (req.equalsIgnoreCase(clanName)) {
                desiredString = this.getDataFromGetter(data, interestedString);
                flag_loop = false;
            }
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return desiredString;
    }

    private String getDataFromGetter(COCData data, String interest) {
        String ret = "";
        if (interest != null) {

            if (interest.equalsIgnoreCase("actualname")) {
                ret =  data.getActualName();
            }
            if (interest.equalsIgnoreCase("email")) {
                ret =  data.getEmail();
            }
            if (interest.equalsIgnoreCase("status")) {
                ret =  data.getStatus();
            }
        }
        return ret;
    }

	private COCData cursorToData(Cursor cursor) {
		COCData userdata = new COCData();
		userdata.setId(cursor.getLong(0));
		userdata.setClanName(cursor.getString(1));
		userdata.setActualName(cursor.getString(2));
		userdata.setEmail(cursor.getString(3));
		userdata.setStatus(cursor.getString(4));
		return userdata;
	}
/*
	public String getValue(long id) {
		String vlaue = null;
		boolean loop_flag = true;
		if (!database.isOpen()) {
			this.open();
		}
		Cursor cursor = database.query(COCDataHelper.TABLE_ACRONYMS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast() && loop_flag == true) {
			COCData aData = cursorToData(cursor);
			if (aData.getId() == id) {
				vlaue = aData.getValue();
				loop_flag = false;
			}
			cursor.moveToNext();
		}
		cursor.close();
		database.close();
		return vlaue;
	}

	public String getAcronym(long id) {
		String acronym = null;
		boolean loop_flag = true;
		if (!database.isOpen()) {
			this.open();
		}
		Cursor cursor = database.query(COCDataHelper.TABLE_ACRONYMS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast() && loop_flag == true) {
			COCData receipt = cursorToData(cursor);
			if (receipt.getId() == id) {
				acronym = receipt.getAcronym();
				loop_flag = false;
			}
			cursor.moveToNext();
		}
		cursor.close();
		database.close();
		return acronym;
	}*/

    public COCData getUserFromID(long id) {
        COCData data = new COCData();
        boolean loop_flag = true;
        if (!database.isOpen()) {
            this.open();
        }
        Cursor cursor = database.query(COCDataHelper.TABLE_COCMAIN,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast() && loop_flag == true) {
            COCData userData = cursorToData(cursor);
            if (userData.getId() == id) {
                data = userData;
                loop_flag = false;
            }
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return data;
    }

    public COCData getUserFromClanName(String name) {
        COCData data = new COCData();
        boolean loop_flag = true;
        if (!database.isOpen()) {
            this.open();
        }
        Cursor cursor = database.query(COCDataHelper.TABLE_COCMAIN,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast() && loop_flag == true) {
            COCData userData = cursorToData(cursor);
            if (userData.getClanName() == name) {
                data = userData;
                loop_flag = false;
            }
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return data;
    }

	public boolean deleteAll() {

		if( !database.isOpen()) {
			this.open();
		}
		try {
			database.delete(COCDataHelper.TABLE_COCMAIN, null, null);
			database.close();
			return true;
		} catch (Exception e) {
			database.close();
			return false;
		}
	}
}