package com.example.nurlan.mynoteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Nurlan on 01.03.2016.
 */
public class DatabaseApp extends SQLiteOpenHelper {

    public DatabaseApp(Context context) {
        super(context, "mybd", null, 8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("CreateDB", "создание базы данных");
        db.execSQL("create table MyFirstTable(id integer primary key autoincrement, note text, date text, lat text , long text );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("note", note.get_noteText());
        cv.put("date", note.get_noteDate());
        cv.put("lat", note.get_noteLat());
        cv.put("long", note.get_noteLong());
        db.insert("MyFirstTable", null, cv);
        Log.d("Add note", note.toString());
        db.close();
    }

    public List<Note> getAllNotes() {

        List<Note> noteList = new ArrayList<Note>();

        String selectQuery = "SELECT * FROM MyFirstTable ORDER BY id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.set_noteId(Integer.valueOf(cursor.getString(0)));
                note.set_noteText(cursor.getString(1));
                note.set_noteDate(cursor.getString(2));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        db.close();
        return noteList;
    }


    public void updateNoteById(int id, String new_note_text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("note", new_note_text);
        String where = "id=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.update("MyFirstTable", cv, where, whereArgs);
        Log.d("Update", "Note: " + new_note_text.toString());
        db.close();
    }


    public Note getNoteByDate(String formatedDate) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("MyFirstTable", new String[]{"id", "note", "date",}, "date" + "=?",
                new String[]{String.valueOf(formatedDate)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        Note note = new Note(Integer.parseInt(cursor
                .getString(0)), cursor.getString(1), cursor.getString(2), null, null);
        db.close();
        return note;
    }
}
