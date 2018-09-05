package com.noblemajesty.marvel.models.getCharacters

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Url : Parcelable {

    private val type: String
    @SerializedName("url")
    @Expose
    val url: String

    protected constructor(`in`: Parcel) {
        this.type = `in`.readValue(String::class.java.classLoader) as String
        this.url = `in`.readValue(String::class.java.classLoader) as String
    }

    constructor() {}

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(type)
        dest.writeValue(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Url> = object : Parcelable.Creator<Url> {


            override fun createFromParcel(`in`: Parcel): Url {
                return Url(`in`)
            }

            override fun newArray(size: Int): Array<Url> {
                return arrayOfNulls(size)
            }

        }
    }

}