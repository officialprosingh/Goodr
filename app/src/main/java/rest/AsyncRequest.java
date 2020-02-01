package rest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;


public class AsyncRequest extends AsyncTask<String, Integer, String> {
    OnAsyncRequestComplete caller;
    Context context;
    String method = "GET";
    ContentValues parameters = null;
    ProgressDialog pDialog = null;
    boolean is_push_to_sale = false;

    // Three Constructors****
    public AsyncRequest(Activity a, String m, ContentValues p) {
        caller = (OnAsyncRequestComplete) a;
        context = a;
        method = m;
        parameters = p;


    }

    public AsyncRequest(Activity a, String m, ContentValues p, boolean push_to_sale) {
        caller = (OnAsyncRequestComplete) a;
        context = a;
        method = m;
        parameters = p;
        this.is_push_to_sale = push_to_sale;

    }

    public AsyncRequest(Activity a, String m) {
        caller = (OnAsyncRequestComplete) a;
        context = a;
        method = m;
    }

    public AsyncRequest(Context a, String m) {
        caller = (OnAsyncRequestComplete) a;
        context = a;
        method = m;
    }

    public AsyncRequest(Activity a) {
        caller = (OnAsyncRequestComplete) a;
        context = a;
    }

    public String doInBackground(String... urls) {
        // get url pointing to entry point of API
        String address = urls[0].toString();
        if (method == "POST") {


            return post(address);
        }

        if (method == "GET") {
            return get(address);
        }


        if (method.equalsIgnoreCase("PUT")) {
            return put(address);
        }
        return null;
    }

    public void onPreExecute() {
        pDialog = new ProgressDialog(context);
        pDialog.setCancelable(false);
        pDialog.setMessage("Please wait...");
        pDialog.show();
    }

    public void onProgressUpdate(Integer... progress) {
        // you can implement some progressBar and update it in this record
        // setProgressPercent(progress[0]);
    }

    public void onPostExecute(String response) {
        if (!((Activity) context).isFinishing()) {
            try {
                if ((this.pDialog != null) && this.pDialog.isShowing()) {
                    this.pDialog.dismiss();
                }

            } catch (IllegalArgumentException e) {
            } catch (Exception e) {
            } finally {
                this.pDialog = null;
            }
        }
        caller.asyncResponse(response);
    }

    protected void onCancelled(String response) {

        try {
            if ((this.pDialog != null) && this.pDialog.isShowing()) {
                this.pDialog.dismiss();
            }

        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
        } finally {
            this.pDialog = null;
        }
        caller.asyncResponse(response);
    }

    @SuppressWarnings("deprecation")
    private String get(String address) {

        try {

            if (parameters != null) {
                Log.e("app", "parameters is----" + parameters.toString());
                String query = "";
                String EQ = "=";
                String AMP = "&";


                for (Map.Entry<String, Object> param : parameters.valueSet()) {
                    query += param.getKey() + EQ + URLEncoder.encode(param.getValue().toString()) + AMP;
                }

                if (query != "") {
                    address += "?" + query;
                }

            }


            InputStream is = null;


            try {
                URL url = new URL(address);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //  conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(60000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();

                is = conn.getInputStream();

                // Convert the InputStream into a string
                String contentAsString = readIt(is);


                return contentAsString;

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                }
            }
            // return //stringifyResponse(contentAsString);

        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

        return null;
    }

    private String post(String address) {
        try {
            URL obj = new URL(address);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();


            con.setRequestMethod("POST");



            String query = "";
            if (parameters != null) {

                String EQ = "=";
                String AMP = "&";
                for (Map.Entry<String, Object> param : parameters.valueSet()) {
                    query += param.getKey() + EQ + URLEncoder.encode(param.getValue().toString()) + AMP;
                }

            }

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(query);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            System.out.println(response.toString());
            return response.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }


        return null;
    }

    private String put(String address) {

        try {
            URL obj = new URL(address);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("PUT");


            String query = "";
            if (parameters != null) {

                String EQ = "=";
                String AMP = "&";
                for (Map.Entry<String, Object> param : parameters.valueSet()) {
                    query += param.getKey() + EQ + URLEncoder.encode(param.getValue().toString()) + AMP;
                }
                /*if (query != "") {
                    address += "?" + query;
                }*/
            }


            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(query);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'PUT' request to URL : " + address);
            System.out.println("Post parameters : " + query);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);

            }
            in.close();

            //print result
            System.out.println(response.toString());
            return response.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }


        return null;
    }


    // Interface to be implemented by calling activity
    public interface OnAsyncRequestComplete {
        public void asyncResponse(String response);

    }

    public String readIt(InputStream stream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return total.toString();
    }
}