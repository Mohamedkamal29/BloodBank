package app.p.bloodbank.Fragment.Fragment.BottomNavigation.HomeFragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import app.p.bloodbank.Adapter.ViewPagerAdapter;
import app.p.bloodbank.Fragment.Fragment.BaseFragment;
import app.p.bloodbank.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment {


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    Context context;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        context = getContext();

        setupViewPager(viewPager);
        tablayout.setupWithViewPager(viewPager);




        return view;
    }


    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());


        viewPagerAdapter.addFragment(new PostFragment(), getString(R.string.Posts));
        viewPagerAdapter.addFragment(new DonateFragment(), getString(R.string.Donate));
        viewPager.setAdapter(viewPagerAdapter);

    }


}
