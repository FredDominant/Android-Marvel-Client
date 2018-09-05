package com.noblemajesty.marvel.models.getCharacters

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MarvelCharacters : Parcelable {

    @SerializedName("code")
    @Expose
    var code: Int? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null

    protected constructor(`in`: Parcel) {
        this.code = `in`.readValue(Int::class.java.classLoader) as Int
        this.data = `in`.readValue(Data::class.java.classLoader) as Data
    }

    constructor() {}

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<MarvelCharacters> = object : Parcelable.Creator<MarvelCharacters> {


            override fun createFromParcel(`in`: Parcel): MarvelCharacters {
                return MarvelCharacters(`in`)
            }

            override fun newArray(size: Int): Array<MarvelCharacters?> {
                return arrayOfNulls(size)
            }

        }
    }

}
