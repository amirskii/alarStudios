package com.example.alarstudios.ui.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.alarstudios.R
import com.example.alarstudios.data.model.place.Place
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class SecondFragment @Inject constructor()
    : Fragment(R.layout.fragment_second) {

    private val viewModel: SecondViewModel by viewModels()
    private val args: SecondFragmentArgs by navArgs()

    private val adapter by lazy { PlaceAdapter(requireContext(), object : PlaceAdapter.Delegate {
        override fun onItemClick(item: Place) {
            val action = SecondFragmentDirections.actionSecondFragmentToDetailsFragment(item)
            findNavController().navigate(action)
        }
    }) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.code = args.code
        placesRv.adapter = adapter
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.listData.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                loading.isVisible = loadStates.refresh is LoadState.Loading
                errorMsgTv.isVisible = loadStates.refresh is LoadState.Error
            }
        }
    }


}