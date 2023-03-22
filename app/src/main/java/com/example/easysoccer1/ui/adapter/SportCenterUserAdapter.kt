package com.example.easysoccer1.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easysoccer1.databinding.ItemsSportCenterUserBinding
import com.example.easysoccer1.models.AreaSportCenterUser

class SportCenterUserAdapter (private val selectGoToDetail: (AreaSportCenterUser) -> Unit,
                              private val selectGoToReserve: (AreaSportCenterUser) -> Unit,
) : RecyclerView.Adapter<SportCenterUserAdapter.SearchProductViewHolder>() {
    private var sportCenterArea: ArrayList<AreaSportCenterUser> = arrayListOf()

    fun setListInYouArea(listStadiumsInYourArea: List<AreaSportCenterUser>) {
        clearAdapter()
        sportCenterArea.addAll(listStadiumsInYourArea)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        sportCenterArea = arrayListOf()
        sportCenterArea.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemsSportCenterUserBinding.inflate(layoutInflater, parent, false)
        return SearchProductViewHolder(binding)
    }

    override fun getItemCount() = sportCenterArea.size

    override fun onBindViewHolder(holder: SearchProductViewHolder, position: Int) {
        val product = sportCenterArea[position]
        //holder.itemView.setOnClickListener { selectGoToDetail(product) }
        holder.bind(product, holder.itemView.context, selectGoToDetail, selectGoToReserve)
    }

    open class SearchProductViewHolder(
        private var view: ItemsSportCenterUserBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            stadiumInYourArea: AreaSportCenterUser,
            context: Context,
            selectGoToDetail: (AreaSportCenterUser) -> Unit,
            selectGoToReserve: (AreaSportCenterUser) -> Unit
        ) {
            view.apply {
                textNameStadiumItem.text = stadiumInYourArea.nameStadium
                textPriceStadiumItem.text = stadiumInYourArea.valueStadium
                textAdressStadiumItem.text = stadiumInYourArea.directionStadium
                //btnMap.setOnClickListener { selectGoToDetail(stadiumInYourArea) }
                //btnReserverUser.setOnClickListener { selectGoToReserve(stadiumInYourArea) }
                val identifier =
                    context.resources.getIdentifier(
                        stadiumInYourArea.image,
                        "drawable",
                        context.packageName
                    )
                if (identifier > 0) {
                    imageSportCenterItem.setImageResource(identifier)
                }
                itemSportCenterUser.elevation = ELEVATION_CARD
            }
        }
    }

    companion object {
        private var ELEVATION_CARD = 4F
    }
}
