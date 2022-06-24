package com.baubap.demo.home.ui.pokedex

import android.view.LayoutInflater
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.baubap.core.base.BaseFragment
import com.baubap.demo.home.R
import com.baubap.demo.home.data.remote.model.ApiResponsePokemon
import com.baubap.demo.home.data.remote.model.Pokemon
import com.baubap.demo.home.databinding.FragmentListPokemonBinding
import com.baubap.demo.home.ui.pokedex.adapter.AdapterPokedex
import com.baubap.demo.home.ui.viewmodel.PokedexViewModel
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentListPokemon(
    override val bindingInflater: (LayoutInflater) -> FragmentListPokemonBinding =
        FragmentListPokemonBinding::inflate
) : BaseFragment<FragmentListPokemonBinding>() {

  private val viewModel by viewModels<PokedexViewModel>()
  private var pokedexAdapter = AdapterPokedex()
  private lateinit var skeleton: Skeleton
  private var visibleThreshold = 30
  private var limit = 30
  private var offset = 0
  private var max = 0
  private var loading = true
  private var isSearching = true

  override fun setupView() {
    super.setupView()
    binding.recyclerView.apply {
      adapter = pokedexAdapter
      skeleton = applySkeleton(R.layout.item_pokemon, 10)
    }
    binding.scrollView.setOnScrollChangeListener(onScrolled())
    binding.scrollView.isSmoothScrollingEnabled = false

    ViewCompat.setNestedScrollingEnabled(binding.recyclerView, true)

    binding.searchText.addTextChangedListener {
      it?.let {
        pokedexAdapter.filter.filter(it)
        isSearching = it.isEmpty()
      }
    }
  }
  private fun scrollToBottom() {
    GlobalScope.launch(context = Dispatchers.Main) {
      delay(2000)
      binding.scrollView.smoothScrollBy(0, 1000)
    }
  }
  private fun onScrolled(): NestedScrollView.OnScrollChangeListener =
      NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
        if (!isSearching) return@OnScrollChangeListener
        val gridLayoutManager = binding.recyclerView.layoutManager as GridLayoutManager
        if (scrollY == (v.getChildAt(0).measuredHeight - v.measuredHeight)) {
          val lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition()
          if (!loading && gridLayoutManager.itemCount <= (lastVisibleItem + visibleThreshold)) {
            requestMorePokemon()
          }
        }
      }

  private fun requestMorePokemon() {
    if (offset < max) {
      offset += 30
      viewModel.getPokedex(limit, offset)
    }
  }

  override fun onLoader(data: Boolean) {
    loading = data
    if (data) skeleton.showSkeleton()
    else {
      skeleton.showOriginal()
    }
  }

  override fun onSuccess(data: Any) {

    (data as ApiResponsePokemon).apply {
      pokedexAdapter.insertNewElements(data.pokemons as ArrayList<Pokemon>)
      max = data.count
    }
    scrollToBottom()
  }

  override fun onFails(message: String) {
    showError(title = "Error en login", "Valida tus usuario o contraseña", "Cerrar")
  }

  override fun setupObserver() {
    viewModel.fetchData().observe(requireActivity(), ::onObserverViewModel)
  }

  override fun init() {
    viewModel.getPokedex(limit, offset)
  }
}
