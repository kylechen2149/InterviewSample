package com.kylechen2149.interviewsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kylechen2149.interviewsample.R
import com.kylechen2149.interviewsample.databinding.LayoutParkingInformationListBinding
import com.kylechen2149.interviewsample.listInformation.model.ParkingInformation
import com.kylechen2149.interviewsample.listInformation.viewmodel.ListInformationViewModel

class ParkingInformationListDetailAdapter(
    private val items: MutableList<ParkingInformation>,
    private val viewModel: ListInformationViewModel
) : RecyclerView.Adapter<ParkingInformationListDetailAdapter.ParkingInformationListViewHolder>() {

    class ParkingInformationListViewHolder(
        private val binding: LayoutParkingInformationListBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(record: ParkingInformation, viewModel: ListInformationViewModel) {
            binding.record = record
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingInformationListViewHolder {
        val binding = DataBindingUtil.inflate<LayoutParkingInformationListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_parking_information_list,
            parent,
            false
        )
        return ParkingInformationListViewHolder(binding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ParkingInformationListViewHolder, position: Int) {
        holder.bind(items[position], viewModel)
    }

    // return the number of the items in the list
    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = position
}