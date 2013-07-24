package com.wakaguzi.kagua.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class KaguaDB {
	
	public static final String KEY_ID = "id";

	public static final String KEY_NAME = "name";

    public static final String KEY_PHONE = "phone";

    public static final String KEY_LAST_VISIT = "last_visit";
    
    public static final String KEY_IMAGE_URL = "image_url";
    
    public static final String[] TBL_HOME_COLUMNS = new String[] {KEY_ID, KEY_NAME, KEY_PHONE, KEY_LAST_VISIT,KEY_IMAGE_URL};
    
    private DatabaseHelper mDbHelper;

    private SQLiteDatabase mDb;
    
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Kagua_db";

    private static final String HOME_TABLE = "home";
    
    private static final String HOME_TABLE_CREATE = 
        	"CREATE TABLE IF NOT EXISTS  "
            + HOME_TABLE 
            + " (" + KEY_ID 
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME 
            + " VARCHAR(150) NOT NULL, " 
            + KEY_PHONE 
            + " VARCHAR(150) NOT NULL,"
            + KEY_LAST_VISIT 
            + " VARCHAR(150) NOT NULL,"
            + KEY_IMAGE_URL
            +" VARCHAR(300) NOT NULL)";
    
    private final Context mContext;
    
    private static final String TAG = "JUNIT_test_class";
    
    public KaguaDB(Context context) {
		this.mContext = context;
	}

	public KaguaDB open() throws SQLException {
		mDbHelper = new DatabaseHelper(mContext);
		mDb = mDbHelper.getWritableDatabase();

		return this;
	}

	public void close() {
		mDbHelper.close();
	}
    
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            
        }
        
        @Override
		public void onCreate(SQLiteDatabase db) {
        	Log.v("Creating Database", HOME_TABLE_CREATE);
			db.execSQL(HOME_TABLE_CREATE);
			
		}

		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
                    + " which destroys all old data");
			
			List<String> MainMenuColumns;
			db.execSQL(HOME_TABLE_CREATE);
			MainMenuColumns =  KaguaDB.getColumns(db, HOME_TABLE);
			db.execSQL("ALTER TABLE " + HOME_TABLE + " RENAME TO temp_" + HOME_TABLE);
			db.execSQL(HOME_TABLE_CREATE);
			MainMenuColumns.retainAll(KaguaDB.getColumns(db, HOME_TABLE));
			String cols = KaguaDB.join(MainMenuColumns, ",");
			db.execSQL(String.format("INSERT INTO %s (%s) SELECT %s FROM temp_%s", HOME_TABLE, cols, cols, HOME_TABLE));
			db.execSQL("DROP TABLE IF EXISTS temp_" + HOME_TABLE);
			onCreate(db);
			
		}
     }


		
		/**
	     * Credits http://goo.gl/7kOpU
	     * @param db
	     * @param tableName
	     * @return
	     */
	    public static List<String> getColumns(SQLiteDatabase db, String tableName) {
	    	Log.v("List<String>", tableName);
	        List<String> ar = null;
	        Cursor c = null;

	        try {
	            c = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 1", null);

	            if (c != null) {
	                ar = new ArrayList<String>(Arrays.asList(c.getColumnNames()));
	            }

	        } catch (Exception e) {
	            Log.v(tableName, e.getMessage(), e);
	            e.printStackTrace();
	        } finally {
	            if (c != null)
	                c.close();
	        }
	        return ar;
	    }

	    public static String join(List<String> list, String delim) {
	        StringBuilder buf = new StringBuilder();
	        int num = list.size();
	        for (int i = 0; i < num; i++) {
	            if (i != 0)
	                buf.append(delim);
	            buf.append((String)list.get(i));
	        }
	        return buf.toString();
	    }
	    
	    /**
		 * The Below code will Insert into HOME_TABLE
		 * 
		 * @param ContentValues
		 *            values
		 * @return
		 */
		public void populate_home_table(ContentValues values) {
			mDb.insertOrThrow(HOME_TABLE, null, values);

		}
		
		/**
	     * The Below code will delete row {@value}name Row from Database table   
	     * @param  KEY_NAME
	     * @return
	     */
	    public void empty_home_table(String name) {
            mDb.delete(HOME_TABLE, KEY_NAME + "="+name, null);

	    }
	    
	    
	    /**
	     * The Below code will ...chill Kiasi
	     */
	    
	    public List<String[]> selectAll()
	    {
	    List<String[]> list = new ArrayList<String[]>();
	    String sql = "SELECT  * FROM " + HOME_TABLE ;
        Cursor cursor = mDb.rawQuery(sql, null); 
	    int x=0;
	    if (cursor.moveToFirst()) {
	       do {
	        String[] b1=new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)}; 
	       // Log.v("Log214124", b1[0]+b1[1]+b1[2]+b1[3]+b1[4]);
	        list.add(b1);
	        x=x+1;
	       } while (cursor.moveToNext());
	    }
	    if (cursor != null && !cursor.isClosed()) {
	       cursor.close();
	    } 
	    cursor.close();
	    
	    return list;
	   }
   

}
