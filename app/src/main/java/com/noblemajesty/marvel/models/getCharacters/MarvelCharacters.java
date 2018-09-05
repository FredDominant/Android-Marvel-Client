
package com.noblemajesty.marvel.models.getCharacters;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarvelCharacters implements Parcelable
{

  @SerializedName("code")
  @Expose
  private Integer code;
  @SerializedName("data")
  @Expose
  private Data data;
  public final static Parcelable.Creator<MarvelCharacters> CREATOR = new Creator<MarvelCharacters>() {


    @SuppressWarnings({
        "unchecked"
    })
    public MarvelCharacters createFromParcel(Parcel in) {
      return new MarvelCharacters(in);
    }

    public MarvelCharacters[] newArray(int size) {
      return (new MarvelCharacters[size]);
    }

  };

  protected MarvelCharacters(Parcel in) {
    this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.data = ((Data) in.readValue((Data.class.getClassLoader())));
  }

  public MarvelCharacters() {
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(data);
  }

  public int describeContents() {
    return 0;
  }

}
