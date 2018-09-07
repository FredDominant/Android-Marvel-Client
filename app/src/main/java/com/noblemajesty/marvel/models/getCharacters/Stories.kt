package com.noblemajesty.marvel.models.getCharacters

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Stories : Parcelable {

    @SerializedName("available")
    @Expose
    var available: Int? = null
    @SerializedName("collectionURI")
    @Expose
    val collectionURI: String
    @SerializedName("items")
    @Expose
    val items: List<StoriesItem>? = null
    @SerializedName("returned")
    @Expose
    val returned: Int?

    protected constructor(`in`: Parcel) {
        this.available = `in`.readValue(Int::class.java.classLoader) as Int
        this.collectionURI = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(this.items, StoriesItem::class.java.classLoader)
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
        val CREATOR: Parcelable.Creator<Stories> = object : Parcelable.Creator<Stories> {


            override fun createFromParcel(`in`: Parcel): Stories {
                return Stories(`in`)
            }

            override fun newArray(size: Int): Array<Stories?> {
                return arrayOfNulls(size)
            }

        }
    }

}
