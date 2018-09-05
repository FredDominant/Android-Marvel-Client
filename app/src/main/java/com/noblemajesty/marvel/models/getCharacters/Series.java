
package com.noblemajesty.marvel.models.getCharacters;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Series implements Parcelable
{

  @SerializedName("available")
  @Expose
  private Integer available;
  @SerializedName("collectionURI")
  @Expose
  private String collectionURI;
  @SerializedName("items")
  @Expose
  private List<SeriesItem> items = null;
  @SerializedName("returned")
  @Expose
  private Integer returned;
  public final static Parcelable.Creator<Series> CREATOR = new Creator<Series>() {


    @SuppressWarnings({
        "unchecked"
    })
    public Series createFromParcel(Parcel in) {
      return new Series(in);
    }

    public Series[] newArray(int size) {
      return (new Series[size]);
    }

  };

  protected Series(Parcel in) {
    this.available = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.collectionURI = ((String) in.readValue((String.class.getClassLoader())));
    in.readList(this.items, (SeriesItem.class.getClassLoader()));
    this.returned = ((Integer) in.readValue((Integer.class.getClassLoader())));
  }

  public Series() {
  }

  public Integer getAvailable() {
    return available;
  }


  public String getCollectionURI() {
    return collectionURI;
  }

  public List<SeriesItem> getItems() {
    return items;
  }

  public Integer getReturned() {
    return returned;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(available);
    dest.writeValue(collectionURI);
    dest.writeList(items);
    dest.writeValue(returned);
  }

  public int describeContents() {
    return 0;
  }

}
