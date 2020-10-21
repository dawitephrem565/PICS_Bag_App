package com.filenber.pics_bag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.filenber.pics_bag.adapter.Video_Adapter;
import com.filenber.pics_bag.model.Video_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Video_Activity extends AppCompatActivity {
  ListView video_recycleView;
  Vector<Video_model> youtubevideo = new Vector<>();
  ArrayList<Video_model> videolist;
  ProgressDialog progressDialog;
  Video_Adapter video_adapter;

  private static final String URL_PRODUCTS = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLxZHs0Jed3jTx8L0x9uDh-E4EeTAkh3HT&key=AIzaSyC2i_s_CbMcp4Cokv_Ru0ZGdg6pnAvNcjo";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.video_recycle_view);
    //Fabric.with(this, new Crashlytics());
    video_recycleView  = (ListView)findViewById(R.id.video_recycle_view);
    videolist = new ArrayList<>();
    video_adapter = new Video_Adapter(Video_Activity.this,Video_Activity.this,videolist);


    video_recycleView.setAdapter(video_adapter);

    // video_recycleView.setAdapter(video_adapter);
    progressDialog= new ProgressDialog(this);
    progressDialog.setTitle("Please Wait");
    progressDialog.setMessage("Video Time Opening Now");
    progressDialog.show();
    new Dave().execute();
  }

  class Dave extends AsyncTask<String,String,Void>
  {
    @Override
    protected Void doInBackground(String... strings) {
      StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            try {
              JSONObject jsonObject = new JSONObject(response);
              JSONArray jsonArray = jsonObject.getJSONArray("items");
              for (int i=0; i<jsonArray.length(); i++)
              {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                JSONObject jsonsnippet= jsonObject1.getJSONObject("snippet");
                JSONObject jsonVideoId=jsonsnippet.getJSONObject("resourceId");
                JSONObject jsonObjectdefault = jsonsnippet.getJSONObject("thumbnails").getJSONObject("medium");
                String video_id = jsonVideoId.getString("videoId");
                Video_model funny_list = new Video_model();
                // funny_list.setVideo_link(video_id);
                funny_list.setVideo_title(jsonsnippet.getString("title"));
                funny_list.setVideo_desc(jsonsnippet.getString("description"));
                funny_list.setVideo_image(jsonObjectdefault.getString("url"));
                funny_list.setVideo_link(video_id);
                //Toast.makeText(Funny_Video_Activity.this, video_id.toString(), Toast.LENGTH_SHORT).show();
                videolist.add(funny_list);
                progressDialog.dismiss();

              }
              video_recycleView.setAdapter(video_adapter);
              video_adapter.notifyDataSetChanged();


            } catch (JSONException e) {
              e.printStackTrace();
              Toast.makeText(Video_Activity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }

          }
        },
        new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            Toast.makeText(Video_Activity.this,"Some thing is error",Toast.LENGTH_LONG);
          }
        }) {
        @Override
        public Map<String, String> getParams() throws AuthFailureError {
          Map<String,String> params = new HashMap<>();

          return params;
        }
      } ;

      //adding our stringrequest to queue
      Volley.newRequestQueue(getBaseContext().getApplicationContext()).add(stringRequest);
      return null;
    }


  }
}
