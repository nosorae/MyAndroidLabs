package com.nosorae.labs.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nosorae.labs.databinding.ItemPhoneNumBinding

class PhoneBookAdapter(
    private var list: ArrayList<PhoneNum>,
    private val context: Context
): BaseAdapter() {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ItemPhoneNumBinding.inflate(LayoutInflater.from(context))
        val data = list[position]

        binding.tvName.text = data.name
        binding.tvPhoneNum.text = data.phoneNum

        return binding.root
    }

    fun submitList(data: ArrayList<PhoneNum>) {
        list = data
    }

}