package com.example.easysoccer1.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easysoccer1.data.models.Reserve
import com.example.easysoccer1.databinding.ItemNotificationReserveBinding

class ReserveUserAdapter(private val goToReserve: (String) -> Unit) :
    RecyclerView.Adapter<ReserveUserAdapter.ReserveUserViewHolder>() {
    private var reserveUser: ArrayList<Reserve> = arrayListOf()

    fun setListReserveUser(listReserveUser: List<Reserve>) {
        clearAdapter()
        reserveUser.addAll(listReserveUser)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        reserveUser = arrayListOf()
        reserveUser.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReserveUserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationReserveBinding.inflate(layoutInflater, parent, false)
        return ReserveUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReserveUserViewHolder, position: Int) {
        val product = reserveUser[position]
        holder.bind(product, holder.itemView.context, goToReserve)
    }

    override fun getItemCount(): Int {
        return reserveUser.size
    }

    open class ReserveUserViewHolder(
        private var view: ItemNotificationReserveBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            reserveUser: Reserve,
            context: Context,
            goToReserve: (String) -> Unit
        ) {
            view.apply {
                nameSportCenterNotification.text = reserveUser.nameSportCenter
                dateNotification.text = reserveUser.date
                hourNotification.text = reserveUser.hour
                priceNotification.text = reserveUser.price
                payNotification.text = reserveUser.paidOrNot
                numberGoalNotification.text = reserveUser.numberGoal
                itemNotificationReserve.elevation = ELEVATION_CARD
            }
        }
    }

    companion object {
        private var ELEVATION_CARD = 4F
    }

}