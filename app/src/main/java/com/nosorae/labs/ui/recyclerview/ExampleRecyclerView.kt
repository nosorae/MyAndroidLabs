package com.nosorae.labs.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nosorae.labs.databinding.ItemExampleBinding
import com.nosorae.labs.listview.PhoneNum

class ExampleRecyclerView(
    private val callback: (PhoneNum) -> Unit // for 리스너
): RecyclerView.Adapter<ExampleRecyclerView.ExampleViewHolder>() {
    val list = mutableListOf<PhoneNum>() // 데이터 담아두는 곳

    //뷰홀더 (독립된 파일로 만들어도 됨)
    inner class ExampleViewHolder(val binding: ItemExampleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhoneNum) {
            // View 와 Data 연결


            // 클릭리스너 예시
            binding.root.setOnClickListener {
                callback(item)
            }

        }
    }

    // 뷰홀더가 생성될 때 호출되는 콜백함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder =
        ExampleViewHolder(
            ItemExampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    // 말그대로 뷰홀더와 데이터를 바인드해주는 콜백함수
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}