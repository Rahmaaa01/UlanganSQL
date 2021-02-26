package com.example.noteapp_ulanganmandirisqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    // nama dan versi database
    public DBHelper (Context context) {
        super(context, "catatan.db", null, 1);
    }

    // membuat tabel catatan dan kolom-kolom untuk atribut catatan
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tb_catatan(id INTEGER PRIMARY KEY, judul TEXT, catatan TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_catatan");
    }

    // memasukkan data catatan ke SQLite
    public boolean masukkanCatatan (SetterGetterData sgd) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("judul", sgd.getJudul());
        cv.put("catatan", sgd.getCatatan());
        return db.insert("tb_catatan", null, cv) > 0;
    }

    // mendapat seluruh data dari database
    public Cursor dapatkanSemuaCatatan () {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + "tb_catatan", null);
    }

    // memperbarui catatan
    public boolean perbaruiCatatan (SetterGetterData sgd, int id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("judul", sgd.getJudul());
        cv.put("catatan", sgd.getCatatan());
        return db.update("tb_catatan", cv, "id" + "=" + id, null) > 0;
    }

    // method menghapus satu catatan
    public void hapusCatatan(int id) {
        SQLiteDatabase db = getReadableDatabase();
        db.delete("tb_catatan", "id" + "=" + id, null);
    }

    // method menghapus semua catatan
    public void hapusSemuaCatatan () {
        SQLiteDatabase db = getReadableDatabase();
        db.delete("tb_catatan", null, null);
    }
}
