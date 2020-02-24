package app.p.bloodbank.Fragment.Fragment.IntroFragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import app.p.bloodbank.Activity.AuthorizationActivity;
import app.p.bloodbank.Adapter.IntroViewPagerAdapter;
import app.p.bloodbank.Fragment.Fragment.Authorization.LoginFragment;
import app.p.bloodbank.R;
import app.p.bloodbank.ScreenItem;
import app.p.bloodbank.SharedPrefManger;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;


public class IntroFragment extends Fragment {


    @BindView(R.id.screen_viewpager)
    ViewPager screenPager;
    @BindView(R.id.btn_next)
    ImageView btnNext;
    @BindView(R.id.tab_indicator)
    TabLayout tabIndicator;
    @BindView(R.id.btn_get_started)
    Button btnGetStarted;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    int position = 0;
    IntroViewPagerAdapter introViewPagerAdapter;
    Animation btnAnim;
    Context context;
    final List<ScreenItem> mList = new ArrayList<>();

    public IntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_intro, container, false);
        ButterKnife.bind(this, view);
        context = getContext();


     if (restorePrefData()) {

            ((AuthorizationActivity) context).SetFragment(new LoginFragment());
        }


        btnAnim = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.button_animation);


        mList.add(new ScreenItem("Blood Bank", "This app help users to find doner of particular Blood Group and it could filter results based on location. The doners can register themselves and provide their contact details.", R.drawable.first_slider_background));
        mList.add(new ScreenItem("Blood Bank", "This app help users to find doner of particular Blood Group and it could filter results based on location. The doners can register themselves and provide their contact details.", R.drawable.first_slider_background));
        mList.add(new ScreenItem("Blood Bank", "This app help users to find doner of particular Blood Group and it could filter results based on location. The doners can register themselves and provide their contact details.", R.drawable.second_slider_background));

        // setup viewpager
        introViewPagerAdapter = new IntroViewPagerAdapter(context, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager

        tabIndicator.setupWithViewPager(screenPager);
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener()  {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size() - 1) {

                    loaddLastScreen();

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    @OnClick({R.id.btn_next,R.id.btn_get_started, R.id.tv_skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);


                }

                if (position == mList.size()-1) { // when we rech to the last screen

                    // TODO : show the GETSTARTED Button and hide the indicator and the next button

                    loaddLastScreen();


                }


                break;

            case R.id.btn_get_started:
                ((AuthorizationActivity)context).SetFragment(new LoginFragment());
               savePrefsData();

                break;
            case R.id.tv_skip:
                screenPager.setCurrentItem(mList.size());
                break;
        }
    }



    private boolean restorePrefData() {


        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;



    }

    private void savePrefsData() {

        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();


    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
        btnGetStarted.setAnimation(btnAnim);



    }

}
