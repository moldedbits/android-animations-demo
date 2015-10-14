package com.moldedbits.animationsdemo.propertyanimations;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.moldedbits.animationsdemo.R;

public class AnimateActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ANIM_TYPE = "anim_type";

    public static final int ANIM_ROTATE = 0;
    public static final int ANIM_TRANSLATE = 1;
    public static final int ANIM_PARALLEL = 2;
    public static final int ANIM_SEQUENTIAL = 3;
    public static final int ANIM_BOUNCE = 4;
    public static final int ANIM_OVERSHOOT = 5;
    public static final int ANIM_BACKGROUND = 6;
    public static final int ANIM_FLIP = 7;

    private int anim;

    private ImageView horse;
    private ImageView mountain;
    private View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rotate);

        anim = getIntent().getIntExtra(ANIM_TYPE, ANIM_ROTATE);
        horse = (ImageView) findViewById(R.id.horse);
        horse.setOnClickListener(this);

        mountain = (ImageView) findViewById(R.id.mountain);

        root = findViewById(R.id.root);
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
        switch (anim) {
            case ANIM_ROTATE:
                rotate();
                break;
            case ANIM_TRANSLATE:
                translate();
                break;
            case ANIM_PARALLEL:
                parallel();
                break;
            case ANIM_SEQUENTIAL:
                sequential();
                break;
            case ANIM_BOUNCE:
                bounce();
                break;
            case ANIM_OVERSHOOT:
                overshoot();
                break;
            case ANIM_BACKGROUND:
                bg();
                break;
            case ANIM_FLIP:
                flip();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void rotate() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(horse, "rotation", 0, 180, 0);
        animator.setDuration(2500);
        animator.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void translate() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(horse, "translationX", 0, 180);
        animator.setDuration(1500);
        animator.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void parallel() {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(horse, "rotation", 0, 360);
        rotate.setDuration(1500);
        ObjectAnimator translateX = ObjectAnimator.ofFloat(horse, "translationX", 0, 150);
        translateX.setDuration(1500);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(horse, "translationY", 0, 150);
        translateY.setDuration(1500);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(horse, "scaleX", 1, 0.5f);
        scaleX.setDuration(1500);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(horse, "scaleY", 1, 1.5f);
        scaleY.setDuration(1500);

        AnimatorSet parallel = new AnimatorSet();
        parallel.playTogether(rotate, translateX, translateY, scaleX, scaleY);
        parallel.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void sequential() {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(horse, "rotation", 0, 360);
        rotate.setDuration(1000);
        ObjectAnimator translateX = ObjectAnimator.ofFloat(horse, "translationX", 0, 150);
        translateX.setDuration(1000);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(horse, "translationY", 0, 150);
        translateY.setDuration(1000);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(horse, "scaleX", 1, 0.5f);
        scaleX.setDuration(1000);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(horse, "scaleY", 1, 1.5f);
        scaleY.setDuration(1000);

        AnimatorSet sequential = new AnimatorSet();
        sequential.playSequentially(rotate, translateX, translateY, scaleX, scaleY);
        sequential.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void bounce() {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(horse, "rotation", 0, 360);
        rotate.setDuration(2500);
        rotate.setInterpolator(new BounceInterpolator());
        rotate.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void overshoot() {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(horse, "rotation", 0, 360);
        rotate.setDuration(2500);
        rotate.setInterpolator(new BounceInterpolator());

        ObjectAnimator translate = ObjectAnimator.ofFloat(horse, "translationY", 0, 200);
        translate.setDuration(1000);
        translate.setInterpolator(new OvershootInterpolator());

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(translate, rotate);
        set.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void bg() {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(horse, "rotation", 0, 360);
        rotate.setDuration(2500);
        rotate.setInterpolator(new BounceInterpolator());

        ObjectAnimator translate = ObjectAnimator.ofFloat(horse, "translationY", 0, 200);
        translate.setDuration(1000);
        translate.setInterpolator(new OvershootInterpolator());

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), Color.TRANSPARENT, Color.GREEN, Color.YELLOW);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                root.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.setDuration(3500);

        AnimatorSet objects = new AnimatorSet();
        objects.playSequentially(rotate, translate);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(colorAnimation, objects);
        set.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void flip() {
        AnimatorSet cardFlipLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.card_flip_left_in);
        cardFlipLeftIn.setTarget(mountain);

        AnimatorSet cardFlipLeftOut = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.card_flip_left_out);
        cardFlipLeftOut.setTarget(horse);
        cardFlipLeftOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mountain.setVisibility(View.VISIBLE);
            }
        });

        AnimatorSet cardFlipRightIn = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.card_flip_right_in);
        cardFlipRightIn.setTarget(mountain);

        cardFlipLeftIn.start();
        cardFlipLeftOut.start();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show();
    }
}
