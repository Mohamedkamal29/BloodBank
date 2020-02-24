package app.p.bloodbank.Fragment.Fragment.Authorization;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import app.p.bloodbank.Activity.AuthorizationActivity;
import app.p.bloodbank.R;
import app.p.bloodbank.data.api.ApiService;
import app.p.bloodbank.data.api.RetrofitClient;
import app.p.bloodbank.data.model.resetpassword.ResetPassword;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForgetPasswordFragment extends Fragment {

    Context context;
    @BindView(R.id.Phone)
    EditText Phone;
    @BindView(R.id.btnSend)
    Button btnSend;


    public ForgetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        ButterKnife.bind(this, view);
        context = getContext();

        return view;
    }

    @OnClick(R.id.btnSend)
    public void onViewClicked() {
      phoneValidation();
        RetrofitClient.getClient().create(ApiService.class).resetPassword(Phone.getText().toString()).enqueue(new Callback<ResetPassword>() {
            @Override
            public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {
                if (response.body().getStatus()==1)
                {

                        ((AuthorizationActivity)context).SetFragment(new ConfirmPasswordFragment());
                    Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResetPassword> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void phoneValidation()
    {
        if (Phone.getText().toString().trim().isEmpty())
        {
            Phone.setError("Enter Phone Number");
        }else if (Phone.getText().toString().length()<11){
            Phone.setError("Phone number must be 11 ");
        }
    }
}
