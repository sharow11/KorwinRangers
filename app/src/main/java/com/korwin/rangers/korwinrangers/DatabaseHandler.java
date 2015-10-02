package com.korwin.rangers.korwinrangers;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Sharow on 2015-10-01.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "KorwinRangers";

    // Events table name
    private static final String TABLE_EVENTS = "Events";

    private static final String KEY_ID = "Id";
    private static final String KEY_TITLE = "Title";
    private static final String KEY_DESC = "Description";
    private static final String KEY_LINK = "Link";
    private static final String KEY_DATE = "Date";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE =  "CREATE TABLE " + TABLE_EVENTS +
                "(  " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TITLE + " TEXT, " + KEY_DESC + "	TEXT, " + KEY_LINK +	" TEXT, " + KEY_DATE + " DATETIME )";
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

        // Create tables again
        onCreate(db);
    }

    // Adding new Event
    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, event.getTitle());
        values.put(KEY_DESC, event.getDescription());
        values.put(KEY_LINK, event.getLink());
        values.put(KEY_DATE, String.valueOf(event.getDate()));

        // Inserting Row
        db.insert(TABLE_EVENTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single Event
    public Event getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EVENTS, new String[] { KEY_ID,
                        KEY_TITLE, KEY_DESC, KEY_LINK, KEY_DATE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(cursor.getString(4));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Event event = new Event(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), new Timestamp(parsedDate.getTime()));
        return event;
    }

    // Getting All Events
    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<Event>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setID(Integer.parseInt(cursor.getString(0)));
                event.setTitle(cursor.getString(1));
                event.setDescription(cursor.getString(2));
                event.setLink(cursor.getString(3));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = null;
                try {
                    parsedDate = dateFormat.parse(cursor.getString(4));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                event.setDate(new Timestamp(parsedDate.getTime()));
                // Adding contact to list

                eventList.add(event);
            } while (cursor.moveToNext());
        }

        // return contact list
        return eventList;
    }
}
