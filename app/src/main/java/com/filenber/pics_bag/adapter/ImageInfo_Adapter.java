package com.filenber.pics_bag.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.filenber.pics_bag.R;
import com.filenber.pics_bag.model.Image_Model;
import com.filenber.pics_bag.model.order_model;

import java.util.List;

public class ImageInfo_Adapter  extends RecyclerView.Adapter<ImageInfo_Adapter.ViewHolder> {
  List<Image_Model> adapter_order_list_items;
  Context context;

  public ImageInfo_Adapter(List<Image_Model> order_list_items, Context context) {
    this.adapter_order_list_items = order_list_items;
    this.context = context;
  }


  @Override
  public ImageInfo_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.image_info_custom, null);
    return new ImageInfo_Adapter.ViewHolder(view);

  }

  @Override
  public void onBindViewHolder(ImageInfo_Adapter.ViewHolder holder, int position) {
    final Image_Model order_list_items = adapter_order_list_items.get(position);
    // RequestOptions requestOptions= new RequestOptions().placeholder(R.drawable.logo).error(R.drawable.ic_refresh_black_24dp);

    //holder.ordered_pics.setText(order_list_items.getInfo_image());
    //holder.ordered_pics.setText(order_list_items.getInfo_title());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        /*Intent intent = new Intent();
        intent.putExtra("MainImage",order_list_items.getImage());
        intent.putExtra("OrderTitle",order_list_items.getTitle());
        intent.setClass(context,OrderActivity.class);
        context.startActivity(intent);*/
      }
    });
  }

  @Override
  public int getItemCount() {
    return adapter_order_list_items.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView ordered_pics, ordered_date;

    public ViewHolder(View itemView) {
      super(itemView);
      ordered_pics = (TextView) itemView.findViewById(R.id.num_of_pics);
      ordered_date = (TextView) itemView.findViewById(R.id.Date_value);
    }
  }
}
