package app.p.bloodbank.data.api;

import app.p.bloodbank.data.model.General.publiceData.bloodTypes.BloodTypes;
import app.p.bloodbank.data.model.General.publiceData.cities.Cities;
import app.p.bloodbank.data.model.General.publiceData.governorates.Governorates;
import app.p.bloodbank.data.model.General.publiceData.setting.Setting;
import app.p.bloodbank.data.model.User.User;
import app.p.bloodbank.data.model.Post.Post;
import app.p.bloodbank.data.model.Profile.getprofile.GetProfile;
import app.p.bloodbank.data.model.resetpassword.ResetPassword;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("login")
    @FormUrlEncoded
    Call<User>getLogin(
            @Field("phone") String phone
            ,@Field("password") String password

    );


    @POST("signup")
    @FormUrlEncoded
    Call<User>getRegister(

      @Field("name")String name,
      @Field("email")String email,
      @Field("birth_date") String birth_date,
      @Field("city_id")int city_id,
      @Field("phone")String phone,
      @Field("donation_last_date")String donation_last_date,
      @Field("password") String password,
      @Field("password_confirmation")String password_confirmation,
      @Field("blood_type_id")int blood_type_id
    );


    @POST("reset-password")
    @FormUrlEncoded
    Call<ResetPassword>resetPassword(@Field("phone")String phone);


    @POST("profile")
    @FormUrlEncoded
    Call<GetProfile>getProfileData ();

    @GET("blood-types")
    Call<BloodTypes>getBloodType();

    @GET("governorates")
    Call<Governorates> getGovernorates();

    @GET("cities")
    Call<Cities> getCities(@Query("governorate_id") int governorate_id);

    @GET("settings")
   Call<Setting>getSetting(@Header("Authorization") String api_token);

    @GET("posts")
    Call<Post> getPosts(
            @Header("Authorization") String token,
            @Query("page") int page);
}


