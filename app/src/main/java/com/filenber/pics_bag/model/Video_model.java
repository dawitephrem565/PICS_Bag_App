package com.filenber.pics_bag.model;

public class Video_model {
  String video_title;
  String Video_link;
  String Video_desc;
  String Video_image;

  public Video_model() {
  }
  public Video_model(String video_title, String video_link, String video_desc, String video_image) {
    this.video_title = video_title;
    Video_link = video_link;
    Video_desc = video_desc;
    Video_image = video_image;
  }

  public String getVideo_title() {
    return video_title;
  }

  public void setVideo_title(String video_title) {
    this.video_title = video_title;
  }

  public String getVideo_link() {
    return Video_link;
  }

  public void setVideo_link(String video_link) {
    Video_link = video_link;
  }

  public String getVideo_desc() {
    return Video_desc;
  }

  public void setVideo_desc(String video_desc) {
    Video_desc = video_desc;
  }

  public String getVideo_image() {
    return Video_image;
  }

  public void setVideo_image(String video_image) {
    Video_image = video_image;
  }
}
