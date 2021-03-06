package com.example.lost.skillplus.helpers.podos.raw

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Skill(val skill_id: Int?,
                 val skill_name: String?,
                 val skill_desc: String?,
                 val session_no: Int,
                 val skill_price: Float,
                 val duration: Float,
                 val extra_fees: Float,
                 val photo_path:String?,
                 val user_id: Int,
                 val cat_id: Int,
                 val adding_date: String?,
                 val rate: Float?,
                 val user_name: String?,
                 var schedule: List<Long>?,
                 @SerializedName("is_favorite") var is_favorite: Boolean?) : Serializable