package com.moldedbits.animationsdemo.viewanimations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.moldedbits.animationsdemo.R;

public class RotateActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ANIMATION = "animation";

    public static final int ANIM_CROSS_FADE = 1;

    private int animationId;

    private ImageView horse;
    private ImageView mountain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rotate);

        animationId = getIntent().getIntExtra(ANIMATION, R.anim.rotate);
        horse = (ImageView) findViewById(R.id.horse);
        horse.setOnClickListener(this);

        mountain = (ImageView) findViewById(R.id.mountain);
    }

    @Override
    protected void onResume() {
        super.onResume();
        horse.postDelayed(new Runnable() {
            @Override
            public void run() {
                animate();
            }
        }, 500);
    }

    private void animate() {
        if(animationId == ANIM_CROSS_FADE) {
            crossFade();
        }
        else {
            playAnimation();
        }
    }

    private void playAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, animationId);
        horse.startAnimation(animation);
    }

    private void crossFade() {
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                horse.setVisibility(View.GONE);
                mountain.setVisibility(View.VISIBLE);
                mountain.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        horse.startAnimation(fadeOut);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show();
    }
}
