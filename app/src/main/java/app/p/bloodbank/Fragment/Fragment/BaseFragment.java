package app.p.bloodbank.Fragment.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.p.bloodbank.Activity.BaseActivity;
import app.p.bloodbank.Activity.MainActivity;
import app.p.bloodbank.Fragment.Fragment.BottomNavigation.HomeFragment.HomeFragment;
import app.p.bloodbank.R;


public class BaseFragment extends Fragment {

   public BaseActivity baseActivity;

   public void setUpActivity(){
       baseActivity = (BaseActivity) getActivity();
       baseActivity.baseFragment= this;
   }
   public void onBack(){
       baseActivity.superBackPressed();

   }

   public void onStart(){
       super.onStart();
       setUpActivity();
   }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setUpActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
