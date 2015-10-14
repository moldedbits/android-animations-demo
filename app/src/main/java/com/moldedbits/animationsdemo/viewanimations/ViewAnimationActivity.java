package com.moldedbits.animationsdemo.viewanimations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.moldedbits.animationsdemo.R;
import com.moldedbits.animationsdemo.viewanimations.RotateActivity;

public class ViewAnimationActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {

    String[] items = new String[] {
            "Simple rotate",
            "Simple translate",
            "Parallel",
            "Sequential",
            "Cross Fade"
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
        int animationId = R.anim.rotate;
        switch (i) {
            case 0:
                animationId = R.anim.rotate;
                break;
            case 1:
                animationId = R.anim.translate;
                break;
            case 2:
                animationId = R.anim.parallel;
                break;
            case 3:
                animationId = R.anim.disco;
                break;
            case 4:
                animationId = RotateActivity.ANIM_CROSS_FADE;
        }

        Intent intent = new Intent(this, RotateActivity.class);
        intent.putExtra(RotateActivity.ANIMATION, animationId);
        startActivity(intent);
    }
}
