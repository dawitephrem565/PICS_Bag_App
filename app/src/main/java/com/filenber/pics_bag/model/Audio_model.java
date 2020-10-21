package com.filenber.pics_bag.model;

public class Audio_model {
  String audio_info;
  String audio_path;

  public Audio_model(String audio_info, String audio_path) {
    this.audio_info = audio_info;
    this.audio_path = audio_path;
  }

  public String getAudio_info() {
    return audio_info;
  }

  public void setAudio_info(String audio_info) {
    this.audio_info = audio_info;
  }

  public String getAudio_path() {
    return audio_path;
  }

  public void setAudio_path(String audio_path) {
    this.audio_path = audio_path;
  }
}
