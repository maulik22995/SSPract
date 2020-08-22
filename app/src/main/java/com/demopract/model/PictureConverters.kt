package com.demopract.model

import androidx.room.TypeConverter
import com.google.gson.Gson


class PictureConverters {
    var gson = Gson()

    @TypeConverter
    fun stringToPicture(data: String?): Picture? {
        if (data == null) {
            return null
        }
        return gson.fromJson(data, Picture::class.java)
    }

    @TypeConverter
    fun pictureToString(someObjects: Picture?): String? {
        return gson.toJson(someObjects)
    }
}