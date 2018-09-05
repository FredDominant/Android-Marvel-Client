
package com.noblemajesty.marvel.models.getCharacters;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{
  @SerializedName("total")
  @Expose
  private Integer total;
  @SerializedName("count")
  @Expose
  private Integer count;
  @SerializedName("results")
  @Expose
  private List<Result> results = null;
  public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


    @SuppressWarnings({
        "unchecked"
    })
    public Data createFromParcel(Parcel in) {
      return new Data(in);
    }

    public Data[] newArray(int size) {
      return (new Data[size]);
    }

  };

  protected Data(Parcel in) {
    this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.count = ((Integer) in.readValue((Integer.class.getClassLoader())));
    in.readList(this.results, (com.noblemajesty.marvel.models.getCharacters.Result.class.getClassLoader()));
  }

  public Integer getTotal() {
    return total;
  }

  public Integer getCount() {
    return count;
  }

  public List<Result> getResults() {
    return results;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(total);
    dest.writeValue(count);
    dest.writeList(results);
  }

  public int describeContents() {
    return 0;
  }

}