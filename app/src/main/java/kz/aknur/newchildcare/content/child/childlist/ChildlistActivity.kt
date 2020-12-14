package kz.aknur.newchildcare.content.child.childlist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_childlist.*
import kotlinx.android.synthetic.main.fragment_hospitals.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.aknur.newchildcare.R
import kz.aknur.newchildcare.content.FoundationActivity
import kz.aknur.newchildcare.content.child.add.ChildAddActivity
import kz.aknur.newchildcare.content.child.childlist.models.ChildModel
import kz.aknur.newchildcare.content.hospitals.HospitalsAdapter
import kz.aknur.newchildcare.content.hospitals.HospitalsViewModel
import kz.aknur.newchildcare.content.hospitals.models.HospitalModel
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert

class ChildlistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_childlist)
        lets()
    }


    private val adapter: ChildListAdapter =
        ChildListAdapter(this)
    private lateinit var viewModel: ChildListViewModel


    private fun lets() {
        setLoading(true)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(ChildListViewModel::class.java)
        preparePage()
        observer()
        goMainPage.onClick {
            finish()
            startActivity(intentFor<FoundationActivity>())
        }
        addChild.onClick {
            startActivity(intentFor<ChildAddActivity>())
        }
    }

    private fun setupRecyclerView() {
        childListRv.adapter = adapter
    }

    private fun addChildlist(childlist: List<ChildModel>) {
        adapter.addChildList(childlist)
        setLoading(false)
    }

    private fun preparePage() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getHospitals()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observer() {
        viewModel.isError.observe(this, Observer {
            errorDialog(it)
        })

        viewModel.childlist.observe(this, Observer {
            addChildlist(it)
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
        if (b) childListLv.visibility = View.VISIBLE else childListLv.visibility = View.GONE
    }

    fun onChildClick(hospitalModel: ChildModel) {
        //
    }

}