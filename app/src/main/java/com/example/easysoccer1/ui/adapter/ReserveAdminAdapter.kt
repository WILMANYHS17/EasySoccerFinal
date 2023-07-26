package com.example.easysoccer1.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easysoccer1.data.models.Reserve
import com.example.easysoccer1.databinding.ItemNotificationReserveAdminBinding

class ReserveAdminAdapter(private val goToReserve: (String) -> Unit) :
    RecyclerView.Adapter<ReserveAdminAdapter.ReserveAdminViewHolder>() {
    private var reserveAdmin: ArrayList<Reserve> = arrayListOf()

    fun setListReserveAdmin(listReserveAdmin: List<Reserve>) {
        clearAdapter()
        reserveAdmin.addAll(listReserveAdmin)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        reserveAdmin = arrayListOf()
        reserveAdmin.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReserveAdminViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationReserveAdminBinding.inflate(layoutInflater, parent, false)
        return ReserveAdminViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reserveAdmin.size
    }

    override fun onBindViewHolder(holder: ReserveAdminViewHolder, position: Int) {
        val product = reserveAdmin[position]
        holder.bind(product, holder.itemView.context, goToReserve)
    }

    open class ReserveAdminViewHolder(private var view: ItemNotificationReserveAdminBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(
            reserveAdmin: Reserve,
            context: Context,
            goToReserve: (String) -> Unit
        ) {
            view.apply {
                goalNumber.text = reserveAdmin.numberGoal
                reserveNumber.text = reserveAdmin.numberReserve
                reserveBy.text = reserveAdmin.nameReserveBy
                dateReserve.text = reserveAdmin.date
                hourReserve.text = reserveAdmin.hour
                priceReserve.text = reserveAdmin.price
                itemNotificationAdmin.setOnClickListener{goToReserve(reserveAdmin.numberGoal)}
                itemNotificationAdmin.elevation = ELEVATION_CARD
            }
        }
    }

    companion object {
        private var ELEVATION_CARD = 4F
    }
}