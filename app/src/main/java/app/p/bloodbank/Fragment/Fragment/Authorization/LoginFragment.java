package app.p.bloodbank.Fragment.Fragment.Authorization;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;

import app.p.bloodbank.Activity.AuthorizationActivity;
import app.p.bloodbank.Activity.MainActivity;
import app.p.bloodbank.R;
import app.p.bloodbank.SharedPrefManger;
import app.p.bloodbank.data.api.ApiService;
import app.p.bloodbank.data.api.RetrofitClient;
import app.p.bloodbank.data.model.User.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {


    @BindView(R.id.Phone)
    EditText Phone;
    @BindView(R.id.Password)
    EditText Password;
    @BindView(R.id.checkbox)
    AppCompatCheckBox checkbox;
    @BindView(R.id.forgetPassword)
    TextView forgetPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvNotHaveAccount)
    TextView tvNotHaveAccount;
    @BindView(R.id.tvRegisterNow)
    TextView tvRegisterNow;

    Context context;
    SharedPrefManger sharedPrefManger;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        context = getContext();
        sharedPrefManger = new SharedPrefManger(context);



        return view;
    }


    @OnClick({R.id.forgetPassword, R.id.btnLogin, R.id.tvRegisterNow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forgetPassword:
                ((AuthorizationActivity)context).SetFragment(new ForgetPasswordFragment());
                break;
            case R.id.btnLogin:
                    Login();
                    break;
            case R.id.tvRegisterNow:
                ((AuthorizationActivity)context).SetFragment(new RegistrationFragment());
                break;
        }
    }



private void   loginValidation()
{
    if(Phone.getText().toString().trim().isEmpty()&&Password.getText().toString().trim().isEmpty())
    {
        Toast.makeText(context, "All Fields Are Required!", Toast.LENGTH_LONG).show();
        Phone.setError("Phone Is Required!");
        Password.setError("Password Is Required");
    }else if(Phone.getText().toString().trim().isEmpty()|| Phone.getText().toString().length()<11)
    {
        Phone.setError("Invalid Phone Number");
    }else if (Password.getText().toString().trim().isEmpty()||Password.getText().toString().length()<6)
    {
        Password.setError("Password Is Invalid");
        Toast.makeText(context, "Password Must be more 6 character", Toast.LENGTH_LONG).show();
    }

}


public void Login()
{

    loginValidation();
    RetrofitClient.getClient().create(ApiService.class).getLogin(Phone.getText().toString(),Password.getText().toString()).enqueue(new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {

            if (response.body().getStatus()==1) {
                sharedPrefManger.setLoginStatus(true);
                sharedPrefManger.setUserData(response.body().getData());

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
}
}
