package com.nosorae.labs.listview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nosorae.labs.databinding.ActivityListViewBinding


data class PhoneNum (val name: String, val phoneNum: String)
// 이름, 전화번호

class ListViewActivity: AppCompatActivity() {
    lateinit var binding: ActivityListViewBinding
    lateinit var listAdapter: PhoneBookAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = ArrayList<PhoneNum>()
        for(i in 0..100) {
            data.add(PhoneNum("노소래${i}", "010-2932-2323"))
        }


        listAdapter = PhoneBookAdapter(data, this)



        binding.list.adapter = listAdapter
    }
}