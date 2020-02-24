package app.p.bloodbank.Fragment.Fragment.BottomNavigation.MoreFragment;


import android.content.Context;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import app.p.bloodbank.Activity.MainActivity;

import app.p.bloodbank.R;
import app.p.bloodbank.SharedPrefManger;
import app.p.bloodbank.data.api.ApiService;
import app.p.bloodbank.data.api.RetrofitClient;
import app.p.bloodbank.data.model.General.publiceData.setting.Setting;
import app.p.bloodbank.data.model.User.Data;
import app.p.bloodbank.data.model.User.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AboutAppFragment extends Fragment {

    Context context;
    @BindView(R.id.aboutApp)
    TextView aboutApp;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

private User user;
    private SharedPrefManger sharedPrefManger;

    public AboutAppFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_app, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        sharedPrefManger = new SharedPrefManger(context);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbarTitle.setText("About App");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ((MainActivity)context).loadFragment(new MoreFragment());


    }
});
getSetting();
        return view;
    }

private void getSetting()
{


    RetrofitClient.getClient().create(ApiService.class).getSetting(user.getData().getApiToken())
            .enqueue(new Callback<Setting>() {
        @Override
        public void onResponse(Call<Setting> call, Response<Setting> response) {

            if (response.body().getStatus()==1)
            {
aboutApp.setText(response.body().getData().getAboutApp());
            }else {
                Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<Setting> call, Throwable t) {
            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

        }
    });
}

}
