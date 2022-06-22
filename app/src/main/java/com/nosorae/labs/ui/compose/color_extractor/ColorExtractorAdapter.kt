package com.nosorae.labs.ui.compose.color_extractor

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.nosorae.labs.R
import com.nosorae.labs.databinding.ColorExtractorItemBinding

data class ImageItem(
    val resId: Int,
    val title: String
)

class ColorExtractorAdapter() : RecyclerView.Adapter<ColorExtractorAdapter.ViewHolder>() {
    val list =
        mutableListOf(
            ImageItem(
                R.drawable.entp,
                "ENTP"
            ),
            ImageItem(
                R.drawable.entj,
                "ENTJ"
            ),
            ImageItem(
                R.drawable.enfp,
                "ENFP"
            ),
            ImageItem(
                R.drawable.enfj,
                "ENFJ"
            ),
            ImageItem(
                R.drawable.estp,
                "ESTP"
            ),
            ImageItem(
                R.drawable.estj,
                "ESTJ"
            ),
            ImageItem(
                R.drawable.esfp,
                "ESFP"
            ),
            ImageItem(
                R.drawable.esfj,
                "ESFJ"
            ),
            ImageItem(
                R.drawable.intj,
                "INTJ"
            ),
            ImageItem(
                R.drawable.infp,
                "INFP"
            ),
            ImageItem(
                R.drawable.infj,
                "INFJ"
            ),
            ImageItem(
                R.drawable.istp,
                "ISTP"
            ),
            ImageItem(
                R.drawable.istj,
                "ISTJ"
            ),
            ImageItem(
                R.drawable.isfp,
                "ISFP"
            ),
            ImageItem(
                R.drawable.isfj,
                "ISFJ"
            )
        ) // 데이터 담아두는 곳

    inner class ViewHolder(val binding: ColorExtractorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageItem) = with(binding) {
            tv.text = item.title

            Glide.with(root)
                .asBitmap()
                .centerCrop()
                .load(item.resId)
                .into(object : BitmapImageViewTarget(iv) {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap?>?
                    ) {
                        super.onResourceReady(resource, transition)
                        Palette.from(resource).maximumColorCount(32).generate {
                            Log.e("sorae.no", "${it?.dominantSwatch?.rgb}")
                            it?.let {
                                val dominantColor = it.dominantSwatch?.rgb


                                v.setBackgroundColor(dominantColor!!)
                            }
                        }

                    }
                })
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ColorExtractorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun submitList(list: List<ImageItem>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

}