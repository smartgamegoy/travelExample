package com.example.interviewapplication.ui.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewapplication.databinding.ViewHolderLanguageBinding
import com.example.interviewapplication.utils.Constants.languageList

class LanguageDialogAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onClick : ((String) -> Unit) ? = null

    inner class LanguageDialogViewHolder(private val binding: ViewHolderLanguageBinding):
        RecyclerView.ViewHolder(binding.root){

        fun onBind(title: String){
            binding.languageTitle.text = title

            binding.languageLinearLayout.setOnClickListener {
                onClick?.let {
                    it(title)
                }
            }
        }
    }

    fun onLanguageItemClick(listener: (String) -> Unit){
        onClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ViewHolderLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageDialogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = languageList[position]
        (holder as LanguageDialogViewHolder).onBind(item.text)
    }

    override fun getItemCount(): Int = languageList.size
}