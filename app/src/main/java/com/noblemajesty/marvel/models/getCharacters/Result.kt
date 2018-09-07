package com.noblemajesty.marvel.models.getCharacters

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result : Parcelable {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail? = null
    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String = ""
    @SerializedName("comics")
    @Expose
    var comics: Comics? = null
    @SerializedName("series")
    @Expose
    var series: Series? = null
    @SerializedName("stories")
    @Expose
    var stories: Stories? = null
    @SerializedName("events")
    @Expose
    var events: Events? = null
    @SerializedName("urls")
    @Expose
    val urls: List<Url>? = null

    protected constructor(`in`: Parcel) {
        this.id = `in`.readValue(Int::class.java.classLoader) as Int
        this.name = `in`.readValue(String::class.java.classLoader) as String
        this.description = `in`.readValue(String::class.java.classLoader) as String
        this.thumbnail = `in`.readValue(Thumbnail::class.java.classLoader) as Thumbnail
        this.resourceURI = `in`.readValue(String::class.java.classLoader) as String
        this.comics = `in`.readValue(Comics::class.java.classLoader) as Comics
        this.series = `in`.readValue(Series::class.java.classLoader) as Series
        this.stories = `in`.readValue(Stories::class.java.classLoader) as Stories
        this.events = `in`.readValue(Events::class.java.classLoader) as Events
        `in`.readList(this.urls, com.noblemajesty.marvel.models.getCharacters.Url::class.java.classLoader)
    }


    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeValue(name)
        dest.writeValue(description)
        dest.writeValue(thumbnail)
        dest.writeValue(resourceURI)
        dest.writeValue(comics)
        dest.writeValue(series)
        dest.writeValue(stories)
        dest.writeValue(events)
        dest.writeList(urls)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Result> = object : Parcelable.Creator<Result> {


            override fun createFromParcel(`in`: Parcel): Result {
                return Result(`in`)
            }

            override fun newArray(size: Int): Array<Result?> {
                return arrayOfNulls(size)
            }

        }
    }

}
