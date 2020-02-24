package app.p.bloodbank.Fragment.Fragment.BottomNavigation.MoreFragment;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import app.p.bloodbank.Activity.MainActivity;
import app.p.bloodbank.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ContactUsFragment extends Fragment {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.facebookImg)
    ImageView facebookImg;
    @BindView(R.id.instgramImg)
    ImageView instgramImg;
    @BindView(R.id.twitterImg)
    ImageView twitterImg;
    @BindView(R.id.youtubeImg)
    ImageView youtubeImg;
    @BindView(R.id.etTitle_Message)
    EditText etTitleMessage;
    @BindView(R.id.etMessage)
    EditText etMessage;
    @BindView(R.id.btnSave)
    Button btnSave;

    Context context;
    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ButterKnife.bind(this, view);
        context = getContext();

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbarTitle.setText("Contact Us");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).loadFragment(new MoreFragment());


            }
        });
        return view;
    }

    @OnClick({R.id.facebookImg, R.id.instgramImg, R.id.twitterImg, R.id.youtubeImg, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.facebookImg:
                Intent facebookIntent = getOpenFacebookIntent(context);
                startActivity(facebookIntent);
                break;
            case R.id.instgramImg:
                newFacebookIntent(context.getPackageManager(),"https://www.facebook.com/ipda3tech");
                break;
            case R.id.twitterImg:
                break;
            case R.id.youtubeImg:
                break;
            case R.id.btnSave:
                break;
        }
    }



        public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);

        }



    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://facewebmodal/f?href=")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/ipda3tech/")); //catches and opens a url to the desired page
        }


    }
}
