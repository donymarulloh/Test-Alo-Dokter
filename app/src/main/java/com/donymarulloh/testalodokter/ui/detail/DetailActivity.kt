package com.donymarulloh.testalodokter.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.donymarulloh.testalodokter.data.model.gambar.ImagesItem
import com.donymarulloh.testalodokter.databinding.ActivityDetailBinding
import com.donymarulloh.testalodokter.ui.base.BaseActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.synnapps.carouselview.ImageListener
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : BaseActivity() {

    private lateinit var objectList: List<ImagesItem>
    private val viewModel by viewModel<DetailViewModel>()

    private lateinit var binding: ActivityDetailBinding

    var sampleImages = arrayOf(
        "https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_3.jpg",
        "https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_1.jpg",
        "https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_2.jpg"
    )


    private val urlExtra by lazy {
        intent.getStringExtra(KEY_URL)
    }
    private val nameExtra by lazy {
        intent.getStringExtra(KEY_NAME)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }

    override fun observeChange() {
    }

    private fun initView() {

        val gson = Gson()
         objectList = gson.fromJson(urlExtra, Array<ImagesItem>::class.java).asList()

        with(binding) {
            binding.carouselView.setPageCount(objectList.size)
            binding.carouselView.setImageListener(imageListener)
            binding.llBack.setOnClickListener {
                onBackPressed()
            }
        }


    }

    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            // You can use Glide or Picasso here
            Glide.with(this@DetailActivity)
                .load(objectList[position].url_image)
                .into(imageView);
        }
    }

    companion object {
        private const val KEY_URL = "url"
        private const val KEY_NAME = "name"

        fun startActivity(context: Context?, urlJson: String) = context?.startActivity(
            Intent(context, DetailActivity::class.java).apply {
                putExtra(KEY_URL, urlJson)
            }
        )
    }

}