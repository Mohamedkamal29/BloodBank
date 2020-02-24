package app.p.bloodbank.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import app.p.bloodbank.Fragment.Fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity {
   public BaseFragment baseFragment;


public void onBackPressed()
{
baseFragment.onBack();
}

    public void superBackPressed(){
    super.onBackPressed();
    }
}
