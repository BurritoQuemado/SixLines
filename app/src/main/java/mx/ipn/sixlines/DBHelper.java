package mx.ipn.sixlines;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String projectsSQL = "CREATE TABLE IF NOT EXISTS proyectos(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, leader TEXT, teacher TEXT, beginD TEXT, endD TEXT)";
        String activitiesSQL = "CREATE TABLE IF NOT EXISTS activities(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, project TEXT , user TEXT, endD TEXT)";
        String userSQL = "CREATE TABLE IF NOT EXISTS users(_id INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, type TEXT, name TEXT, lname TEXT, email TEXT, password TEXT, bday TEXT, state TEXT, school TEXT, career TEXT, username TEXT);";
        db.execSQL(userSQL);
        db.execSQL(projectsSQL);
        db.execSQL(activitiesSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
