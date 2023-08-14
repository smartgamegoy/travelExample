package com.example.interviewapplication.ui.language

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewapplication.databinding.FragmentLanguageDialogBinding
import com.example.interviewapplication.remote.LanguageDialogListener
import com.example.interviewapplication.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageDialogFragment(val dialogListener: LanguageDialogListener) : DialogFragment() {

    private var _binding: FragmentLanguageDialogBinding? = null

    private val binding get() = _binding!!

    val languageDialogAdapter = LanguageDialogAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLanguageDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        languageDialogAdapter.onLanguageItemClick { title ->
            var lang = "zh-tw"
            val findLanguage = Constants.languageList.find { language ->
                language.text == title
            }
            findLanguage?.let {
                lang = findLanguage.lang
            }
            Constants.language = lang

            val findTitle = Constants.appTitleList.find { language ->
                language.lang == lang
            }
            findTitle?.let {
                Constants.title = findTitle.text
            }

            val findTel = Constants.telList.find { language ->
                language.lang == lang
            }
            findTel?.let {
                Constants.tel = findTel.text
            }

            val findAddress = Constants.addressList.find { language ->
                language.lang == lang
            }
            findAddress?.let {
                Constants.address = findAddress.text
            }

            dialogListener.onDialogClosed()
            dismiss()
        }

        binding.languageRecycler.apply {
            adapter = languageDialogAdapter
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
    }
}