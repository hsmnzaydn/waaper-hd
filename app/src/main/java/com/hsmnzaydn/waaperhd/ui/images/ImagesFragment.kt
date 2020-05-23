package com.hsmnzaydn.waaperhd.ui.images

import android.view.View
import com.basefy.base_mvp.BaseFragment
import com.basefy.core_utility.onInitGrid
import com.hsmnzaydn.waaperhd.databinding.FragmentImagesBinding
import com.hsmnzaydn.waaperhd.image.domain.entities.Image
import com.hsmnzaydn.waaperhd.ui.adapters.ImagesAdapter
import com.hsmnzaydn.waaperhd.ui.controller
import com.hsmnzaydn.waaperhd.ui.image_detail.ImageDetailFragment
import javax.inject.Inject


class ImagesFragment : BaseFragment<FragmentImagesBinding>(), ImagesContract.View {
    @Inject
    lateinit var presenter: ImagesContract.Presenter<ImagesContract.View>


    val imageAdapter: ImagesAdapter by lazy {
        ImagesAdapter(activity!!)
    }



    private var pageNumber: Int? = 0
    private var isSearch = false
    private var searchField:String? = null


    override fun loadDataToList(response: List<Image>?) {

        imageAdapter.items = response!!

        binding!!.fragmentImageContentLoadingProgressbar.run {
            visibility = View.GONE
        }

        imageAdapter.onItemClick { it, position, layoutId ->
            it.id?.let { it1 ->
                ImageDetailFragment.getImageDetailInstance(
                    it1,
                    imageAdapter.items[position].imagePath!!
                )
            }?.let { it2 ->
                controller.navigate(
                    it2
                )
            }
        }

    }

    override fun showBottomLoadin() {
        binding!!.fragmentImageContentLoadingProgressbar.run {
            visibility = View.VISIBLE
        }
    }




    override fun initUI() {
        binding = FragmentImagesBinding.inflate(layoutInflater)
        presenter.onAttach(this)

        initImageAdapter()

    }

    private fun initImageAdapter() {

        imageAdapter.onInitGrid(
            binding!!.fragmentImagesRecylerview,
            column = 3
        )

        imageAdapter.recylerView = binding!!.fragmentImagesRecylerview

        imageAdapter.reciveBottom {
            pageNumber = pageNumber!! + 1
            if(isSearch){
                searchField?.let { presenter.searchImages(pageNumber!!, it) }
            }else{
                presenter.getImages(pageNumber)
            }
        }


        presenter.getImages()

        binding!!.hsmnzaydnToolbar.listenerSearchEdittext {
            presenter.searchImages(0, it)
            searchField = it
            isSearch = true
        }


        binding!!.hsmnzaydnToolbar.closeClickIconListener {
            pageNumber = 0
            presenter.getImages(pageNumber)
            isSearch = false
        }
    }

    override fun againOpened() {

    }


}
