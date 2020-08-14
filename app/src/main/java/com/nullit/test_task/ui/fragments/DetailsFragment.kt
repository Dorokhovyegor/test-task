package com.nullit.test_task.ui.fragments

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nullit.test_task.R
import com.nullit.test_task.ui.api.dto.BestBookDto
import com.nullit.test_task.ui.api.dto.MainCarouselDtoItem
import com.nullit.test_task.ui.fragments.adapters.NormalCarouselRecyclerAdapter
import com.nullit.test_task.ui.utils.Mapper
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    lateinit var booksViewModel: BooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.run {
            booksViewModel = ViewModelProvider(this)[BooksViewModel::class.java]
        }
        closeButton.setOnClickListener {
            findNavController().popBackStack()
        }
        subscribeObservers()
        booksViewModel.requestSimilarBooks()
    }

    private fun subscribeObservers() {
        booksViewModel.similarsBooks.observe(viewLifecycleOwner, Observer { similarBooks ->
            // apply recycler view
            alsoRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = NormalCarouselRecyclerAdapter().apply {
                    // для скорости написания и только
                    val items = ArrayList<MainCarouselDtoItem>()
                    similarBooks.forEach { similar ->
                        items.add(Mapper.convertSimilarBooksToCarouselItem(similar))
                    }
                    this.carouselItems = items
                }
            }
        })

        booksViewModel.bestBoolDetails.observe(viewLifecycleOwner, Observer { details ->
            setContent(details)
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setContent(contentInfo: BestBookDto) {
        nameOfBookTextView.text = contentInfo.title
        priceImageButton.text = contentInfo.price.toString() + "€"
        authorTextView.text = contentInfo.author
        val text = "<font color=#ffffff>${contentInfo.rate.score}</font> <font color=#707070>(${contentInfo.rate.amount})</font>"
        ratingTextView.setText(Html.fromHtml(text, 0))
        Glide.with(this).load(contentInfo.image)
            .centerCrop()
            .into(logoImageView)

    }
}