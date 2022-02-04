package com.example.imagesearchpaging.ui.gallery

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagesearchpaging.R
import com.example.imagesearchpaging.adapter.UnsplashPhotoAdapter
import com.example.imagesearchpaging.adapter.UnsplashPhotoLoadStateAdapter
import com.example.imagesearchpaging.databinding.FragmentGalleryBinding
import com.example.imagesearchpaging.model.UnsplashPhoto
import com.example.imagesearchpaging.ui.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(), UnsplashPhotoAdapter.OnItemClickListener {

    private lateinit var binding: FragmentGalleryBinding
    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGalleryBinding.inflate(inflater, container, false)

        val photoAdapter = UnsplashPhotoAdapter(this)

        binding.recyclerView.apply {
            itemAnimator = null
            adapter = photoAdapter.withLoadStateHeaderAndFooter(
                header = UnsplashPhotoLoadStateAdapter { photoAdapter.retry() },
                footer = UnsplashPhotoLoadStateAdapter { photoAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(context)
        }

        binding.buttonRetry.setOnClickListener {
            photoAdapter.retry()
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            photoAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        // Swipe to refresh if data changed
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            photoAdapter.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        photoAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error
                // empty view
                if (loadState.source.refresh is LoadState.NotLoading
                    && loadState.append.endOfPaginationReached
                    && photoAdapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewNoResult.isVisible = true
                } else {
                    textViewNoResult.isVisible = false
                }
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onItemClick(photo: UnsplashPhoto) {
        val direction = GalleryFragmentDirections.actionGalleryFragmentToPhotoDetailsFragment(photo)
        this.findNavController().navigate(direction)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.searchPhotos(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}