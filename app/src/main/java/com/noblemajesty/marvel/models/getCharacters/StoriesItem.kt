package com.noblemajesty.marvel.models.getCharacters

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StoriesItem : Parcelable {

    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String
    @SerializedName("name")
    @Expose
    val name: String
    @SerializedName("type")
    @Expose
    var type: String? = null

    protected constructor(`in`: Parcel) {
        this.resourceURI = `in`.readValue(String::class.java.classLoader) as String
        this.name = `in`.readValue(String::class.java.classLoader) as String
        this.type = `in`.readValue(String::class.java.classLoader) as String
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(resourceURI)
        dest.writeValue(name)
        dest.writeValue(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<StoriesItem> = object : Parcelable.Creator<StoriesItem> {


            override fun createFromParcel(`in`: Parcel): StoriesItem {
                return StoriesItem(`in`)
            }

            override fun newArray(size: Int): Array<StoriesItem?> {
                return arrayOfNulls(size)
            }

        }
    }

}
