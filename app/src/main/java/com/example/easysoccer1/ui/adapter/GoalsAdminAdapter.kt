package com.example.easysoccer1.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easysoccer1.data.models.Goals
import com.example.easysoccer1.databinding.ItemGoalsBinding

class GoalsAdminAdapter(private val deleteGoals: (String) -> Unit): RecyclerView.Adapter<GoalsAdminAdapter.GoalsViewHolder>() {
    private var goals: ArrayList<Goals> = arrayListOf()

    fun setListGoals(listGoals: List<Goals>) {
        clearAdapter()
        goals.addAll(listGoals)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        goals = arrayListOf()
        goals.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGoalsBinding.inflate(layoutInflater, parent, false)
        return GoalsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return goals.size
    }

    override fun onBindViewHolder(holder: GoalsViewHolder, position: Int) {
        val product = goals[position]
        holder.bind(product, holder.itemView.context, deleteGoals)
    }

    open class GoalsViewHolder(private var view: ItemGoalsBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(
            goals: Goals,
            context: Context,
            deleteGoals: (String) -> Unit
        ) {

            view.apply {
                availableGoal.text = goals.available
                hourGoal.text = goals.hour
                dateGoal.text = goals.date
                sizeGoal.text = goals.size
                numberGoal.text = goals.number
                priceGoal.text = goals.price
                imageTrash.setOnClickListener {deleteGoals(goals.number) }
                itemGoals.elevation = ELEVATION_CARD
            }
        }

    }
companion object{
    private var ELEVATION_CARD = 4F
}

}