package com.example.easysoccer1.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.easysoccer1.data.models.SportCenter
import androidx.recyclerview.widget.RecyclerView
import com.example.easysoccer1.databinding.ItemSportCenterAdminBinding
import com.example.easysoccer1.databinding.ItemsSportCenterUserBinding

class SelectSportCenterAdapter(
    private val selectSportCenter: (SportCenter) -> Unit
) : RecyclerView.Adapter<SelectSportCenterAdapter.DescriptionViewHolder>() {

    private var listSelectSportCenter: ArrayList<SportCenter> = arrayListOf()

    fun setListSelectSportCenter(listSportCenter: List<SportCenter>) {
        clearAdapter()
        listSelectSportCenter.addAll(listSportCenter)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        listSelectSportCenter = arrayListOf()
        listSelectSportCenter.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DescriptionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSportCenterAdminBinding.inflate(layoutInflater, parent, false)
        return DescriptionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DescriptionViewHolder,
        position: Int
    ) {
        val product = listSelectSportCenter[position]

        holder.bind(product, holder.itemView.context, selectSportCenter)
    }

    override fun getItemCount(): Int {
        return listSelectSportCenter.size
    }

    open class DescriptionViewHolder(
        private var view: ItemSportCenterAdminBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            sportCenterSelect: SportCenter,
            context: Context,
            selectSportCenter: (SportCenter) -> Unit
        ) {
            view.apply {
                nameSelectSportCenter.text = sportCenterSelect.name
                nitSelectSportCenter.text = sportCenterSelect.nit
                addressSelectSportCenter.text = sportCenterSelect.address
                itemSportCenterAdmin.setOnClickListener { selectSportCenter(sportCenterSelect) }
                itemSportCenterAdmin.elevation = ELEVATION_CARD
            }

        }
    }

    companion object {
        private var ELEVATION_CARD = 4F
    }
}