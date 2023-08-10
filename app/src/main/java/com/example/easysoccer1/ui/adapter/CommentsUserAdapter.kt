package com.example.easysoccer1.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easysoccer1.data.models.Comments
import com.example.easysoccer1.databinding.ItemCommentsBinding
import kotlin.reflect.KFunction0


class CommentsUserAdapter(private val goComment: (Comments) -> Unit) :
    RecyclerView.Adapter<CommentsUserAdapter.CommentUserViewHolder>() {
    private var comment: ArrayList<Comments> = arrayListOf()

    fun setListComment(listComment: List<Comments>) {
        clearAdapter()
        comment.addAll(listComment)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        comment = arrayListOf()
        comment.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentUserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCommentsBinding.inflate(layoutInflater, parent, false)
        return CommentUserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return comment.size
    }

    override fun onBindViewHolder(holder: CommentUserViewHolder, position: Int) {
        val product = comment[position]
        holder.bind(product, holder.itemView.context, goComment)
    }

    open class CommentUserViewHolder(
        private var view: ItemCommentsBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            comment: Comments,
            context: Context,
            goComment: (Comments) -> Unit
        ) {
            view.apply {
                nameUserComments.text = comment.nameUser
                textCommentUser.text = comment.comment
                itemCommentsUsers.setOnClickListener { goComment(comment) }
                itemCommentsUsers.elevation = ELEVATION_CARD
            }
        }
    }

    companion object {
        private var ELEVATION_CARD = 4F
    }

}