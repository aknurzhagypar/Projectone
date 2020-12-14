package kz.aknur.newchildcare.content.home.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kz.nurbayevnt.newchildcare.R
import kz.aknur.newchildcare.content.home.HomeFragment
import kz.aknur.newchildcare.content.home.categories.models.SmallCategoriesModel
import kotlin.collections.ArrayList

class SmallCategoriesAdapter : RecyclerView.Adapter<SmallCategoriesAdapter.SmallCategoriesHolder> {

    companion object {
        const val TAG = "SmallCategoriesAdapter"
    }

    private var smallCategoriesList: ArrayList<SmallCategoriesModel> = ArrayList()
    private var callback: HomeFragment

    constructor(callback: HomeFragment) : super() {
        this.callback = callback
    }

    fun addSmallCat(smallCategoriesList: List<SmallCategoriesModel>) {
        this.smallCategoriesList.addAll(smallCategoriesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SmallCategoriesHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.small_category_item, parent, false)
        return SmallCategoriesHolder(
            root
        )
    }

    override fun getItemCount(): Int {
        return smallCategoriesList.count()
    }

    override fun onBindViewHolder(holder: SmallCategoriesHolder, position: Int) {
        holder.bind(smallCategoriesList.get(position), callback)
    }

    class SmallCategoriesHolder(private val root: View) :
        RecyclerView.ViewHolder(root) {
        private val smallCatImage: ImageView = root.findViewById(R.id.smallCatImage)
        private val smallCatTitle: TextView = root.findViewById(R.id.smallCatTitle)
        fun bind(
            smallCategory: SmallCategoriesModel,
            callback: HomeFragment
        ) {
            smallCatTitle.text = smallCategory.name

            if (smallCategory.icon != null) {

               Glide.with(root)
                    .load(smallCategory.icon)
                    .placeholder(R.drawable.loader)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(smallCatImage)
                smallCatImage.visibility = View.VISIBLE
            }
            root.setOnClickListener { v: View? ->
                callback.onSmallCategoryClick(
                    smallCategory
                )
            }
        }

    }

}

