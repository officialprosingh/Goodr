package rest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.MODE_PRIVATE;

public class Constants {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String amount = "amountkey";
    public static final String Email = "emailKey";
    public static final String user_id = "user_idKey";
    public static final String token = "tokenKey";
    public static final String password = "passwordKey";
    public static final String is_login = "is_login";
    public static final String code = "codekey";


    public static final int survey_amount=40;
    public static final int video_amount=1;
    



    public static final String url = "http://moneyrewards.co.in/";




    public static String getToken(Context context){
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String token = prefs.getString(Constants.token, null);
        return token;
    }
    public static String getEmail(Context context){
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String token = prefs.getString(Constants.Email, null);
        return token;
    }
    public static String getAmount(Context context){
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String token = prefs.getString(Constants.amount, null);
        return token;
    }

    public static String getIsLogin(Context context){
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String client_id = prefs.getString(Constants.is_login, null);
        return client_id;
    }

    public static String getPassword(Context context){
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String client_id = prefs.getString(Constants.password, null);
        return client_id;
    }

    public static String getCode(Context context){
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String client_id = prefs.getString(Constants.code, null);
        return client_id;
    }

    public static boolean isNetworkAvailable(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isAvailable = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isAvailable && mContext instanceof Activity) {
            // Toast.makeText(mContext, mContext.getString(R.string.no_network), Toast.LENGTH_LONG).show();
        }

        return isAvailable;
    }

}
