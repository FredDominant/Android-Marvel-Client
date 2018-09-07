package com.noblemajesty.marvel.models.getCharacters

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ComicItem : Parcelable {

    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null
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
        val CREATOR: Parcelable.Creator<ComicItem> = object : Parcelable.Creator<ComicItem> {


            override fun createFromParcel(`in`: Parcel): ComicItem {
                return ComicItem(`in`)
            }

            override fun newArray(size: Int): Array<ComicItem?> {
                return arrayOfNulls(size)
            }

        }
    }

}
