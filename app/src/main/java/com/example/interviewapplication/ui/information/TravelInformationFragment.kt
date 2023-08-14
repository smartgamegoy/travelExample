package com.example.interviewapplication.ui.information

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.interviewapplication.R
import com.example.interviewapplication.data.Data
import com.example.interviewapplication.databinding.FragmentTravelInformationBinding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.interviewapplication.data.Image
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TravelInformationFragment : Fragment() {

    private var _binding: FragmentTravelInformationBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: TravelInformationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TravelInformationViewModel::class.java]
        setFragmentResultListener("selectItem") { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val itemData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("itemData", Data::class.java)
            } else {
                @Suppress("DEPRECATION")
                bundle.getParcelable<Data>("itemData")
            }
            itemData?.let {
                viewModel.itemData.set(it)
                getImageList(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTravelInformationBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        viewModel.imageList.observe(viewLifecycleOwner){
            binding.informationImage.setImageList(it, ScaleTypes.FIT)
        }

        binding.telPhone.setOnClickListener {
            val phoneNumber = binding.telPhone.text.toString()
            val uri = Uri.parse("tel:$phoneNumber")
            val dialIntent = Intent(Intent.ACTION_DIAL, uri)

            try {
                startActivity(dialIntent)
            } catch (e: ActivityNotFoundException) {
                // Define what your app should do if no activity can handle the intent.
            }
        }

        binding.address.setOnClickListener {
            val addressText = binding.address.text.toString()
            val uri = Uri.parse("geo:0,0?q=$addressText")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            mapIntent.setPackage("com.google.android.apps.maps")

            try {
                startActivity(mapIntent)
            } catch (e: ActivityNotFoundException) {
                // Define what your app should do if no activity can handle the intent.
            }
        }

        binding.urlText.setOnClickListener {
            val urlText = binding.urlText.text.toString()
            val bundle = Bundle()
            bundle.putString("urlString", urlText)
            setFragmentResult(
                "webView",
                bundle
            )
            findNavController().navigate(R.id.action_travelInformationFragment_to_webViewFragment)
        }

//        viewModel.urlString.observe(viewLifecycleOwner){
//            val bundle = Bundle()
//            bundle.putString("urlString", it)
//            setFragmentResult(
//                "webView",
//                bundle
//            )
//            findNavController().navigate(R.id.action_travelInformationFragment_to_webViewFragment)
//        }
    }

    private fun getImageList(itemData: Data){
        val slideModels = ArrayList<SlideModel>()
        itemData.images.let { list: List<Image> ->
            list.forEach { image ->
                val url = image.src
                slideModels.add(SlideModel(url))
            }
            viewModel.imageList.value = slideModels
        }.run {
            if(itemData.images.isEmpty())
                binding.informationImage.visibility = View.GONE
        }
    }
}