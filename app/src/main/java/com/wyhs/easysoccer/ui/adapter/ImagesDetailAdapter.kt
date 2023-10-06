package com.wyhs.easysoccer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wyhs.easysoccer.databinding.ItemImageProductBinding

class ImagesDetailAdapter() : RecyclerView.Adapter<ImagesDetailAdapter.ImageViewHolder>() {
    private var imagesProducts: ArrayList<String> = arrayListOf()

    fun setListImage(picturesList: List<String>) {
        imagesProducts = arrayListOf()
        imagesProducts.clear()
        imagesProducts.addAll(picturesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemImageProductBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val product = imagesProducts[position]
        holder.bind(product, holder.itemView.context)
    }

    override fun getItemCount() = imagesProducts.size

    open class ImageViewHolder(private var view: ItemImageProductBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(picture: String, context: Context) {
            view.apply {
                Glide.with(context).load(picture).into(imvImageProduct)
            }
        }
    }
}