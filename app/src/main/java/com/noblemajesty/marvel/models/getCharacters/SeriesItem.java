
package com.noblemajesty.marvel.models.getCharacters;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeriesItem implements Parcelable
{

  @SerializedName("resourceURI")
  @Expose
  private String resourceURI;
  @SerializedName("name")
  @Expose
  private String name;
  public final static Parcelable.Creator<SeriesItem> CREATOR = new Creator<SeriesItem>() {


    @SuppressWarnings({
        "unchecked"
    })
    public SeriesItem createFromParcel(Parcel in) {
      return new SeriesItem(in);
    }

    public SeriesItem[] newArray(int size) {
      return (new SeriesItem[size]);
    }

  };

  protected SeriesItem(Parcel in) {
    this.resourceURI = ((String) in.readValue((String.class.getClassLoader())));
    this.name = ((String) in.readValue((String.class.getClassLoader())));
  }

  public SeriesItem() {
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public String getName() {
    return name;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(resourceURI);
    dest.writeValue(name);
  }

  public int describeContents() {
    return 0;
  }

}
