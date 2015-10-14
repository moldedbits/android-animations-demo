package com.moldedbits.animationsdemo.propertyanimations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.moldedbits.animationsdemo.R;

public class PropertyAnimationActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener{

    String[] items = new String[] {
            "Simple rotate",
            "Simple translate",
            "Parallel",
            "Sequential",
            "Bounce",
            "Overshoot",
            "Background",
            "Flip"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int animationType = AnimateActivity.ANIM_ROTATE;
        switch (i) {
            case 0:
                animationType = AnimateActivity.ANIM_ROTATE;
                break;
            case 1:
                animationType = AnimateActivity.ANIM_TRANSLATE;
                break;
            case 2:
                animationType = AnimateActivity.ANIM_PARALLEL;
                break;
            case 3:
                animationType = AnimateActivity.ANIM_SEQUENTIAL;
                break;
            case 4:
                animationType = AnimateActivity.ANIM_BOUNCE;
                break;
            case 5:
                animationType = AnimateActivity.ANIM_OVERSHOOT;
                break;
            case 6:
                animationType = AnimateActivity.ANIM_BACKGROUND;
                break;
            case 7:
                animationType = AnimateActivity.ANIM_FLIP;
                break;
        }

        Intent intent = new Intent(this, AnimateActivity.class);
        intent.putExtra(AnimateActivity.ANIM_TYPE, animationType);
        startActivity(intent);
    }
}
