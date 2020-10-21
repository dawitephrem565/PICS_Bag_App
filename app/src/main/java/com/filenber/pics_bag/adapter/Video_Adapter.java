package com.filenber.pics_bag.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.filenber.pics_bag.R;
import com.filenber.pics_bag.Video_Activity;
import com.filenber.pics_bag.Video_Display;
import com.filenber.pics_bag.model.Video_model;

import java.util.ArrayList;

public class Video_Adapter extends BaseAdapter {
  Activity activity;
  ArrayList<Video_model> funny_video_itemsArrayList;
  LayoutInflater inflater;
  Context context;
  public Video_Adapter(Context context, Video_Activity funny_video_activity, ArrayList<Video_model> videolist) {
    this.activity = funny_video_activity;
    this.funny_video_itemsArrayList=videolist;
    this.context=context;
  }

  @Override
  public int getCount() {
    return  this.funny_video_itemsArrayList.size();
  }

  @Override
  public Object getItem(int position) {
    return this.funny_video_itemsArrayList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return (long) position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if(inflater == null)
    {
      inflater = this.activity.getLayoutInflater();
    }
    if (convertView==null)
    {
      convertView = inflater.inflate(R.layout.video_custom,null);
    }
    ImageView imageView = (ImageView)convertView.findViewById(R.id.video_image);
    TextView video_title = (TextView)convertView.findViewById(R.id.video_title);
    TextView video_desc = (TextView)convertView.findViewById(R.id.video_desc);
    CardView cardbox = (CardView)convertView.findViewById(R.id.funny_card_box);

    final Video_model funny_video_items_list = (Video_model) this.funny_video_itemsArrayList.get(position);
    Glide.with(context ).load(funny_video_items_list.getVideo_image()).into(imageView);
    //  Picasso.get().load(funny_video_items_list.getVideo_image()).into(imageView);
    video_title.setText(funny_video_items_list.getVideo_title());
    video_desc.setText(funny_video_items_list.getVideo_desc());
    cardbox.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent=new Intent(context, Video_Display.class);
        intent.putExtra("videoId",funny_video_items_list.getVideo_link().toString());
        v.getContext().startActivity(intent);
      }
    });

    return convertView;
  }}
