package com.bhargavms.currencyconverter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class LiveQuotesRecyclerViewAdapter(
    private val dataSource: DataSource
) : RecyclerView.Adapter<LiveQuotesRecyclerViewAdapter.ViewHolder>() {

    init {
        dataSource.observeChange { notifyDataSetChanged() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quote_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSource.count
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val currencyAbbreivationView: TextView = view.findViewById(R.id.currencyTitle)
        private val conversionTextView: TextView = view.findViewById(R.id.currencyConversionQuote)
        fun bind(vm: MainScreen.LiveQuoteViewModel) {
            currencyAbbreivationView.text = vm.currencyAbrievation
            conversionTextView.text = vm.convertedRate.toString()
        }
    }

    interface DataSource {
        val count: Int
        fun observeChange(function: () -> Unit)
        operator fun get(position: Int): MainScreen.LiveQuoteViewModel
    }
}
