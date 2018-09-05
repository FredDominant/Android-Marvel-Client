
package com.noblemajesty.marvel.models.getCharacters;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Events implements Parcelable
{

  @SerializedName("available")
  @Expose
  private Integer available;
  @SerializedName("collectionURI")
  @Expose
  private String collectionURI;
  @SerializedName("items")
  @Expose
  private List<EventItem> items = null;
  @SerializedName("returned")
  @Expose
  private Integer returned;
  public final static Parcelable.Creator<Events> CREATOR = new Creator<Events>() {


    @SuppressWarnings({
        "unchecked"
    })
    public Events createFromParcel(Parcel in) {
      return new Events(in);
    }

    public Events[] newArray(int size) {
      return (new Events[size]);
    }

  };

  protected Events(Parcel in) {
    this.available = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.collectionURI = ((String) in.readValue((String.class.getClassLoader())));
    in.readList(this.items, (EventItem.class.getClassLoader()));
    this.returned = ((Integer) in.readValue((Integer.class.getClassLoader())));
  }

  public Events() {
  }

  public Integer getAvailable() {
    return available;
  }

  public String getCollectionURI() {
    return collectionURI;
  }

  public List<EventItem> getItems() {
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