package com.noblemajesty.marvel.models.getCharacters

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventItem : Parcelable {

    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String = ""
    @SerializedName("name")
    @Expose
    var name: String? = null

    protected constructor(`in`: Parcel) {
        this.resourceURI = `in`.readValue(String::class.java.classLoader) as String
        this.name = `in`.readValue(String::class.java.classLoader) as String
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(resourceURI)
        dest.writeValue(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<EventItem> = object : Parcelable.Creator<EventItem> {


            override fun createFromParcel(`in`: Parcel): EventItem {
                return EventItem(`in`)
            }

            override fun newArray(size: Int): Array<EventItem?> {
                return arrayOfNulls(size)
            }

        }
    }

}
