package com.filenber.pics_bag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
  public static String URL_PRODUCTS ="http://localhost/ACA/api/api.php?op=";
  String total_pics_value = "";
  TextView total_pics,sold_pics,pics_on_stock,pics_price_value ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        load_total_pics();
        load_sold_pics();
        load_pics_onstock();
        load_pics_current_price();
    }

    public  void load_total_pics()
    {
      StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS + "gettotalpics",
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            //pDialog.dismiss();

            try {
              JSONObject object     = new JSONObject(response);
              JSONArray jsonArray   = object.getJSONArray("totalpics");
              JSONObject jsonObject = jsonArray.getJSONObject(0);

// fetch password from JSON
               total_pics_value         = jsonObject.getString("total");
               total_pics.setText(total_pics_value);
// use password in textviewfemail.setText(password, TextView.BufferType.EDITABLE);
            //  pas.setText(password, TextView.BufferType.EDITABLE);



            }
            catch (JSONException e) {
              //Toast.makeText(ForgotPasswordActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }


          }

        },

        new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
          }
        }) {
        @Override
        protected Map<String, String> getParams() {
          Map<String, String> params = new HashMap<String, String>();
          //params.put("username",Username.getText().toString());

          //params.put("user", SharedPrefManager.getInstance(getContext()).getUsername());;

          return params;
        }
      };
      RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
      requestQueue.add(stringRequest);

    }
  public  void load_sold_pics()
  {
    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
      new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
          //pDialog.dismiss();

          try {
            JSONObject object     = new JSONObject(response);
            JSONArray jsonArray   = object.getJSONArray("totalpics");
            JSONObject jsonObject = jsonArray.getJSONObject(0);

// fetch password from JSON
            total_pics_value         = jsonObject.getString("total");
            total_pics.setText(total_pics_value);
// use password in textviewfemail.setText(password, TextView.BufferType.EDITABLE);
            //  pas.setText(password, TextView.BufferType.EDITABLE);



          }
          catch (JSONException e) {
            //Toast.makeText(ForgotPasswordActivity.this, e.toString(), Toast.LENGTH_LONG).show();
          }


        }

      },

      new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
        }
      }) {
      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        //params.put("username",Username.getText().toString());

        //params.put("user", SharedPrefManager.getInstance(getContext()).getUsername());;

        return params;
      }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
    requestQueue.add(stringRequest);

  }
  public  void load_pics_onstock()
  {
    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
      new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
          //pDialog.dismiss();

          try {
            JSONObject object     = new JSONObject(response);
            JSONArray jsonArray   = object.getJSONArray("totalpics");
            JSONObject jsonObject = jsonArray.getJSONObject(0);

// fetch password from JSON
            total_pics_value         = jsonObject.getString("total");
            total_pics.setText(total_pics_value);
// use password in textviewfemail.setText(password, TextView.BufferType.EDITABLE);
            //  pas.setText(password, TextView.BufferType.EDITABLE);



          }
          catch (JSONException e) {
            //Toast.makeText(ForgotPasswordActivity.this, e.toString(), Toast.LENGTH_LONG).show();
          }


        }

      },

      new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
        }
      }) {
      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        //params.put("username",Username.getText().toString());

        //params.put("user", SharedPrefManager.getInstance(getContext()).getUsername());;

        return params;
      }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
    requestQueue.add(stringRequest);

  }
  public  void load_pics_current_price()
  {
    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
      new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
          //pDialog.dismiss();

          try {
            JSONObject object     = new JSONObject(response);
            JSONArray jsonArray   = object.getJSONArray("totalpics");
            JSONObject jsonObject = jsonArray.getJSONObject(0);

// fetch password from JSON
            total_pics_value         = jsonObject.getString("total");
            total_pics.setText(total_pics_value);
// use password in textviewfemail.setText(password, TextView.BufferType.EDITABLE);
            //  pas.setText(password, TextView.BufferType.EDITABLE);



          }
          catch (JSONException e) {
            //Toast.makeText(ForgotPasswordActivity.this, e.toString(), Toast.LENGTH_LONG).show();
          }


        }

      },

      new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
        }
      }) {
      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        //params.put("username",Username.getText().toString());

        //params.put("user", SharedPrefManager.getInstance(getContext()).getUsername());;

        return params;
      }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
    requestQueue.add(stringRequest);

  }
    public void init()
    {

      total_pics=findViewById(R.id.total_pics_value);
      sold_pics=findViewById(R.id.sold_pics_value);
      pics_on_stock=findViewById(R.id.pics_on_stock);
      pics_price_value=findViewById(R.id.pics_price_value);
    }

}


