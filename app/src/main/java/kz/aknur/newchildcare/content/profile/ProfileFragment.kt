package kz.aknur.newchildcare.content.profile

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
import kotlinx.android.synthetic.main.fragment_home.homeFragLv
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.nurbayevnt.newchildcare.R
import kz.aknur.newchildcare.content.child.childlist.ChildlistActivity
import kz.aknur.newchildcare.content.home.HomeViewModel
import kz.aknur.newchildcare.content.home.categories.models.LargeCategoriesModel
import kz.aknur.newchildcare.content.home.categories.models.SmallCategoriesModel
import kz.aknur.newchildcare.splash.SplashActivity
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.intentFor


class ProfileFragment : Fragment() {



    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment()

        const val TAG = "ProfileFragment"
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    private fun lets() {
        //setLoading(true)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        preparePage()
        observer()
        listeners()
        goToChildList.onClick {
            startActivity(intentFor<ChildlistActivity>())
        }
    }

    private fun listeners() {
        setProfileBtn.onClick { confirmLogOut()  }
    }


    private fun preparePage() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getProfileInfo()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observer() {
        viewModel.isError.observe(viewLifecycleOwner, Observer {
            errorDialog(it)
        })

        viewModel.profileText.observe(viewLifecycleOwner, Observer {
            profileText.text = it
            setLoading(false)
        })

    }

    private fun errorDialog(errorMsg: String) {
        alert {
            title = getString(R.string.error_unknown_title)
            message = errorMsg
            isCancelable = false
            positiveButton(getString(R.string.dialog_retry)) { dialog ->
                setLoading(false)
                dialog.dismiss()
            }
            negativeButton(getString(R.string.dialog_exit)) {
                activity?.finish()
            }
        }.show()
    }

    private fun confirmLogOut() {
        alert {
            title = "Вы уверены?"
            message = "Что хотите выйти из аккаунта?"
            isCancelable = true
            positiveButton("Отменить") { dialog ->
                dialog.dismiss()
            }
            negativeButton("Выйти") {
                viewModel.logOut()
                activity?.startActivity(intentFor<SplashActivity>())
                activity?.finish()
            }
        }.show()
    }

    private fun setLoading(b: Boolean) {
        if (b) profileFragLv.visibility = View.VISIBLE else profileFragLv.visibility = View.GONE
    }


}