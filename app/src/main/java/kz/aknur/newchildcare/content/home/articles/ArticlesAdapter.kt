package kz.aknur.newchildcare.content.home.articles

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kz.aknur.newchildcare.R
import kz.aknur.newchildcare.content.home.articles.list.ArticleListActivity
import kotlin.collections.ArrayList

class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ArticlesHolder> {

    companion object {
        const val TAG = "ArticlesAdapter"
    }

    private var articles: ArrayList<ArticleModel> = ArrayList()
    private var callback: ArticleListActivity

    constructor(callback: ArticleListActivity) : super() {
        this.callback = callback
    }

    fun addArticles(articles: List<ArticleModel>) {
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticlesHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.art_item, parent, false)
        return ArticlesHolder(
            root
        )
    }

    override fun getItemCount(): Int {
        return articles.count()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ArticlesHolder, position: Int) {
        holder.bind(articles.get(position), callback)
    }

    class ArticlesHolder(private val root: View) :
        RecyclerView.ViewHolder(root) {
        private val artIcon: ImageView = root.findViewById(R.id.artIcon)
        private val artName: TextView = root.findViewById(R.id.artName)
        private val artText: TextView = root.findViewById(R.id.artText)


        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(
            article: ArticleModel,
            callback: ArticleListActivity
        ) {
            artName.text = article.topic




            artText.text = Html.fromHtml(article.text, Html.FROM_HTML_MODE_COMPACT)

            if (article.icon != null) {

               Glide.with(root)
                    .load(article.icon)
                    .placeholder(R.drawable.loader)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(artIcon)
                artIcon.visibility = View.VISIBLE
            }
            root.setOnClickListener { v: View? ->
                callback.onArticleClick(
                    article
                )
            }
        }

    }

}

