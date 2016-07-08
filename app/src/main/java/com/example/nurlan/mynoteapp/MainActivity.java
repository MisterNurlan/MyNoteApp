package com.example.nurlan.mynoteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.about) {
            Intent it = new Intent(this,AboutAppActivity.class);
            startActivity(it);
            return true;
        }
        if (id == R.id.creatnote) {
            Intent it = new Intent(this,CreateNoteActivity.class);
            startActivity(it);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();

        adapter = new ListAdapter(MainActivity.this, getData(), MainActivity.this.getApplicationContext());

        getSupportActionBar().setTitle("Заметки (" +String.valueOf(adapter.getCount()) + ")");

        ListView lvNotes = (ListView) findViewById(R.id.listView);
        lvNotes.setAdapter(adapter);
    }

    public ArrayList<Note> getData() {

        DatabaseApp db = new DatabaseApp(MainActivity.this.getApplicationContext());
        final ArrayList<Note> stringItems = new ArrayList<Note>();

        ArrayList<Note> pr = (ArrayList<Note>) db.getAllNotes();

        for (Note p : pr) {
            stringItems.add(p);
        }

        return stringItems;

    }
}
