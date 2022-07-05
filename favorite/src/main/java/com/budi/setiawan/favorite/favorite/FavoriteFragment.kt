package com.budi.setiawan.favorite.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.budi.setiawan.core.ui.FavoriteAdapter
import com.budi.setiawan.favorite.databinding.FragmentFavoriteBinding
import com.budi.setiawan.favorite.di.DaggerFavoriteComponent
import com.budi.setiawan.gamein.di.FavoriteModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels{
        factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context.applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val favoriteAdapter = FavoriteAdapter()
            favoriteAdapter.setOnItemClickListener {
//                val favoriteDirections = FavoriteFragmentDirections.actionFavoriteFragmentToDetailGameActivity(it.id)
//                findNavController().navigate(favoriteDirections)
            }

            favoriteViewModel.favoriteGames.observe(viewLifecycleOwner) {
                favoriteAdapter.setData(it)
                binding.empty.visibility =
                    if (it.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvFavorites) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}