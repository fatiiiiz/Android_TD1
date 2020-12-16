package agourram.insa.firstapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    public static final String PRODUIT_KEY = "id";
    public static final String PRODUIT_NOM = "produit";

    public static final String PRODUIT_TABLE_CREATE =
            "CREATE TABLE PRODUIT (" +
                    PRODUIT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PRODUIT_NOM + " TEXT);";

    public DataBaseHandler(Context context) {
        super(context, "PRODUIT", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PRODUIT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(String s) {
        Log.i("JFL"," Insert in database");
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(PRODUIT_NOM, s);
        db.insertOrThrow("PRODUIT",null, values);
        db.setTransactionSuccessful();
        db.endTransaction();

    }

    public void readData(){
        Log.i("JFL", "Reading database...");
        String select = new String("SELECT * from PRODUIT");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Log.i("JFL", "Number of entries: " + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();do {
                Log.i("JFL", "Reading: " + cursor.getString(cursor.getColumnIndex(PRODUIT_NOM)));
            }
            while (cursor.moveToNext());
        }
    }
}
