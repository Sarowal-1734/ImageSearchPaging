package com.example.imagesearchpaging.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.imagesearchpaging.R
import com.example.imagesearchpaging.databinding.FragmentPhotoDetailsBinding
import com.example.imagesearchpaging.effect.Shimmer

class PhotoDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPhotoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)

        // Getting arguments passed from UserFragment
        val args: PhotoDetailsFragmentArgs by navArgs()

        Glide.with(this)
            .load(args.photo.urls.regular)
            .centerCrop()
            .placeholder(Shimmer.getEffect())
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_error)
            .into(binding.imageView)

        binding.textView.text = args.photo.user.name

        return binding.root
    }

}