package com.budi.setiawan.gamein.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.budi.setiawan.core.data.Resource
import com.budi.setiawan.core.domain.model.Game
import com.budi.setiawan.gamein.R
import com.budi.setiawan.gamein.databinding.ActivityDetailGameBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.jsoup.Jsoup

@AndroidEntryPoint
class DetailGameActivity : AppCompatActivity() {

    private var _binding: ActivityDetailGameBinding? = null
    private val binding get() = _binding as ActivityDetailGameBinding

    private val detailViewModel: DetailViewModel by viewModels()

    private val args: DetailGameActivityArgs by navArgs()
    private var data: Game? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = args.id

        showDetailGame(id)
    }

    private fun showDetailGame(id: Int){

        if(id != 0){
            detailViewModel.getGamesId(id).observe(this){
                when(it){
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        data = it.data as Game
                        data?.let { game ->
                            with(binding){
                                Glide.with(this@DetailGameActivity)
                                    .load(game.background_image)
                                    .into(detailCard.ivDetail)
                                tvTitle.text = game.name
                                tvGenre.text = game.genres
                                contentDetail.tvStartCount.text = game.rating.toString()
                                contentDetail.tvReviewCount.text = game.reviews_count.toString()
                                contentDetail.tvDownloadCount.text = game.added.toString()
                                tvDescription.text = Jsoup.parse(game.description!!).text()

                                btnWebsite.setOnClickListener {
                                    Intent(Intent.ACTION_VIEW).apply {
                                        data = Uri.parse(game.website)
                                        startActivity(this)
                                    }
                                }

                                var statusFavorite = game.isFavorite
                                setStatusFavorite(!statusFavorite)
                                fab.setOnClickListener {
                                    statusFavorite = !statusFavorite
                                    if(statusFavorite){
                                        Toast.makeText(this@DetailGameActivity,"Game successfully added to favorites", Toast.LENGTH_LONG).show()
                                    }else{
                                        Toast.makeText(this@DetailGameActivity,"Game has been successfully removed from favorites", Toast.LENGTH_LONG).show()
                                    }
                                    detailViewModel.setFavoriteGames(game, statusFavorite)
                                    setStatusFavorite(statusFavorite)
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = it.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean){
        if(statusFavorite){
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }else{
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}