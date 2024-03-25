package com.stevemd.feature.repolist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.stevemd.common.extension.setUpVerticalRecyclerView
import com.stevemd.feature.repolist.R
import com.stevemd.feature.repolist.adapter.RepoListAdapter
import com.stevemd.feature.repolist.databinding.FragmentRepositoryListBinding
import com.stevemd.feature.repolist.viewmodel.RepoListUiState
import com.stevemd.feature.repolist.viewmodel.RepoListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepositoryListFragment : Fragment() {

    private lateinit var binding: FragmentRepositoryListBinding

    private val viewModel: RepoListViewModel by viewModels()
    private val adapter = RepoListAdapter {
        navigateToProfile()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoryListBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().setUpVerticalRecyclerView(binding.recyclerViewRepoList, adapter)

        observeViewState()
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

    private fun observeViewState() {
//        viewModel.uiState.execute { state ->
//            when (state) {
//                is RepoListUiState.Loading -> (state.isLoading)
//                is RepoListUiState.Success -> adapter.submitList(state.data)
//                is RepoListUiState.Error -> state.message
//            }
//        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is RepoListUiState.Loading -> (state.isLoading)
                        is RepoListUiState.Success -> adapter.submitList(state.data)
                        is RepoListUiState.Error -> state.message
                    }
                }
            }
        }
    }

//    protected inline fun <T> Flow<T>.execute(crossinline action: (T) -> Unit) {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                collect {
//                    action(it)
//                }
//            }
//        }
//    }
}