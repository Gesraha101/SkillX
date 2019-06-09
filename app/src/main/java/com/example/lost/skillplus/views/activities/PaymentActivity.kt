package com.example.lost.skillplus.views.activities

import RetrofitManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.lost.skillplus.R
import com.example.lost.skillplus.models.adapters.CustomAdapter
import com.example.lost.skillplus.models.managers.BackendServiceManager
import com.example.lost.skillplus.models.managers.PreferencesManager
import com.example.lost.skillplus.models.podos.raw.ApplySkill
import com.example.lost.skillplus.models.podos.responses.ApplySkillResponse
import kotlinx.android.synthetic.main.activity_payment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentActivity : AppCompatActivity() {

    private var tv: TextView? = null
    private var scheduleList = arrayListOf<Long>()

    private var SkillId: Int = 0

//    private var userId :Int = 2

    private var appliedRequest = ApplySkill(2, SkillId, scheduleList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        SkillId = intent.getIntExtra("SkillId", 0)

        tv = findViewById(R.id.tv)

        for (i in 0 until CustomAdapter.public_modelArrayList.size) {
            if (CustomAdapter.public_modelArrayList[i].getSelecteds()) {
                tv!!.text = tv!!.text.toString() + " , " + CustomAdapter.public_modelArrayList[i].getAnimals()
                scheduleList.add(CustomAdapter.public_modelArrayList[i].getAnimals().toLong())
            }
        }

        val share = PreferencesManager(this@PaymentActivity)
        val userId :Int = share.getId().toInt()
        val stringName = share.getName()

        if (userId != 0 && SkillId != 0) {
            appliedRequest.learner = userId
            Toast.makeText(this@PaymentActivity, "cat id is " + SkillId, Toast.LENGTH_LONG).show()
            appliedRequest.skill = SkillId
            appliedRequest.schedule = scheduleList
        }

        cancleButton.setOnClickListener{
            startActivity(Intent(this@PaymentActivity , AddNeedActivity::class.java))
        }

        Toast.makeText(this@PaymentActivity, scheduleList.size.toString(), Toast.LENGTH_SHORT).show()
        payButton.setOnClickListener {
            if (stringName != "" && SkillId != 0) {
                appliedRequest.learner = stringName!!.toInt()
                Log.d("SchaduleActivity", " id is " + userId)
                appliedRequest.skill = SkillId
                Log.d("SchaduleActivity", " SkillId is"+SkillId.toString())
                appliedRequest.schedule = scheduleList
            }

            Log.d("SchaduleActivity", "learner"+appliedRequest.learner.toString())
            Log.d("SchaduleActivity", "skill"+appliedRequest.skill.toString())
            Log.d("SchaduleActivity" , "SchaduleActivity # "+ appliedRequest.schedule?.get(0))
            val service = RetrofitManager.getInstance()?.create(BackendServiceManager::class.java)
            val call: Call<ApplySkillResponse>? = service?.applySkill(applySkill = appliedRequest)
            call?.enqueue(object : Callback<ApplySkillResponse> {
                override fun onFailure(call: Call<ApplySkillResponse>, t: Throwable) {
                   Toast.makeText(this@PaymentActivity ,"you just registered in this course "   , Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ApplySkillResponse>, response: Response<ApplySkillResponse>) {
                    Toast.makeText(this@PaymentActivity, "learnerID = " + stringName!!.toIntOrNull() + ", SkillId  " + SkillId + " status " + response.body()?.status, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}