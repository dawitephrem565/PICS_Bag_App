package com.filenber.pics_bag;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.filenber.pics_bag.adapter.order_adapter;
import com.filenber.pics_bag.model.order_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order extends AppCompatActivity {

  private static final String URL_PRODUCTS = "http://localhost/ACA/api/api.php?op=ordered_data&phone=";
  Context context;
  View view;
  RecyclerView recyclerView;
  List<order_model> order_detail_listitems;
  order_adapter order_detail_adapter;
  Toolbar toolbar;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.order_recycle_view);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    recyclerView =(RecyclerView)findViewById(R.id.ordere_recyle_view);
    // Order_detail_adapter adapter = new Order_detail_adapter(order_detail_listitems, this);
    //recyclerView.setAdapter(order_detail_adapter);

    order_detail_listitems = new ArrayList<>();
    loadProducts();
    //order_detail_listitems.add(new Order_Detail_listitems(1,"001","002","002","002","002","002"));
    // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    order_detail_adapter = new order_adapter(order_detail_listitems,this);
    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

    recyclerView.setLayoutManager(staggeredGridLayoutManager);
    recyclerView.setHasFixedSize(true);
    //recyclerView.setAdapter(order_detail_adapter);

    // recyclerView.setAdapter(order_detail_adapter);
  }
  public  void loadProducts() {

    /*
     * Creating a String Request
     * The request type is GET defined by first parameter
     * The URL is defined in the second parameter
     * Then we have a Response Listener and a Error Listener
     * In response listener we will get the JSON response as a String
     */
    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS+SharedPrefManager.getInstance(context).saved(),
      new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
          try {
            //
//                              String val = getArguments().getString("mykey");
            //converting the string to json array object
            JSONArray array = new JSONArray(response);

            //traversing through all the object
            for (int i = 0; i < array.length(); i++) {

              //getting product object from json array
              JSONObject product = array.getJSONObject(i);

              //adding the product to product list
              order_detail_listitems.add(new order_model(
                product.getString("Num_Bag"),
                product.getString("Pics_Price")

              ));
            }

            //creating adapter object and setting it to recyclerview
            // order_detail_listitems order_detail_listitems=new
            order_adapter adapter = new order_adapter(order_detail_listitems,Order.this);
            recyclerView.setAdapter(adapter);
            //Toast.makeText(con,"Displayed",Toast.LENGTH_LONG).show();
          } catch (JSONException e) {
            e.printStackTrace();
          }

        }
      },
      new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Toast.makeText(getBaseContext(),"Some thing is error", Toast.LENGTH_LONG);

        }
      }) {
      @Override
      public Map<String, String> getParams() throws AuthFailureError {
        Map<String,String> params = new HashMap<>();
        //params.put("username",Username.getText().toString());
        String data=getIntent().getStringExtra("gname");
        params.put("user",getIntent().getStringExtra("OrderTitle") );
        return params;
      }
    } ;

    //adding our stringrequest to queue
    Volley.newRequestQueue(getBaseContext().getApplicationContext()).add(stringRequest);

  }
}
