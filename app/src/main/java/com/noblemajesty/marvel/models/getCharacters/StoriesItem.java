
package com.noblemajesty.marvel.models.getCharacters;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoriesItem implements Parcelable
{

  @SerializedName("resourceURI")
  @Expose
  private String resourceURI;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("type")
  @Expose
  private String type;
  public final static Parcelable.Creator<StoriesItem> CREATOR = new Creator<StoriesItem>() {


    @SuppressWarnings({
        "unchecked"
    })
    public StoriesItem createFromParcel(Parcel in) {
      return new StoriesItem(in);
    }

    public StoriesItem[] newArray(int size) {
      return (new StoriesItem[size]);
    }

  };

  protected StoriesItem(Parcel in) {
    this.resourceURI = ((String) in.readValue((String.class.getClassLoader())));
    this.name = ((String) in.readValue((String.class.getClassLoader())));
    this.type = ((String) in.readValue((String.class.getClassLoader())));
  }

  public StoriesItem() {
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(resourceURI);
    dest.writeValue(name);
    dest.writeValue(type);
  }

  public int describeContents() {
    return 0;
  }

}
