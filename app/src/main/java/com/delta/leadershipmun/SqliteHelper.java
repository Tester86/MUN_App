package com.delta.leadershipmun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SqliteHelper extends SQLiteOpenHelper {
    public SqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table images(id integer primary key, img blob not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists images");
    }

    public boolean insertImage(String path, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            FileInputStream fs = new FileInputStream(path);
            byte[] binaryImage = new byte[fs.available()];

            ContentValues contentValues = new ContentValues();
            contentValues.put("id", id);
            contentValues.put("img", binaryImage);

            db.insert("images", null, contentValues);

            fs.close();
            return true;
        } catch(FileNotFoundException e){
            e.printStackTrace();
            return false;
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public Bitmap getImage(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap bitmap = null;
        Cursor cursor = db.rawQuery("select * from images where id=?", new String[]{String.valueOf(id)});

        if(cursor.moveToNext()){
            byte[] image = cursor.getBlob(1);
            bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        }

        return bitmap;
    }
}