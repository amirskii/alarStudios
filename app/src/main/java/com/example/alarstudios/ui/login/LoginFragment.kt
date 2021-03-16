package com.example.alarstudios.ui.login

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.alarstudios.R
import com.example.alarstudios.data.model.Status
import com.example.alarstudios.data.model.login.LoginResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment @Inject constructor()
    : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        password.apply {
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        observeViewModel()
                }
                false
            }

            loginBtn.setOnClickListener {
                observeViewModel()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.login(username.text.toString(), password.text.toString()).observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        loading.visibility = View.GONE
                        it.data?.let {
                            handleSuccess(it)
                        }
                    }
                    Status.ERROR -> {
                        loading.visibility = View.GONE
                        it.message?.let { showLoginFailed(it) }
                    }
                    Status.LOADING -> {
                        statusTv.text = ""
                        loading.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun handleSuccess(resp: LoginResponse) {
        if (resp.status == "error") {
            statusTv.text = "Invalid user name/password"
        } else {
            val action = LoginFragmentDirections.actionLoginFragmentToSecondFragment(resp.code)
            findNavController(this).navigate(action)
        }
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show()
    }
}