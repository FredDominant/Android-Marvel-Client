package com.noblemajesty.marvel.models.getCharacters;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url implements Parcelable
{

  private String type;
  @SerializedName("url")
  @Expose
  private String url;
  public final static Parcelable.Creator<Url> CREATOR = new Creator<Url>() {


    @SuppressWarnings({
        "unchecked"
    })
    public Url createFromParcel(Parcel in) {
      return new Url(in);
    }

    public Url[] newArray(int size) {
      return (new Url[size]);
    }

  };

  protected Url(Parcel in) {
    this.type = ((String) in.readValue((String.class.getClassLoader())));
    this.url = ((String) in.readValue((String.class.getClassLoader())));
  }

  public Url() {
  }

  public String getUrl() {
    return url;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(type);
    dest.writeValue(url);
  }

  public int describeContents() {
    return 0;
  }

}