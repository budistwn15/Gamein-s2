package com.budi.setiawan.gamein.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.budi.setiawan.core.data.Resource
import com.budi.setiawan.core.ui.GameAdapter
import com.budi.setiawan.gamein.R
import com.budi.setiawan.gamein.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()
    private val gameAdapter = GameAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            showGames()
        }
    }

    private fun showGames(){
        gameAdapter.setOnItemClickListener {
            val homeDirections = HomeFragmentDirections.actionHomeFragmentToDetailGameActivity(it.id)
            findNavController().navigate(homeDirections)
        }

        homeViewModel.games.observe(viewLifecycleOwner){
            if(it != null){
                when(it){
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        it.data?.let { games ->
                            binding.progressBar.visibility = View.GONE
                            gameAdapter.setData(games)
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

        with(binding){
            rvTopGames.apply{
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
    }

    override fun onDestroyView() {
        binding.rvTopGames.adapter = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}