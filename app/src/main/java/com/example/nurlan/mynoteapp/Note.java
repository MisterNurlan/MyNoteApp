package com.example.nurlan.mynoteapp;

/**
 * Created by Nurlan on 01.03.2016.
 */
public class Note {
    public Note(String str, String formatedDate) {

    }


    int _noteId;
    String _noteText;
    String _noteDate;
    String _noteLat;
    String _noteLong;




    public Note(int noteId, String noteText, String noteDate, String noteLat, String _noteLong) {
        this._noteId = noteId;
        this._noteText = noteText;
        this._noteDate = noteDate;
        this._noteLat = noteLat;
        this._noteLong = _noteLong;
    }

    public Note(String noteText, String noteDate, String noteLat, String _noteLong) {

        this._noteText = noteText;
        this._noteDate = noteDate;
        this._noteLat = noteLat;
        this._noteLong = _noteLong;
    }

    public Note() {

    }

    public int get_noteId() {
        return _noteId;
    }

    public void set_noteId(int _noteId) {
        this._noteId = _noteId;
    }

    public String get_noteText() {
        return _noteText;
    }

    public void set_noteText(String _noteText) {
        this._noteText = _noteText;
    }

    public String get_noteDate() {
        return _noteDate;
    }

    public void set_noteDate(String _noteDate) {
        this._noteDate = _noteDate;
    }




    public String get_noteLat() {
        return _noteLat;
    }

    public void set_noteLat(String _noteLat) {
        this._noteLat = _noteLat;
    }

    public String get_noteLong() {
        return _noteLong;
    }

    public void set_noteLong(String _noteLong) {
        this._noteLong = _noteLong;
    }



    @Override
    public String toString() {
        return "Note{" +
                "_noteId=" + _noteId +
                ", _noteText='" + _noteText + '\'' +
                ", _noteDate='" + _noteDate + '\'' +
                ", _noteLat='" + _noteLat + '\'' +
                ", _noteLong='" + _noteLong + '\'' +
                '}';
    }


}
