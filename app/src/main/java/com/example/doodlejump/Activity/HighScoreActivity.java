package com.example.doodlejump.Activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doodlejump.GameComponents.Database;
import com.example.doodlejump.R;

import java.util.ArrayList;

public class HighScoreActivity extends Activity {

    ListView listView;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.high_score);

        database = new Database(this);
        listView = (ListView) findViewById(R.id.listview);


        ArrayList<String> arrayList = new ArrayList<>();
        Cursor data = database.getListContests();

        if(data.getCount() == 0){
            Toast.makeText(this,"Database is Empty", Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                arrayList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(listAdapter);
            }
        }

    }

    public void resetScore(View view){
      this.database.reset();
      this.recreate();
    }
}
