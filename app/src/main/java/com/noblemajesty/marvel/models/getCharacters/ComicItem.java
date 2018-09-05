
package com.noblemajesty.marvel.models.getCharacters;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComicItem implements Parcelable
{

  @SerializedName("resourceURI")
  @Expose
  private String resourceURI;
  @SerializedName("name")
  @Expose
  private String name;
  public final static Parcelable.Creator<ComicItem> CREATOR = new Creator<ComicItem>() {


    @SuppressWarnings({
        "unchecked"
    })
    public ComicItem createFromParcel(Parcel in) {
      return new ComicItem(in);
    }

    public ComicItem[] newArray(int size) {
      return (new ComicItem[size]);
    }

  };

  protected ComicItem(Parcel in) {
    this.resourceURI = ((String) in.readValue((String.class.getClassLoader())));
    this.name = ((String) in.readValue((String.class.getClassLoader())));
  }

  public ComicItem() {
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public void setResourceURI(String resourceURI) {
    this.resourceURI = resourceURI;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(resourceURI);
    dest.writeValue(name);
  }

  public int describeContents() {
    return 0;
  }

}
