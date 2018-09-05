
package com.noblemajesty.marvel.models.getCharacters;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventItem implements Parcelable
{

  @SerializedName("resourceURI")
  @Expose
  private String resourceURI;
  @SerializedName("name")
  @Expose
  private String name;
  public final static Parcelable.Creator<EventItem> CREATOR = new Creator<EventItem>() {


    @SuppressWarnings({
        "unchecked"
    })
    public EventItem createFromParcel(Parcel in) {
      return new EventItem(in);
    }

    public EventItem[] newArray(int size) {
      return (new EventItem[size]);
    }

  }
      ;

  protected EventItem(Parcel in) {
    this.resourceURI = ((String) in.readValue((String.class.getClassLoader())));
    this.name = ((String) in.readValue((String.class.getClassLoader())));
  }

  public EventItem() {
  }

  public String getResourceURI() {
    return resourceURI;
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
