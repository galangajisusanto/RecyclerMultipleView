package com.galangaji.recylermultipleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.galangaji.recylermultipleview.model.LoadMore
import com.galangaji.recylermultipleview.model.Promo
import kotlinx.android.synthetic.main.item_load_more.view.*
import kotlinx.android.synthetic.main.item_promo.view.*


class DataAdapter(private val context: Context) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val adapterDataList: MutableList<Any> = mutableListOf()

    companion object {
        private const val TYPE_PROMO = 0
        private const val TYPE_LOAD_MORE = 1
    }

    private fun addData(item: Any) {
        adapterDataList.add(item)
        notifyItemChanged(adapterDataList.size - 1)
    }

    fun addAllData(itemList: List<Any>) {
        for (item in itemList)
            addData(item)
    }

    inner class PromoViewHolder(itemView: View) : BaseViewHolder<Promo>(itemView) {
        override fun bind(item: Promo) {
            itemView.apply {
                val resId = resources.getIdentifier(item.imagePromo, "drawable", context.packageName)
                imageBanner.setBackgroundResource(resId)
                titleBadgePromo.text = item.titleBadge
                titlePromo.text = item.title
            }
        }
    }

    inner class LoadMoreViewHolder(itemView: View) : BaseViewHolder<LoadMore>(itemView) {
        override fun bind(item: LoadMore) {
            itemView.titleLoadMore.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_PROMO -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_promo, parent, false)
                PromoViewHolder(view)
            }
            TYPE_LOAD_MORE -> {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.item_load_more, parent, false)
                LoadMoreViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return adapterDataList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = adapterDataList[position]
        when (holder) {
            is PromoViewHolder -> holder.bind(element as Promo)
            is LoadMoreViewHolder -> holder.bind(element as LoadMore)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (adapterDataList[position]) {
            is Promo -> TYPE_PROMO
            is LoadMore -> TYPE_LOAD_MORE
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

}