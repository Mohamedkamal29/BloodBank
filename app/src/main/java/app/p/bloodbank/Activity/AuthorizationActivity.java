package app.p.bloodbank.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import app.p.bloodbank.Fragment.Fragment.IntroFragment.IntroFragment;
import app.p.bloodbank.R;
import app.p.bloodbank.SharedPrefManger;

public class AuthorizationActivity extends AppCompatActivity {
    FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        SetFragment(new IntroFragment());
        manager = getSupportFragmentManager();

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }


    public void SetFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.introContainer, fragment)
                .commit();
    }

    public void ReplaceFragment(Fragment fragment, String previous) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.introContainer, fragment);
        ft.addToBackStack(previous);
        ft.commit();
    }
}
