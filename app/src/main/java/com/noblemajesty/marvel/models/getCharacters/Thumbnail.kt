package com.noblemajesty.marvel.models.getCharacters

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Thumbnail : Parcelable {

    @SerializedName("path")
    @Expose
    val path: String
    @SerializedName("extension")
    @Expose
    val extension: String

    protected constructor(`in`: Parcel) {
        this.path = `in`.readValue(String::class.java.classLoader) as String
        this.extension = `in`.readValue(String::class.java.classLoader) as String
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(path)
        dest.writeValue(extension)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Thumbnail> = object : Creator<Thumbnail> {


            override fun createFromParcel(`in`: Parcel): Thumbnail {
                return Thumbnail(`in`)
            }

            override fun newArray(size: Int): Array<Thumbnail?> {
                return arrayOfNulls(size)
            }

        }
    }

}