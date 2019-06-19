package com.example.lost.skillplus.models.podos.responses

import java.io.Serializable

data class PostSessionResponse(val status: Boolean,
                               val message: String,
                               val skill_id: Int?) : Serializable