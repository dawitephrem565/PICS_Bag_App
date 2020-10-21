package com.filenber.pics_bag.model;

public class Image_Model {
  String info_image;
  String info_title;
  String info_descr;

  public Image_Model(String info_image, String info_title, String info_descr) {
    this.info_image = info_image;
    this.info_title = info_title;
    this.info_descr = info_descr;
  }

  public String getInfo_image() {
    return info_image;
  }

  public void setInfo_image(String info_image) {
    this.info_image = info_image;
  }

  public String getInfo_title() {
    return info_title;
  }

  public void setInfo_title(String info_title) {
    this.info_title = info_title;
  }

  public String getInfo_descr() {
    return info_descr;
  }

  public void setInfo_descr(String info_descr) {
    this.info_descr = info_descr;
  }
}
