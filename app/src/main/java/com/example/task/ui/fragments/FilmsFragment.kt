package com.example.task.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.task.R
import com.example.task.data.entities.Film
import com.example.task.data.entities.FilmUserRef
import com.example.task.data.entities.Rate
import com.example.task.data.entities.UserFilmCrossRef
import com.example.task.data.repository.FilmRepository
import com.example.task.data.repository.RateRepository
import com.example.task.data.repository.UserFilmCrossRefRepository
import com.example.task.ui.adapters.AdapterFavorites
import com.example.task.ui.adapters.AdapterFilms
import com.example.task.databinding.FragmentFilmsBinding
import com.example.task.domain.CurrentUser
import com.example.task.domain.FilterTypes
import com.example.task.ui.adapters.ItemHorizontalTouch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FilmsFragment : Fragment(R.layout.fragment_films) {

    private var binding : FragmentFilmsBinding ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFilmsBinding.bind(view)

        binding?.let { binding ->
            lifecycleScope.launch {
                val filmRepository = FilmRepository()

                val filmsList =
                    async(Dispatchers.IO) {
                        filmRepository.getAllRefDesc(CurrentUser.get())
                    }.await()
                val favoritesList =
                    async(Dispatchers.IO) {
                        filmRepository.getAllUserFilms(CurrentUser.get())
                    }.await()
                val adapterFavorites = AdapterFavorites(
                    favoritesList = favoritesList,
                    onItemClicked = ::onItemClicked,
                    onFavoriteButtonClicked = ::onFavoriteButtonClicked
                )
                val adapterFilms = AdapterFilms(
                    filmsList = filmsList,
                    onItemClicked = ::onItemClicked,
                    onButtonClicked = ::onButtonClicked
                )

                val callback: ItemTouchHelper.Callback = ItemHorizontalTouch(
                    onItemSwiped=::onItemSwiped
                )
                val touchHelper = ItemTouchHelper(callback)
                touchHelper.attachToRecyclerView(binding.filmsRv)

                binding.msgTv.visibility = if (filmsList.isEmpty()) View.VISIBLE else View.GONE

                binding.filmsRv.adapter = adapterFilms
                binding.filmsRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

                binding.favoritesRv.adapter = adapterFavorites

                binding.filterSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        lifecycleScope.launch {
                            val adapter = binding.filmsRv.adapter as AdapterFilms
                            when (position) {
                                FilterTypes.YEAR_ASC.getValue() -> {
                                    adapter.updateItems(filmRepository.getAllRefDesc(CurrentUser.get()))
                                }
                                FilterTypes.YEAR_DESC.getValue() -> {
                                    adapter.updateItems(filmRepository.getAllRef(CurrentUser.get()))
                                }
                                FilterTypes.RATE_ASC.getValue() -> {
                                    adapter.updateItems(filmRepository.getByRateAsc(CurrentUser.get()))
                                }
                                FilterTypes.RATE_DESC.getValue() -> {
                                    adapter.updateItems(filmRepository.getByRateDesc(CurrentUser.get()))
                                }
                            }
                            binding.filmsRv.scrollToPosition(0)
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        return
                    }
                }
            }
        }
    }

    private fun onItemClicked(film : Film) {
        lifecycleScope.launch {
            val rateRepository = RateRepository()

            val dialog = BottomSheetFragment.getInstance(
                film=film,
                rate = rateRepository.get(
                    filmId=film.id,
                    userId=CurrentUser.get()
                ),
                average = rateRepository.getAvg(filmId=film.id),
                onButtonClicked=::onRatingBarButtonClicked
            )
            dialog.show(childFragmentManager, BottomSheetFragment.BOTTOM_SHEET_FRAGMENT_TAG)
        }
    }

    private fun onButtonClicked(filmUserRef : FilmUserRef) {
        val userFilmCrossRefRepository = UserFilmCrossRefRepository()
        lifecycleScope.launch {
            val userFilmCrossRef = UserFilmCrossRef(
                userId=CurrentUser.get(),
                filmId=filmUserRef.film.id
            )
            if (filmUserRef.added == CurrentUser.get()) {
                userFilmCrossRefRepository.delete(userFilmCrossRef)
                filmUserRef.added = 0
            }
            else {
                userFilmCrossRefRepository.add(userFilmCrossRef)
                filmUserRef.added = CurrentUser.get()
            }
            setNewFavoritesList()
        }
    }

    private fun onFavoriteButtonClicked(film : Film) {
        val userFilmCrossRefRepository = UserFilmCrossRefRepository()
        val filmRepository = FilmRepository()
        lifecycleScope.launch {
            val userFilmCrossRef = UserFilmCrossRef(
                userId=CurrentUser.get(),
                filmId=film.id
            )
            userFilmCrossRefRepository.delete(userFilmCrossRef)
            setNewFavoritesList()

            binding?.let {
                val adapter = it.filmsRv.adapter as AdapterFilms
                val newList = filmRepository.getAllRef(CurrentUser.get())
                adapter.updateItems(newList)
            }
        }
    }

    private suspend fun setNewFavoritesList() {
        val filmRepository = FilmRepository()
        val newList = filmRepository.getAllUserFilms(CurrentUser.get())
        binding?.let {
            val adapter = it.favoritesRv.adapter as AdapterFavorites
            adapter.updateItems(newList)
        }
    }

    private fun onItemSwiped(filmUserRef: FilmUserRef) {
        val film = filmUserRef.film
        val filmRepository = FilmRepository()
        val userFilmCrossRefRepository = UserFilmCrossRefRepository()
        val rateRepository = RateRepository()
        lifecycleScope.launch {
            userFilmCrossRefRepository.delete(film.id)
            rateRepository.deleteAllMatches(film.id)
            filmRepository.delete(film)
            val newFilmsList = filmRepository.getAllRef(CurrentUser.get())
            val newFavoritesList = filmRepository.getAllUserFilms(CurrentUser.get())
            binding?.let {
                val adapterFilms = it.filmsRv.adapter as AdapterFilms
                val adapterFavorites = it.favoritesRv.adapter as AdapterFavorites
                adapterFilms.updateItems(newFilmsList)
                adapterFavorites.updateItems(newFavoritesList)
                it.msgTv.visibility = if (newFilmsList.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    private fun onRatingBarButtonClicked(filmId : Int, rate : Int, tv : TextView) {
        lifecycleScope.launch {
            val rateRepository = RateRepository()
            val rate = Rate(
                filmId = filmId,
                userId = CurrentUser.get(),
                rate = rate
            )
            rateRepository.insert(rate)
            tv.text = rateRepository.getAvg(filmId).toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
