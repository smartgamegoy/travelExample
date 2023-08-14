package com.example.interviewapplication.ui.travel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewapplication.R
import com.example.interviewapplication.databinding.FragmentTravelMainBinding
import com.example.interviewapplication.remote.LanguageDialogListener
import com.example.interviewapplication.ui.language.LanguageDialogFragment
import com.example.interviewapplication.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

@AndroidEntryPoint
class TravelMainFragment : Fragment() {

    private var _binding: FragmentTravelMainBinding? = null

    private val binding get() = _binding!!

    val viewModel: TravelMainViewModel by viewModels()

    val travelAdapter = TravelPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTravelMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.travelToolbar.setOnClickListener{
            val languageDialogFragment = LanguageDialogFragment(dialogListener = object: LanguageDialogListener {
                override fun onDialogClosed() {
                    setPage()
                    viewModel.setAppTitle()
                }
            })
            languageDialogFragment.show(parentFragmentManager, "dialog")
        }

        viewModel.appTitle.observe(viewLifecycleOwner){
            binding.travelToolbar.title = it
        }

        setRecyclerView()
    }

    private fun setPage(){
        viewModel.setPage(1)

        viewModel.list.observe(viewLifecycleOwner){
            MainScope().launch {
                withContext(Dispatchers.IO) {
                    launch {
                        awaitAll(async { travelAdapter.submitData(it) })
                    }
                }
            }
        }
    }

    private fun setRecyclerView(){
        binding.travelRecycler.apply {
            adapter = travelAdapter
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
        }

        travelAdapter.onTravelItemClick { data ->
            val bundle = Bundle()
            bundle.putParcelable("itemData", data)
            setFragmentResult(
                "selectItem",
                bundle
            )
            findNavController().navigate(R.id.action_travelMainFragment_to_travelInformationFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        setPage()
    }
}