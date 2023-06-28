package com.danielwaiguru.radius.facilities_presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.danielwaiguru.radius.facilities_presentation.adapters.FacilitiesAdapter
import com.danielwaiguru.radius.facilities_presentation.databinding.FragmentFacilitiesBinding
import com.danielwaiguru.radius.facilities_presentation.models.FacilitiesUIState
import com.danielwaiguru.radius.facilities_presentation.utils.snackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FacilitiesFragment : Fragment(R.layout.fragment_facilities) {
    private var _binding: FragmentFacilitiesBinding? = null
    private val binding: FragmentFacilitiesBinding get() = _binding!!
    private val viewModel: FacilitiesViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFacilitiesBinding.bind(view)
        setupUI()
    }

    private fun setupUI() {
        val facilitiesAdapter = createFacilitiesAdapter()
        setupRecyclerView(facilitiesAdapter)
        observeViewState(facilitiesAdapter)
    }

    private fun observeViewState(facilitiesAdapter: FacilitiesAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.facilitiesUIState.collectLatest {
                    handleFacilitiesUIState(it, facilitiesAdapter)
                }
            }
        }
    }

    private fun handleFacilitiesUIState(
        state: FacilitiesUIState,
        facilitiesAdapter: FacilitiesAdapter
    ) {
        with(binding) {
            progressBar.isVisible = state.isLoading
            state.errorMessage?.let { error ->
                snackBar(
                    message = error,
                    destructive = true
                )
            }
            facilitiesAdapter.submitList(state.facilities)
        }
    }

    private fun setupRecyclerView(facilitiesAdapter: FacilitiesAdapter) {
        binding.rvFacilities.apply {
            adapter = facilitiesAdapter
            hasFixedSize()
        }
    }

    private fun createFacilitiesAdapter(): FacilitiesAdapter {
        return FacilitiesAdapter { optionId, isChecked ->
            viewModel.selectOption(optionId, isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}