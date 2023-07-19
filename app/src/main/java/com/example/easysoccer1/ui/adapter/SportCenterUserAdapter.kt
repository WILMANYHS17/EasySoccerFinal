package com.example.easysoccer1.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easysoccer1.databinding.ItemsSportCenterUserBinding
import com.example.easysoccer1.data.models.SportCenter

class SportCenterUserAdapter(
    private val goToDescription: (String) -> Unit
) : RecyclerView.Adapter<SportCenterUserAdapter.DescriptionViewHolder>() {
    private var sportCenterArea: ArrayList<SportCenter> = arrayListOf()

    fun setListInYouArea(listStadiumsInYourArea: List<SportCenter>) {
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
    ): DescriptionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemsSportCenterUserBinding.inflate(layoutInflater, parent, false)
        return DescriptionViewHolder(binding)
    }

    override fun getItemCount() = sportCenterArea.size

    override fun onBindViewHolder(holder: DescriptionViewHolder, position: Int) {
        val product = sportCenterArea[position]
        //holder.itemView.setOnClickListener { selectGoToDetail(product) }
        holder.bind(product, holder.itemView.context, goToDescription)
    }

    open class DescriptionViewHolder(
        private var view: ItemsSportCenterUserBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            stadiumInYourArea: SportCenter,
            context: Context,
            goToDescription: (String) -> Unit

        ) {
            view.apply {
                textNameStadiumItem.text = stadiumInYourArea.nameSportCenter
                textPriceStadiumItem.text = stadiumInYourArea.price5vs5
                textAdressStadiumItem.text = stadiumInYourArea.address
                itemSportCenterUser.setOnClickListener { goToDescription(stadiumInYourArea.nit) }
                //btnMap.setOnClickListener { selectGoToDetail(stadiumInYourArea) }
                //btnReserverUser.setOnClickListener { selectGoToReserve(stadiumInYourArea) }
                /*
                val identifier =
                    context.resources.getIdentifier(
                        stadiumInYourArea.image,
                        "drawable",
                        context.packageName
                    )
                if (identifier > 0) {
                    imageSportCenterItem.setImageResource(identifier)
                }
                 */
                itemSportCenterUser.elevation = ELEVATION_CARD
            }
        }
    }

    companion object {
        private var ELEVATION_CARD = 4F
    }
}
