package com.stevemd.feature.repolist.ui

import android.os.Bundle
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLink
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.stevemd.common.base.BaseFragment
import com.stevemd.common.extension.setUpVerticalRecyclerView
import com.stevemd.feature.repolist.R
import com.stevemd.feature.repolist.adapter.RepoListAdapter
import com.stevemd.feature.repolist.databinding.FragmentRepoListBinding
import com.stevemd.feature.repolist.viewmodel.RepoListUiState
import com.stevemd.feature.repolist.viewmodel.RepoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoListFragment : BaseFragment<FragmentRepoListBinding>() {
    override fun viewBindingLayout(): FragmentRepoListBinding =
        FragmentRepoListBinding.inflate(layoutInflater)

    private val viewModel: RepoListViewModel by viewModels()
    private val adapter = RepoListAdapter {
        navigateToProfile()
    }

    private fun navigateToProfile() {
        val deepLink = NavDeepLinkRequest.Builder
            .fromUri("github://profile".toUri())
            .build()

        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_repolist, true)
            .build()

        findNavController().navigate(deepLink, navOptions)
    }


    override fun initializeView(savedInstanceState: Bundle?) {
        requireActivity().setUpVerticalRecyclerView(binding.recyclerViewRepoList, adapter)
        observeViewState()
    }

    private fun observeViewState() {
        viewModel.uiState.execute { state ->
            when (state) {
                is RepoListUiState.Loading -> (state.isLoading)
                is RepoListUiState.Success -> adapter.submitList(state.data)
                is RepoListUiState.Error -> state.message
            }
        }
    }
}