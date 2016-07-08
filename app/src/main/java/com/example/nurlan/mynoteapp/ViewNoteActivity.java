package com.example.nurlan.mynoteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ViewNoteActivity extends AppCompatActivity {
    String note_Text;
    int dbId;
    final String LOG_TAG = "NoteLog";
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        note_Text = getIntent().getStringExtra("note_Text");
        dbId = Integer.valueOf(getIntent().getStringExtra("data_Id"));
        Log.d(LOG_TAG, "note_Text " + note_Text);
        Log.d(LOG_TAG, "dbId " + dbId);
        Log.d(LOG_TAG, "+" );


        et = (EditText) findViewById(R.id.noteedit);
        et.setText(note_Text);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_note, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_share) {

            Intent sendIntent = new Intent();

            sendIntent.setAction(Intent.ACTION_SEND);

            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");

            sendIntent.setType("text/plain");

            startActivity(sendIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickUpdeate(View view) {
        DatabaseApp db = new DatabaseApp(ViewNoteActivity.this);

        db.updateNoteById(dbId, et.getText().toString());
        finish();
    }

}
