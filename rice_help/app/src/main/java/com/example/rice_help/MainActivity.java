package com.example.rice_help;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int duration;

    private ImageView logo_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        duration = 2000;

        logo_img = findViewById(R.id.logo_img);

        animate_logo();
    }

    private void animate_logo(){

        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(logo_img, "scaleX", 1.25f);
        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(logo_img, "scaleY", 1.25f);
        scaleUpX.setDuration(duration);
        scaleUpY.setDuration(duration);

        AnimatorSet scaleUp = new AnimatorSet();
        scaleUp.play(scaleUpX).with(scaleUpY);

        scaleUp.start();

        scaleUp.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                // ...
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // ...
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                to_main();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // ...
            }
        });


    }

    private void to_main(){
        Intent i = new Intent(MainActivity.this, Main.class);
        startActivity(i);
        finish();
    }
}