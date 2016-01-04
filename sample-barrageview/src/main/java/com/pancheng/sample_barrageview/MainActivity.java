package com.pancheng.sample_barrageview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recylcerView);
        LinearLayoutManager customLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(customLayoutManager);

        ArrayList<String> dataIn = new ArrayList<>();
        dataIn.add("This is barrage 0.");
        dataIn.add("This is barrage 1.");
        dataIn.add("This is barrage 2.");
        dataIn.add("This is barrage 3.");
        dataIn.add("This is barrage 4.");

        ArrayList<String> urlIn = new ArrayList<>();
        urlIn.add("http://q4.qlogo.cn/g?b=qq&k=EwibKzqbDjO6k1N16CPje7A&s=40&t=1449038671");
        urlIn.add("http://q3.qlogo.cn/g?b=qq&k=X8eB06bQ72OANFwVkwBJeg&s=40&t=1380088051");
        urlIn.add("http://q4.qlogo.cn/g?b=qq&k=EwibKzqbDjO6k1N16CPje7A&s=40&t=1449038671");
        urlIn.add("http://q3.qlogo.cn/g?b=qq&k=X8eB06bQ72OANFwVkwBJeg&s=40&t=1380088051");
        urlIn.add("http://q3.qlogo.cn/g?b=qq&k=X8eB06bQ72OANFwVkwBJeg&s=40&t=1380088051");

        MyAdapter myAdapter = new MyAdapter(dataIn,urlIn);
        recyclerView.setAdapter(myAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
