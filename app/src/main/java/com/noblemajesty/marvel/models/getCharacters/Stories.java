
package com.noblemajesty.marvel.models.getCharacters;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stories implements Parcelable
{

  @SerializedName("available")
  @Expose
  private Integer available;
  @SerializedName("collectionURI")
  @Expose
  private String collectionURI;
  @SerializedName("items")
  @Expose
  private List<StoriesItem> items = null;
  @SerializedName("returned")
  @Expose
  private Integer returned;
  public final static Parcelable.Creator<Stories> CREATOR = new Creator<Stories>() {


    @SuppressWarnings({
        "unchecked"
    })
    public Stories createFromParcel(Parcel in) {
      return new Stories(in);
    }

    public Stories[] newArray(int size) {
      return (new Stories[size]);
    }

  };

  protected Stories(Parcel in) {
    this.available = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.collectionURI = ((String) in.readValue((String.class.getClassLoader())));
    in.readList(this.items, (StoriesItem.class.getClassLoader()));
    this.returned = ((Integer) in.readValue((Integer.class.getClassLoader())));
  }

  public Stories() {
  }

  public Integer getAvailable() {
    return available;
  }

  public void setAvailable(Integer available) {
    this.available = available;
  }

  public String getCollectionURI() {
    return collectionURI;
  }

  public List<StoriesItem> getItems() {
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
