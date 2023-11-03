package br.feevale.projetopdc;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class ThreatSQLiteDatabase {
    Context ctx;
    public static final String DATABASE_NAME = "threats.db";
    public static final Integer DATABASE_VERSION = 13;
    private SQLiteDatabase db;

    public ThreatSQLiteDatabase(Context ctx){
        this.ctx = ctx;
        db = new SQLiteDatabaseHelper().getWritableDatabase();
    }

    public static class ThreatsTable implements BaseColumns {
        public static final String TABLE_NAME = "threats";
        public static final String COLUMN_ENDERECO = "adress";
        public static final String COLUMN_DATA = "date";
        public static final String COLUMN_DESCRICAO = "description";

        public static String getSQL(){
            String sql = "CREATE TABLE " + TABLE_NAME + " ("+
                    _ID                 + " INTEGER PRIMARY KEY, " +
                    COLUMN_ENDERECO     + " TEXT, " +
                    COLUMN_DATA         + " TEXT, " +
                    COLUMN_DESCRICAO    + " TEXT)";
            return sql;
        }
    }

    public Long addThreat(Threat s){
        ContentValues values = new ContentValues();
        values.put(ThreatsTable.COLUMN_ENDERECO, s.getAdress());
        values.put(ThreatsTable.COLUMN_DATA, s.getDate());
        values.put(ThreatsTable.COLUMN_DESCRICAO, s.getDescription());

        return db.insert(ThreatsTable.TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public Threat getThreat(Long id){
        String cols[] = {ThreatsTable._ID, ThreatsTable.COLUMN_ENDERECO, ThreatsTable.COLUMN_DATA, ThreatsTable.COLUMN_DESCRICAO};
        String args[] = {id.toString()};
        Cursor cursor = db.query(ThreatsTable.TABLE_NAME, cols, ThreatsTable._ID+"=?", args, null, null, ThreatsTable._ID);

        if(cursor.getCount() != 1){
            return null;
        }

        cursor.moveToNext();
        Threat s = new Threat();
        s.setId(cursor.getLong(cursor.getColumnIndex(ThreatsTable._ID)));
        s.setAdress(cursor.getString(cursor.getColumnIndex(ThreatsTable.COLUMN_ENDERECO)));
        s.setDate(cursor.getString(cursor.getColumnIndex(ThreatsTable.COLUMN_DATA)));
        s.setDescription(cursor.getString(cursor.getColumnIndex(ThreatsTable.COLUMN_DESCRICAO)));

        return s;
    }

    @SuppressLint("Range")
    public List<Threat> getThreats(){
        String cols[] = {ThreatsTable._ID, ThreatsTable.COLUMN_ENDERECO, ThreatsTable.COLUMN_DATA, ThreatsTable.COLUMN_DESCRICAO};
        Cursor cursor = db.query(ThreatsTable.TABLE_NAME, cols, null, null, null, null, ThreatsTable.COLUMN_ENDERECO);
        List<Threat> threats = new ArrayList<>();
        Threat s;

        while(cursor.moveToNext()){
            s = new Threat();
            s.setId(cursor.getLong(cursor.getColumnIndex(ThreatsTable._ID)));
            s.setAdress(cursor.getString(cursor.getColumnIndex(ThreatsTable.COLUMN_ENDERECO)));
            s.setDate(cursor.getString(cursor.getColumnIndex(ThreatsTable.COLUMN_DATA)));
            s.setDescription(cursor.getString(cursor.getColumnIndex(ThreatsTable.COLUMN_DESCRICAO)));
            threats.add(s);
        }

        return threats;
    }

    public Integer removeThreat(Threat s){
        String args[] = {s.getId().toString()};
        return db.delete(ThreatsTable.TABLE_NAME, ThreatsTable._ID + "=?", args);
    }

    public Integer updateThreat(Threat s){
        String args[] = {s.getId().toString()};
        ContentValues values = new ContentValues();
        values.put(ThreatsTable.COLUMN_ENDERECO, s.getAdress());
        values.put(ThreatsTable.COLUMN_DATA, s.getDate());
        values.put(ThreatsTable.COLUMN_DESCRICAO, s.getDescription());
        return db.update(ThreatsTable.TABLE_NAME, values, ThreatsTable._ID + "=?", args);
    }

    private class SQLiteDatabaseHelper extends SQLiteOpenHelper {
        public SQLiteDatabaseHelper() {
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ThreatsTable.getSQL());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ThreatsTable.TABLE_NAME);
            onCreate(db);
        }
    }
}
