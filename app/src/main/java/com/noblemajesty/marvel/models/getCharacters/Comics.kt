package com.noblemajesty.marvel.models.getCharacters

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Comics protected constructor(`in`: Parcel) : Parcelable {

    @SerializedName("available")
    @Expose
    val available: Int?
    @SerializedName("collectionURI")
    @Expose
    val collectionURI: String
    @SerializedName("comicItems")
    @Expose
    val comicItems: List<ComicItem>? = null

    init {
        this.available = `in`.readValue(Int::class.java.classLoader) as Int
        this.collectionURI = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(this.comicItems, ComicItem::class.java.classLoader)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(available)
        dest.writeValue(collectionURI)
        dest.writeList(comicItems)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Comics> = object : Parcelable.Creator<Comics> {


            override fun createFromParcel(`in`: Parcel): Comics {
                return Comics(`in`)
            }

            override fun newArray(size: Int): Array<Comics?> {
                return arrayOfNulls(size)
            }

        }
    }

}

