package com.moldedbits.animationsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.moldedbits.animationsdemo.propertyanimations.PropertyAnimationActivity;
import com.moldedbits.animationsdemo.scenes.SceneActivity;
import com.moldedbits.animationsdemo.viewanimations.ViewAnimationActivity;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {

    String[] items = new String[] {
            "View Animation",
            "Property Animation",
            "Scene Transitions"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = null;
        switch (i) {
            case 0:
                intent = new Intent(this, ViewAnimationActivity.class);
                break;
            case 1:
                intent = new Intent(this, PropertyAnimationActivity.class);
                break;
            case 2:
                intent = new Intent(this, SceneActivity.class);
                break;
        }
        startActivity(intent);
    }
}
