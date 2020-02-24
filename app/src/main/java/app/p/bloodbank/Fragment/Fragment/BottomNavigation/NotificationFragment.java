package app.p.bloodbank.Fragment.Fragment.BottomNavigation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.p.bloodbank.Fragment.Fragment.BaseFragment;
import app.p.bloodbank.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment {


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

}
