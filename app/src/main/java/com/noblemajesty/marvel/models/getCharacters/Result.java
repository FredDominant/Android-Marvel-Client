
package com.noblemajesty.marvel.models.getCharacters;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable
{

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("thumbnail")
  @Expose
  private Thumbnail thumbnail;
  @SerializedName("resourceURI")
  @Expose
  private String resourceURI;
  @SerializedName("comics")
  @Expose
  private Comics comics;
  @SerializedName("series")
  @Expose
  private Series series;
  @SerializedName("stories")
  @Expose
  private Stories stories;
  @SerializedName("events")
  @Expose
  private Events events;
  @SerializedName("urls")
  @Expose
  private List<Url> urls = null;
  public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


    @SuppressWarnings({
        "unchecked"
    })
    public Result createFromParcel(Parcel in) {
      return new Result(in);
    }

    public Result[] newArray(int size) {
      return (new Result[size]);
    }

  }
      ;

  protected Result(Parcel in) {
    this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.name = ((String) in.readValue((String.class.getClassLoader())));
    this.description = ((String) in.readValue((String.class.getClassLoader())));
    this.thumbnail = ((Thumbnail) in.readValue((Thumbnail.class.getClassLoader())));
    this.resourceURI = ((String) in.readValue((String.class.getClassLoader())));
    this.comics = ((Comics) in.readValue((Comics.class.getClassLoader())));
    this.series = ((Series) in.readValue((Series.class.getClassLoader())));
    this.stories = ((Stories) in.readValue((Stories.class.getClassLoader())));
    this.events = ((Events) in.readValue((Events.class.getClassLoader())));
    in.readList(this.urls, (com.noblemajesty.marvel.models.getCharacters.Url.class.getClassLoader()));
  }

  public Result() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public Thumbnail getThumbnail() {
    return thumbnail;
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public Comics getComics() {
    return comics;
  }

  public Series getSeries() {
    return series;
  }

  public Stories getStories() {
    return stories;
  }

  public Events getEvents() {
    return events;
  }

  public List<Url> getUrls() {
    return urls;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(id);
    dest.writeValue(name);
    dest.writeValue(description);
    dest.writeValue(thumbnail);
    dest.writeValue(resourceURI);
    dest.writeValue(comics);
    dest.writeValue(series);
    dest.writeValue(stories);
    dest.writeValue(events);
    dest.writeList(urls);
  }

  public int describeContents() {
    return 0;
  }

}
