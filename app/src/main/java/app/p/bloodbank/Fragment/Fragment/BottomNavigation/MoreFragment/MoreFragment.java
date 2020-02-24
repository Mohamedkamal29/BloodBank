package app.p.bloodbank.Fragment.Fragment.BottomNavigation.MoreFragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import app.p.bloodbank.Activity.AuthorizationActivity;
import app.p.bloodbank.Activity.MainActivity;
import app.p.bloodbank.Fragment.Fragment.Authorization.ConfirmPasswordFragment;
import app.p.bloodbank.Fragment.Fragment.BaseFragment;
import app.p.bloodbank.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MoreFragment extends BaseFragment {
    Context context;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtFavourite)
    TextView txtFavourite;
    @BindView(R.id.txtContact)
    TextView txtContact;
    @BindView(R.id.txtAboutUs)
    TextView txtAboutUs;
    @BindView(R.id.txtRate)
    TextView txtRate;
    @BindView(R.id.txtNotification)
    TextView txtNotification;
    @BindView(R.id.txtExit)
    TextView txtExit;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbarTitle.setText("Others");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);


        return view;
    }


    @OnClick({R.id.txtFavourite, R.id.txtContact, R.id.txtAboutUs, R.id.txtRate, R.id.txtNotification, R.id.txtExit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtFavourite:
                break;
            case R.id.txtContact:
                ((MainActivity)context).loadFragment(new ContactUsFragment());

                break;
            case R.id.txtAboutUs:
                ((MainActivity)context).loadFragment(new AboutAppFragment());
                break;
            case R.id.txtRate:
                break;
            case R.id.txtNotification:
                break;
            case R.id.txtExit:
                break;
        }
    }
}
