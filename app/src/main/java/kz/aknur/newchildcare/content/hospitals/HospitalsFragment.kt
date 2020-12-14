package kz.aknur.newchildcare.content.hospitals

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_hospitals.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.aknur.newchildcare.R
import kz.aknur.newchildcare.content.home.HomeFragment
import kz.aknur.newchildcare.content.home.HomeViewModel
import kz.aknur.newchildcare.content.home.categories.LargeCategoriesAdapter
import kz.aknur.newchildcare.content.home.categories.SmallCategoriesAdapter
import kz.aknur.newchildcare.content.home.categories.models.LargeCategoriesModel
import kz.aknur.newchildcare.content.home.categories.models.SmallCategoriesModel
import kz.aknur.newchildcare.content.hospitals.models.HospitalModel
import org.jetbrains.anko.support.v4.alert


class HospitalsFragment : Fragment() {


    private val adapter: HospitalsAdapter =
        HospitalsAdapter(this)

    companion object {
        @JvmStatic
        fun newInstance() =
            HospitalsFragment()

        const val TAG = "HospitalsFragment"
    }

    private lateinit var viewModel: HospitalsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hospitals, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    private fun lets() {
        setLoading(true)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(HospitalsViewModel::class.java)
        preparePage()
        observer()
    }

    private fun setupRecyclerView() {
        hospitalsRv.adapter = adapter
    }

    private fun addHospitals(hospitals: List<HospitalModel>) {
        adapter.addHospitals(hospitals)
        setLoading(false)
    }

    private fun preparePage() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getHospitals()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observer() {
        viewModel.isError.observe(viewLifecycleOwner, Observer {
            errorDialog(it)
        })

        viewModel.hospitals.observe(viewLifecycleOwner, Observer {
            addHospitals(it)
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
                activity?.finish()
            }
        }.show()
    }

    private fun setLoading(b: Boolean) {
        if (b) hospFragLv.visibility = View.VISIBLE else hospFragLv.visibility = View.GONE
    }

    fun onHospitalClick(hospitalModel: HospitalModel) {
        //
    }


}