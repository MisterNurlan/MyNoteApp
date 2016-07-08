package com.example.nurlan.mynoteapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class CreateNoteActivity extends AppCompatActivity {
    private static final String LOG_TAG = "mytable";
    DatabaseApp db;
    EditText someEditText;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onClickCancel(View view) {
        this.finish();
    }

    public void onClickSave(View view) {
        EditText someEditText = (EditText) findViewById(R.id.editText);
        String str = someEditText.getText().toString();
        DatabaseApp db = new DatabaseApp(CreateNoteActivity.this);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatedDate = df.format(c.getTime());

        double lat = 0.0;
        double longT = 0.0;
        GPSTracker gp = new GPSTracker(CreateNoteActivity.this);
        if (gp.canGetLocation()) {
            lat = gp.getLatitude();
            longT = gp.getLongitude();

            Log.d(LOG_TAG, "Lat" + String.valueOf(lat));
            Log.d(LOG_TAG, "Long" + String.valueOf(longT));
        } else {
            gp.showSettingsAlert();
        }
        String noteText = someEditText.getText().toString();
        Note note = new Note(str, formatedDate, String.valueOf(lat), String.valueOf(longT));


        db.addNote(note);


        Toast t = Toast.makeText(this, "Заметка создана", Toast.LENGTH_LONG);
        t.show();
        Note n = db.getNoteByDate(formatedDate);

        Intent intent = new Intent(this, ViewNoteActivity.class);

        intent.putExtra("note_Text", n.get_noteText());
        intent.putExtra("data_Id", n.get_noteId());

        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake

        Notification noti = new Notification.Builder(this)
                .setContentTitle("Заметка создана")
                .setContentText(noteText).setSmallIcon(R.drawable.plus)
                .setContentIntent(pIntent)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);


        finish();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreateNote Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.nurlan.mynoteapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreateNote Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.nurlan.mynoteapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
