package me.kofesst.vkservices.presentation.screens.details.extras

import android.os.Parcel
import android.os.Parcelable
import me.kofesst.vkservices.domain.models.AppService

data class AppServiceExtra(
    val name: String,
    val description: String,
    val iconUrl: String,
    val serviceUrl: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        name = parcel.readString() ?: "Invalid name",
        description = parcel.readString() ?: "Invalid description",
        iconUrl = parcel.readString() ?: "Invalid icon",
        serviceUrl = parcel.readString() ?: "Invalid url"
    )

    fun toModel() = AppService(name, description, iconUrl, serviceUrl)

    override fun writeToParcel(parcel: Parcel, unused: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(iconUrl)
        parcel.writeString(serviceUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AppServiceExtra> {
        override fun createFromParcel(parcel: Parcel): AppServiceExtra {
            return AppServiceExtra(parcel)
        }

        override fun newArray(size: Int): Array<AppServiceExtra?> {
            return arrayOfNulls(size)
        }
    }
}