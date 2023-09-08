package com.example.easysoccer1.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.databinding.ItemSelectSportCenterBinding

class SportCenterAdminAdapter(private val goToSportCenter: (String) -> Unit) :
    RecyclerView.Adapter<SportCenterAdminAdapter.SportCenterViewHolder>() {
    private var sportCenter: ArrayList<SportCenter> = arrayListOf()

    fun setListSelectSportCenter(listSportCenter: List<SportCenter>) {
        clearAdapter()
        sportCenter.addAll(listSportCenter)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        sportCenter = arrayListOf()
        sportCenter.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportCenterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSelectSportCenterBinding.inflate(layoutInflater, parent, false)
        return SportCenterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sportCenter.size
    }

    override fun onBindViewHolder(holder: SportCenterViewHolder, position: Int) {
        val product = sportCenter[position]
        holder.bind(product, holder.itemView.context, goToSportCenter)
    }

    open class SportCenterViewHolder(
        private var view: ItemSelectSportCenterBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            sportCenter: SportCenter,
            context: Context,
            goToSportCenter: (String) -> Unit
        ) {
            view.apply {
                titleSportCenter.text = sportCenter.nameSportCenter
                titleNitSportCenter.text = sportCenter.nit
                titleAddressSportCenter.text = sportCenter.address
                Glide.with(context).load(sportCenter.imageSportCenterUrl).into(imageSelectSportCenter)
                itemSelectSportCenter.setOnClickListener { goToSportCenter(sportCenter.nit) }
                itemSelectSportCenter.elevation = ELEVATION_CARD
            }

        }


    }

    companion object {
        private var ELEVATION_CARD = 4F
    }
}





