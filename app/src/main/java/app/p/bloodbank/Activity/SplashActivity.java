package app.p.bloodbank.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import app.p.bloodbank.R;
import app.p.bloodbank.SharedPrefManger;

public class SplashActivity extends AppCompatActivity {

    private final int splash_duration = 3500;
SharedPrefManger sharedPrefManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
sharedPrefManger= new SharedPrefManger(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             /*  Intent intent = new Intent(SplashActivity.this, AuthorizationActivity.class);
                startActivity(intent);
                finish();*/

              if (sharedPrefManger.getLoginStatus())
              {
                  Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                  startActivity(intent);
                  finish();
              }else {
                  Intent intent = new Intent(SplashActivity.this, AuthorizationActivity.class);
                  startActivity(intent);
                  finish();
              }

            }
        }, splash_duration);
    }


}

