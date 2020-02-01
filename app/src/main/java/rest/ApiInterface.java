package rest;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


     @POST("login_money.php")
     @FormUrlEncoded
     Call<JsonObject>getLoginDetails(@Field("email_id") String email_id,
                                     @Field("password") String password);

    @POST("update_amount.php")
    @FormUrlEncoded
    Call<JsonObject>saveAmount(@Field("email_id") String email_id,
                                    @Field("token") String password, @Field("amount") String amount);

    @POST("update_password.php")
    @FormUrlEncoded
    Call<JsonObject>updatePassword(@Field("email_id") String email_id,
                               @Field("token") String token, @Field("password") String password);

    @POST("send_email_money.php")
    @FormUrlEncoded
    Call<JsonObject>sendEmail(@Field("email_id") String email_id,
                                   @Field("token") String token,@Field("type_text") String type_text,
                              @Field("amount") String amount,@Field("redeem_amount") String redeem_amount);

    @POST("send_paytm_email.php")
    @FormUrlEncoded
    Call<JsonObject>sendPaytm(@Field("email_id") String email_id,
                              @Field("token") String token,@Field("type_text") String type_text,
                              @Field("amount") String amount,@Field("redeem_amount") String redeem_amount,
                              @Field("mobile") String mobile);

}

