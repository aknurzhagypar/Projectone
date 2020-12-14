package kz.aknur.newchildcare.content.home.articles.details

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_article_details.*
import kz.aknur.newchildcare.R
import kz.aknur.newchildcare.content.home.articles.ArticleModel
import org.jetbrains.anko.sdk27.coroutines.onClick

class ArticleDetailsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        lets()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun lets() {
        artBackButton.onClick { onBackPressed() }
        setArt()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setArt() {

        val topic = intent.getStringExtra("topic")
        val text = intent.getStringExtra("text")
        val icon = intent.getByteArrayExtra("icon")

        artDetailTitle.text = topic
        artDetailText.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)

        if (icon != null) {

            Glide.with(this)
                .load(icon)
                .placeholder(R.drawable.loader)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(artDetailIcon)
            artDetailIcon.visibility = View.VISIBLE
        }
    }
}