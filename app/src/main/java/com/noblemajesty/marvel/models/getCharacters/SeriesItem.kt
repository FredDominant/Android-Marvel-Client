package com.noblemajesty.marvel.models.getCharacters

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SeriesItem : Parcelable {

    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String
    @SerializedName("name")
    @Expose
    val name: String

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
        val CREATOR: Parcelable.Creator<SeriesItem> = object : Parcelable.Creator<SeriesItem> {


            override fun createFromParcel(`in`: Parcel): SeriesItem {
                return SeriesItem(`in`)
            }

            override fun newArray(size: Int): Array<SeriesItem?> {
                return arrayOfNulls(size)
            }

        }
    }

}
