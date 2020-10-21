package com.filenber.pics_bag.model;

public class order_model {
  String No_pics;
  String Date;

  public order_model(String no_pics, String date) {
    No_pics = no_pics;
    Date = date;
  }

  public String getNo_pics() {
    return No_pics;
  }

  public void setNo_pics(String no_pics) {
    No_pics = no_pics;
  }

  public String getDate() {
    return Date;
  }

  public void setDate(String date) {
    Date = date;
  }
}
