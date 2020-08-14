package com.nullit.test_task.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nullit.test_task.R
import com.nullit.test_task.ui.api.dto.BestBookDto
import com.nullit.test_task.ui.fragments.adapters.BestItemClickListener
import com.nullit.test_task.ui.fragments.adapters.CarouselRecyclerAdapter
import com.nullit.test_task.ui.fragments.adapters.VerticalRecyclerAdapter
import com.nullit.test_task.ui.utils.CarouselLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*

class FragmentList : Fragment(), BestItemClickListener {

    lateinit var booksViewModel: BooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.run {
            booksViewModel = ViewModelProvider(this)[BooksViewModel::class.java]
        }
        subscribeObserver()
        booksViewModel.requestMainCarouselsItems()
        booksViewModel.requestBestBooks()

        if (savedInstanceState == null) {
            // запустили в первый раз
        }
    }

    private fun subscribeObserver() {
        booksViewModel.carouselBooks.observe(viewLifecycleOwner, Observer {items ->
            carouselRecyclerView.apply {
                layoutManager = CarouselLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = CarouselRecyclerAdapter().apply {
                    this.carouselItems = items
                }
            }
        })

        booksViewModel.bestBooks.observe(viewLifecycleOwner, Observer {books ->
            if (books.isNotEmpty()) {
                titleTextView.visibility = View.VISIBLE
            }
            bestSellersRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = VerticalRecyclerAdapter(this@FragmentList).apply {
                    this.bestBooks = books
                }
            }
        })
    }

    override fun onItemClick(bestBook: BestBookDto) {
        findNavController().navigate(R.id.action_fragmentList_to_detailsFragment)
        booksViewModel.onBestItemClick(bestBook)
    }
}