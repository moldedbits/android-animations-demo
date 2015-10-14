package com.moldedbits.animationsdemo.scenes;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.moldedbits.animationsdemo.R;

public class SceneActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    // We transition between these Scenes
    private Scene mScene1;
    private Scene mScene2;
    private Scene mScene3;

    /** A custom TransitionManager */
    private TransitionManager mTransitionManagerForScene3;

    /** Transitions take place in this ViewGroup. We retain this for the dynamic transition on scene 4. */
    private ViewGroup mSceneRoot;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.select_scene);
        radioGroup.setOnCheckedChangeListener(this);

        mSceneRoot = (ViewGroup) findViewById(R.id.scene_root);

        // A Scene can be instantiated from a live view hierarchy.
        mScene1 = new Scene(mSceneRoot, (ViewGroup) mSceneRoot.findViewById(R.id.container));

        // You can also inflate a generate a Scene from a layout resource file.
        mScene2 = Scene.getSceneForLayout(mSceneRoot, R.layout.scene2, this);

        // Another scene from a layout resource file.
        mScene3 = Scene.getSceneForLayout(mSceneRoot, R.layout.scene3, this);

        // We create a custom TransitionManager for Scene 3, in which ChangeBounds and Fade
        // take place at the same time.
        mTransitionManagerForScene3 = TransitionInflater.from(this)
                .inflateTransitionManager(R.transition.scene3_transition_manager, mSceneRoot);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.select_scene_1: {
                // You can start an automatic transition with TransitionManager.go().
                TransitionManager.go(mScene1);
                break;
            }
            case R.id.select_scene_2: {
                TransitionManager.go(mScene2);
                break;
            }
            case R.id.select_scene_3: {
                // You can also start a transition with a custom TransitionManager.
                mTransitionManagerForScene3.transitionTo(mScene3);
                break;
            }
            case R.id.select_scene_4: {
                // Alternatively, transition can be invoked dynamically without a Scene.
                // For this, we first call TransitionManager.beginDelayedTransition().
                TransitionManager.beginDelayedTransition(mSceneRoot);
                // Then, we can just change view properties as usual.
                View square = mSceneRoot.findViewById(R.id.transition_square);
                ViewGroup.LayoutParams params = square.getLayoutParams();
                int newSize = getResources().getDimensionPixelSize(R.dimen.square_size_expanded);
                params.width = newSize;
                params.height = newSize;
                square.setLayoutParams(params);
                break;
            }
        }
    }
}
