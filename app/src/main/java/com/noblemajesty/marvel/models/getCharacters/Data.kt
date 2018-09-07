package com.noblemajesty.marvel.models.getCharacters

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data protected constructor(`in`: Parcel) : Parcelable {
    @SerializedName("total")
    @Expose
    val total: Int?
    @SerializedName("count")
    @Expose
    val count: Int?
    @SerializedName("results")
    @Expose
    val results: List<Result>? = null

    init {
        this.total = `in`.readValue(Int::class.java.classLoader) as Int
        this.count = `in`.readValue(Int::class.java.classLoader) as Int
        `in`.readList(this.results, com.noblemajesty.marvel.models.getCharacters.Result::class.java.classLoader)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(total)
        dest.writeValue(count)
        dest.writeList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Data> = object : Parcelable.Creator<Data> {


            override fun createFromParcel(`in`: Parcel): Data {
                return Data(`in`)
            }

            override fun newArray(size: Int): Array<Data?> {
                return arrayOfNulls(size)
            }

        }
    }

}