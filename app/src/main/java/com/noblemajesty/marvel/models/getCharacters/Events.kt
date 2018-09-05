package com.noblemajesty.marvel.models.getCharacters

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Events : Parcelable {

    @SerializedName("available")
    @Expose
    var available: Int? = null
    @SerializedName("collectionURI")
    @Expose
    var collectionURI: String = ""
    @SerializedName("items")
    @Expose
    val items: List<EventItem>? = null
    @SerializedName("returned")
    @Expose
    var returned: Int? = null

    protected constructor(`in`: Parcel) {
        this.available = `in`.readValue(Int::class.java.classLoader) as Int
        this.collectionURI = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(this.items, EventItem::class.java.classLoader)
        this.returned = `in`.readValue(Int::class.java.classLoader) as Int
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(available)
        dest.writeValue(collectionURI)
        dest.writeList(items)
        dest.writeValue(returned)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Events> = object : Parcelable.Creator<Events> {

            override fun createFromParcel(`in`: Parcel): Events {
                return Events(`in`)
            }

            override fun newArray(size: Int): Array<Events?> {
                return arrayOfNulls(size)
            }

        }
    }

}