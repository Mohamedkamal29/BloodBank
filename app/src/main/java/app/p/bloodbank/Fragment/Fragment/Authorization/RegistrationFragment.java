package app.p.bloodbank.Fragment.Fragment.Authorization;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.text.format.DateFormat;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import app.p.bloodbank.Activity.MainActivity;
import app.p.bloodbank.R;

import app.p.bloodbank.data.api.ApiService;
import app.p.bloodbank.data.api.RetrofitClient;
import app.p.bloodbank.data.model.General.publiceData.DateTxt;
import app.p.bloodbank.data.model.General.publiceData.bloodTypes.BloodTypes;
import app.p.bloodbank.data.model.General.publiceData.cities.Cities;
import app.p.bloodbank.data.model.General.publiceData.governorates.Governorates;
import app.p.bloodbank.data.model.User.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationFragment extends Fragment {

    private DateTxt donationData;
    private DateTxt Bid;

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.birthDate)
    EditText birthDate;
    @BindView(R.id.bloodType)
    Spinner bloodType;
    @BindView(R.id.donateDate)
    EditText donateDate;
    @BindView(R.id.country)
    Spinner country;
    @BindView(R.id.city)
    Spinner city;
    @BindView(R.id.Phone)
    EditText phone;
    @BindView(R.id.Password)
    EditText password;
    @BindView(R.id.confirmPassword)
    EditText confirmPassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    Context context;

    private List<String> GovernoratesTxt = new ArrayList<>();
    private List<Integer> GovernoratesId = new ArrayList<>();

    private List<String> citiesTxt = new ArrayList<>();
    private List<Integer> citiesId = new ArrayList<>();

    private List<String> BloodTypesTxt = new ArrayList<>();
    private List<Integer> BloodTypesId = new ArrayList<>();

    private int city_id = 0;
    private int blood_type_id = 0;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.bind(this, view);
        context = getContext();

        DecimalFormat mFormat = new DecimalFormat("00");
        Calendar calander = Calendar.getInstance();
        String cDay = mFormat.format(Double.valueOf(String.valueOf(calander.get(Calendar.DAY_OF_MONTH))));
        String cMonth = mFormat.format(Double.valueOf(String.valueOf(calander.get(Calendar.MONTH + 1))));
        String cYear = String.valueOf(calander.get(Calendar.YEAR));

        donationData = new DateTxt("01", "01", "1990", "01-01-1990");
        Bid = new DateTxt("01", "01", "1990", "01-01-1990");


        getBloodType();
        getGovernoratess();
        //getCities();


        return view;
    }

    @OnClick({R.id.btnRegister, R.id.birthDate, R.id.donateDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                getRegister();
                break;
            case R.id.birthDate:
                showCalender(getActivity(), "birthdate", birthDate, Bid);
break;
            case R.id.donateDate:
             //   DatePicker(view);
                showCalender(getActivity(), "birthdate", donateDate, donationData);

                break;
        }
    }


    private void getBloodType() {
        RetrofitClient.getClient().create(ApiService.class).getBloodType().enqueue(new Callback<BloodTypes>() {
            @Override
            public void onResponse(Call<BloodTypes> call, Response<BloodTypes> response) {
                if (response.body().getStatus() == 1) {
                    try {
                        BloodTypesTxt = new ArrayList<>();
                        BloodTypesId = new ArrayList<>();
                        BloodTypesTxt.add("Choose blood-type");
                        BloodTypesId.add(0);

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            BloodTypesTxt.add(response.body().getData().get(i).getName());
                            BloodTypesId.add(response.body().getData().get(i).getId());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                R.layout.spinner_item, BloodTypesTxt);

                        bloodType.setAdapter(adapter);
                    } catch (Exception e) {

                    }
                }

            }

            @Override
            public void onFailure(Call<BloodTypes> call, Throwable t) {

            }
        });
    }

    private void getGovernoratess() {
        RetrofitClient.getClient().create(ApiService.class).getGovernorates().enqueue(new Callback<Governorates>() {
            @Override
            public void onResponse(Call<Governorates> call, Response<Governorates> response) {
                try {


                    if (response.body().getStatus() == 1) {
                        GovernoratesTxt = new ArrayList<>();
                        GovernoratesId = new ArrayList<>();

                        GovernoratesTxt.add("Choose Government");
                        GovernoratesId.add(0);

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            GovernoratesTxt.add(response.body().getData().get(i).getName());
                            GovernoratesId.add(response.body().getData().get(i).getId());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                R.layout.spinner_item, GovernoratesTxt);

                        country.setAdapter(adapter);

                        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (i != 0) {
                                    getCities(GovernoratesId.get(i));
                                } else {
                                    city.setVisibility(View.GONE);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }


                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Governorates> call, Throwable t) {

            }
        });
    }

    private void getCities(int i) {
        RetrofitClient.getClient().create(ApiService.class).getCities(i).enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                try {


                    if (response.body().getStatus() == 1) {

                        city.setVisibility(View.VISIBLE);

                        citiesTxt = new ArrayList<>();
                        citiesId = new ArrayList<>();

                        citiesTxt.add("Choose City");
                        citiesId.add(0);

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            citiesTxt.add(response.body().getData().get(i).getName());
                            citiesId.add(response.body().getData().get(i).getId());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                R.layout.spinner_item, citiesTxt);

                        city.setAdapter(adapter);

                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {

            }
        });
    }

    private void getRegister() {
        registrationValidation();
        RetrofitClient.getClient().create(ApiService.class).getRegister(name.getText().toString().trim(), email.getText().toString().trim(), birthDate.getText().toString().trim(), city_id, phone.getText().toString().trim(), donateDate.getText().toString().trim(), password.getText().toString().trim(), confirmPassword.getText().toString().trim(), blood_type_id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {


                    if (response.body().getStatus() == 1) {


                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();

                    } else {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registrationValidation() {

        if (name.getText().toString().trim().isEmpty()) {
            Toast.makeText(context, "Name is Required", Toast.LENGTH_SHORT).show();
            name.setError("name is required");

        } else if (email.getText().toString().trim().isEmpty()) {
            email.setError("Email Is Required");

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {
            email.setError("email Is Invalid");
            Toast.makeText(context, "Email must be in this way 'example@example.com' ", Toast.LENGTH_LONG).show();

        } else if (birthDate.getText().toString().trim().isEmpty()) {
            birthDate.setError("birthDate is required");

        } else if (donateDate.getText().toString().trim().isEmpty()) {
            donateDate.setError("Last donateDate is required");

        } else if (phone.getText().toString().length() < 11) {

            phone.setError("Invalid Phone Number");
        } else if (password.getText().toString().length() < 6) {
            password.setError("Password Is Invalid");
            Toast.makeText(context, "Password Must be more 6 character", Toast.LENGTH_LONG).show();
        } else if (confirmPassword.getText().toString().length() < 6) {
            confirmPassword.setError("confirmPassword Is Invalid");
            Toast.makeText(context, "Confirm Password Must be more 6 character", Toast.LENGTH_LONG).show();

        } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            Toast.makeText(context, "Password Must be Identical", Toast.LENGTH_LONG).show();

        } else if (BloodTypesId.size() > 0) {
            blood_type_id = BloodTypesId.get(bloodType.getSelectedItemPosition());
            if (blood_type_id == 0) {
                Toast.makeText(context, "Please choose blood type", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(context, "Please choose blood type", Toast.LENGTH_SHORT).show();
        }
        if (GovernoratesId.size() > 0) {
            if (country.getSelectedItemPosition() == 0) {
                Toast.makeText(context, "Please choose country", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(context, "Please choose country", Toast.LENGTH_SHORT).show();
        }

        if (citiesId.size() > 0) {
            city_id = citiesId.get(city.getSelectedItemPosition());

            if (city_id == 0) {
                Toast.makeText(context, "Please choose city", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(context, "Please choose city", Toast.LENGTH_SHORT).show();
        }

    }

/*
    public void DatePicker(final View v) {


        Calendar cal = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                (view, year, monthOfYear, dayOfMonth) -> {
                    String birthSelectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                    String donateSelectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" +year ;

                    switch (v.getId()) {
                        case R.id.birthDate:
                            ((EditText) v).setText(birthSelectedDate);
                            break;
                        case R.id.donateDate:
                            ((EditText) v).setText(donateSelectedDate);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setTitle("sdas");
        datePickerDialog.show();

    }*/

    public static void showCalender(Context context, String title, final TextView text_view_data, final DateTxt data1) {
        DatePickerDialog mDatePicker = new DatePickerDialog(context, AlertDialog.THEME_HOLO_DARK, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                DecimalFormat mFormat = new DecimalFormat("00", symbols);
                String data = selectedYear + "-" + mFormat.format(Double.valueOf((selectedMonth + 1))) + "-" + mFormat.format(Double.valueOf(selectedDay));
                data1.setDate_txt(data);
                data1.setDay(mFormat.format(Double.valueOf(selectedDay)));
                data1.setMonth(mFormat.format(Double.valueOf(selectedMonth + 1)));
                data1.setYear(String.valueOf(selectedYear));
                text_view_data.setText(data);
            }
        }, Integer.parseInt(data1.getYear()), Integer.parseInt(data1.getMonth()) - 1, Integer.parseInt(data1.getDay()));
        mDatePicker.setTitle(title);
        mDatePicker.show();
    }

    public static Date convertDateString(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date parse = format.parse(date);

            return parse;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DateTxt convertStringToDateTxtModel(String date) {
        try {
            Date date1 = convertDateString(date);
            String day = (String) DateFormat.format("dd", date1); // 20
            String monthNumber = (String) DateFormat.format("MM", date1); // 06
            String year = (String) DateFormat.format("yyyy", date1); // 2013

            return new DateTxt(day, monthNumber, year, date);

        } catch (Exception e) {
            return null;
        }}



}











