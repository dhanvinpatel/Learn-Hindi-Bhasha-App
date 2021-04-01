package com.bhasha.learnhindibhasha.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.bhasha.learnhindibhasha.activities.home.HomeScreenActivity;
import com.bhasha.learnhindibhasha.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation startAnimation = AnimationUtils.loadAnimation(this, R.anim.start_animation);

        ImageView rickshawTop = (ImageView) findViewById(R.id.rickshawTop);
        ImageView rickshawBottom = (ImageView) findViewById(R.id.rickshawBottom);
        ImageView lamp = (ImageView) findViewById(R.id.lampImageView);

        rickshawTop.setAnimation(startAnimation);
        rickshawTop.animate().alpha(0f).setDuration(2000);
        rickshawBottom.setAnimation(startAnimation);
        rickshawBottom.animate().alpha(0f).setDuration(2000);
        lamp.animate().alpha(1f).setDuration(2000);

        // Intent to the HomeScreenActivity after displaying the main screen for 2 seconds.
        new Handler().postDelayed(() -> {
            Intent homeScreenIntent = new Intent(this, HomeScreenActivity.class);
            startActivity(homeScreenIntent);
            finish();
        }, 2000);
    }
}