package kz.aknur.newchildcare.content.home.articles.list

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_article_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.aknur.newchildcare.R
import kz.aknur.newchildcare.content.home.articles.ArticleModel
import kz.aknur.newchildcare.content.home.articles.ArticlesAdapter
import kz.aknur.newchildcare.content.home.articles.details.ArticleDetailsActivity
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.io.Serializable

class ArticleListActivity : AppCompatActivity() {

    val adapter: ArticlesAdapter =
        ArticlesAdapter(this)

    private lateinit var viewModel: ArticlesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)
        lets()
    }

    private fun lets() {
        setLoading(true)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)
        observer()
        preparePage()
        backButton.onClick { onBackPressed() }
    }

    private fun preparePage() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getArticlesByCategory(intent?.getStringExtra("CATEGORY_ID").toString().toInt())
        }
    }

    private fun setupRecyclerView() {
        artRv.adapter = adapter
    }

    private fun addArticles(articles: List<ArticleModel>) {
        adapter.addArticles(articles)
        setLoading(false)
    }


    @SuppressLint("SetTextI18n")
    private fun observer() {
        viewModel.isError.observe(this, Observer {
            errorDialog(it)
        })

        viewModel.articles.observe(this, Observer {
            addArticles(it)
        })


    }

    private fun errorDialog(errorMsg: String) {
        alert {
            title = getString(R.string.error_unknown_title)
            message = errorMsg
            isCancelable = false
            positiveButton(getString(R.string.dialog_retry)) { dialog ->
                dialog.dismiss()
            }
            negativeButton(getString(R.string.dialog_exit)) {
                finish()
            }
        }.show()
    }

    private fun setLoading(b: Boolean) {
        if (b) artLv.visibility = View.VISIBLE else artLv.visibility = View.GONE
    }

    fun onArticleClick(article: ArticleModel) {
        startActivity(intentFor<ArticleDetailsActivity>()
            .putExtra("title", article.topic)
            .putExtra("text", article.text)
            .putExtra("icon", article.icon)
        )
    }
}