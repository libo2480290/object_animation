package com.example.libo.Object_animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.libo.annimation.R;

public class MainActivity extends AppCompatActivity {


    private Button button;
    private AnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        button = (Button) findViewById(R.id.btn_start);
        animationView = (AnimationView) findViewById(R.id.animation_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.animateX();
            }
        });
    }
}
