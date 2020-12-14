package kz.aknur.newchildcare.content.child.childlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import kz.aknur.newchildcare.R
import kz.aknur.newchildcare.content.child.childlist.models.ChildModel
import kz.aknur.newchildcare.content.home.HomeFragment
import kz.aknur.newchildcare.content.home.categories.models.LargeCategoriesModel
import kz.aknur.newchildcare.content.hospitals.models.HospitalModel
import kotlin.collections.ArrayList

class ChildListAdapter : RecyclerView.Adapter<ChildListAdapter.ChildListHolder> {

    companion object {
        const val TAG = "ChildListAdapter"
    }

    private var ChildList: ArrayList<ChildModel> = ArrayList()
    private var callback: ChildlistActivity

    constructor(callback: ChildlistActivity) : super() {
        this.callback = callback
    }

    fun addChildList(childList: List<ChildModel>) {
        this.ChildList.addAll(childList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChildListHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.childlist_item, parent, false)
        return ChildListHolder(
            root
        )
    }

    override fun getItemCount(): Int {
        return ChildList.count()
    }

    override fun onBindViewHolder(holder: ChildListHolder, position: Int) {
        holder.bind(ChildList.get(position), callback)
    }

    class ChildListHolder(private val root: View) :
        RecyclerView.ViewHolder(root) {
        private val childListName: TextView = root.findViewById(R.id.childListName)
        private val childListText: TextView = root.findViewById(R.id.childListText)


        @SuppressLint("SetTextI18n")
        fun bind(
            child: ChildModel,
            callback: ChildlistActivity
        ) {
            childListName.text = child.nickname
            childListText.text = child.birthDate

            root.setOnClickListener { v: View? ->
                callback.onChildClick(
                    child
                )
            }
        }

    }

}

