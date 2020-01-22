package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static Database.DBEntry.cleaning;
import static Database.DBEntry.cooking;
import static Database.DBEntry.date;
import static Database.DBEntry.dishes;
import static Database.DBEntry.hygiene;
import static Database.DBEntry.laundry;
import static Database.DBEntry.other;
import static Database.DBEntry.shower;
import static Database.DBEntry.table;
import static Database.DBEntry.toilet;

public class DiaryEntryDB extends SQLiteOpenHelper {
    public static final String DB_fileName = "DiaryEntry.db";
    public static final int DB_Version = 1;
    public DiaryEntryDB(Context context) {
        super(context, DB_fileName, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS"+ table +"(" +
                date + "TEXT PRIMARY KEY NOT NULL,"+
                shower + "REAL DEFAULT 0," +
                toilet + "REAL DEFAULT 0," +
                hygiene + "REAL DEFAULT 0," +
                laundry + "REAL DEFAULT 0," +
                dishes + "REAL DEFAULT 0," +
                cooking + "REAL DEFAULT 0," +
                cleaning + "REAL DEFAULT 0," +
                other + "REAL DEFAULT 0" + ");");

        //Toast.makeText(OverviewActivity.mcontext,"Database created",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<newVersion){
            db.execSQL(DBEntry.SQL_DELETE);//drop sql string
            onCreate(db);//Create new sql database or database
        }
    }
}
