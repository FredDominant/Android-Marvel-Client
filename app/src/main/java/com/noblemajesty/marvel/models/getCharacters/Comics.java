package com.noblemajesty.marvel.models.getCharacters;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comics implements Parcelable
{

  @SerializedName("available")
  @Expose
  private Integer available;
  @SerializedName("collectionURI")
  @Expose
  private String collectionURI;
  @SerializedName("comicItems")
  @Expose
  private List<ComicItem> comicItems = null;
  public final static Parcelable.Creator<Comics> CREATOR = new Creator<Comics>() {


    @SuppressWarnings({
        "unchecked"
    })
    public Comics createFromParcel(Parcel in) {
      return new Comics(in);
    }

    public Comics[] newArray(int size) {
      return (new Comics[size]);
    }

  };

  protected Comics(Parcel in) {
    this.available = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.collectionURI = ((String) in.readValue((String.class.getClassLoader())));
    in.readList(this.comicItems, (ComicItem.class.getClassLoader()));
  }

  public Integer getAvailable() {
    return available;
  }

  public String getCollectionURI() {
    return collectionURI;
  }

  public List<ComicItem> getComicItems() {
    return comicItems;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(available);
    dest.writeValue(collectionURI);
    dest.writeList(comicItems);
  }

  public int describeContents() {
    return 0;
  }

}

