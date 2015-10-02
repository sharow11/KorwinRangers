package com.korwin.rangers.korwinrangers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

public class MainPage extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        DatabaseHandler db = new DatabaseHandler(this);

        db.getWritableDatabase();

        java.util.Date date = new java.util.Date();
        Event ev = new Event("Test", "Test Description opis blablabla", "https://www.youtube.com/", new Timestamp(date.getTime()));
        db.addEvent(ev);

        lv = (ListView) findViewById(R.id.listView);

        List<Event> events = db.getAllEvents();

        EventAdapter arrayAdapter = new EventAdapter(
                this,
                new ArrayList<Event>(events)
        );
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(((Event) lv.getItemAtPosition(position)).getLink()));
                startActivity(browserIntent);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
