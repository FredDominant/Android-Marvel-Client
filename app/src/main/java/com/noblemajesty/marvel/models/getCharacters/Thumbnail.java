
package com.noblemajesty.marvel.models.getCharacters;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnail implements Parcelable
{

  @SerializedName("path")
  @Expose
  private String path;
  @SerializedName("extension")
  @Expose
  private String extension;
  public final static Parcelable.Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {


    @SuppressWarnings({
        "unchecked"
    })
    public Thumbnail createFromParcel(Parcel in) {
      return new Thumbnail(in);
    }

    public Thumbnail[] newArray(int size) {
      return (new Thumbnail[size]);
    }

  };

  protected Thumbnail(Parcel in) {
    this.path = ((String) in.readValue((String.class.getClassLoader())));
    this.extension = ((String) in.readValue((String.class.getClassLoader())));
  }

  public Thumbnail() {
  }

  public String getPath() {
    return path;
  }

  public String getExtension() {
    return extension;
  }


  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(path);
    dest.writeValue(extension);
  }

  public int describeContents() {
    return 0;
  }

}