package com.example.nurlan.mynoteapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nurlan on 05.03.2016.
 */
public class ListAdapter extends BaseAdapter implements OnClickListener {

    final String LOG_TAG = "NoteLog";
    private Activity activity;
    private String[] data;
    private ArrayList<Note> rData = new ArrayList<Note>();
    private static LayoutInflater inflater = null;
    private Context mContext;


    public ListAdapter(Activity a, ArrayList<Note> rD,
                       Context context) {
        this.mContext = context;
        activity = a;
        rData = rD;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public int getCount() {

        return rData.size();

    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {

    }

    // Create a holder Class to contain inflated xml file elements /
    public static class ViewHolder {

        public TextView text;
        public TextView text1;
        public ImageView image;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            // Inflate tabitem.xml file for each row ( Defined below ) */
            vi = inflater.inflate(R.layout.list_view_row, null);

            // View Holder Object to contain tabitem.xml file elements /

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.text);
            holder.text1 = (TextView) vi.findViewById(R.id.text1);
            holder.image = (ImageView) vi.findViewById(R.id.image);

            // Set holder with LayoutInflater /
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();
        Note item = rData.get(position);
        Context context = parent.getContext();

        holder.text.setText(item.get_noteText());
      /*  if (item.get_noteText().length() <= 15) {

        }
        else {
            holder.text.setText(item.get_noteText().substring(0, 15) + " ...");
        }*/
        holder.text1.setText(item.get_noteDate());

        // Set Item Click Listner for LayoutInflater for each row ***/
        vi.setOnClickListener(new OnItemClickListener(position, item.get_noteText(), item.get_noteId()));
        return vi;
    }

    // when Item click in ListView ***/
    private class OnItemClickListener implements OnClickListener {
        private int mPosition;
        private String note_t;
        private int dbIdl;

        OnItemClickListener(int position, String note_text, int databaseId) {
            mPosition = position;
            note_t = note_text;
            dbIdl = databaseId;
        }

        @Override
        public void onClick(View arg0) {
            Intent myIntent = new Intent(mContext, ViewNoteActivity.class);
            myIntent.putExtra("mPosition", String.valueOf(mPosition));
            myIntent.putExtra("note_Text", note_t);
            myIntent.putExtra("data_Id", String.valueOf(dbIdl));


            Log.d(LOG_TAG, "mPosition " + mPosition);
            Log.d(LOG_TAG, "note_text " + note_t);
            Log.d(LOG_TAG, "note_db_id " + dbIdl);


            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            mContext.startActivity(myIntent);


        }
    }
}
