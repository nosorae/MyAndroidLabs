package com.nosorae.labs.listview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nosorae.labs.databinding.ActivityListViewBinding
import com.nosorae.labs.ui.recyclerview.ExampleRecyclerView


data class PhoneNum (val name: String, val phoneNum: String)
// 이름, 전화번호

class ListViewActivity: AppCompatActivity() {
    lateinit var binding: ActivityListViewBinding
    lateinit var listAdapter: ExampleRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}