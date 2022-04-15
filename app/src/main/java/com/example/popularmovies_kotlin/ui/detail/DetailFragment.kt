package com.example.popularmovies_kotlin.ui.detail

import android.os.Bundle
 import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
 import androidx.lifecycle.ViewModelProvider
 import com.example.popularmovies_kotlin.App
import com.example.popularmovies_kotlin.Const.BASE_IMAGE_LARGE
 import com.example.popularmovies_kotlin.R
import com.example.popularmovies_kotlin.ViewModelFactory
import com.example.popularmovies_kotlin.databinding.FragmentDetailBinding
 import com.gnova.domain.models.Movie
 import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailFragment : Fragment(R.layout.fragment_detail) {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<DetailViewModel>
    private lateinit var viewModel: DetailViewModel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        val binding = FragmentDetailBinding.bind(view)
        _binding = binding

        // Grab the selectedMovie from the safeargs
        val movie = DetailFragmentArgs.fromBundle(requireArguments()).selectedMovie

        // Init View Model
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        viewModel.onViewInit(movie)


        initialiseData(movie)
    }

    private fun initialiseData(movie: Movie) {
        movie.poster_path?.let { picassoLoadImages(it, binding.moviePoster) }
        movie.backdrop_path?.let { picassoLoadImages(it, binding.movieBackdrop) }
        binding.releaseDate.text = movie.release_date
        binding.rating.text = movie.vote_average.toString()
        binding.movieTitle.text = movie.title
        binding.synopsis.text = movie.overview
    }


    private fun picassoLoadImages(img: String, imageView: ImageView) {
        val imgUrl = BASE_IMAGE_LARGE + img
        imgUrl.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Picasso.get()
                .load(imgUri)
                //.placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(imageView)
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}