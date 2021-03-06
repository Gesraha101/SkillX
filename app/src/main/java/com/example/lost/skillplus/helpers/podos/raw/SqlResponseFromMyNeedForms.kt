package com.example.lost.skillplus.helpers.podos.raw

data class SqlResponseFromMyNeedForms(
        var user_id: Int,
        var user_name: String? = null,
        var user_pic: String? = null,
        var need: String? = null,
        var form_id: Int,
        var session_no: Int,
        var duration: Int,
        var need_price: Int,
        var extra_fees: Int,
        var need_id: Int,
        var last_updated: Long,
        var flag: Int,
        var schedule: List<Long>


)