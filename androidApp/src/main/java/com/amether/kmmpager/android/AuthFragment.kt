package com.amether.kmmpager.android

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.amether.kmmpager.data.AuthViewModel
import com.amether.kmmpager.di.DiModule
import com.amether.kmmpager.model.Response

class AuthFragment : Fragment(R.layout.frg_auth) {

    private val viewModel: AuthViewModel = DiModule.authViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val successAuth = view.findViewById<Button>(R.id.successAuth)
        val failAuth = view.findViewById<Button>(R.id.failAuth)
        val responseText = view.findViewById<TextView>(R.id.responseText)

        successAuth.setOnClickListener {
            viewModel.auth(true)
        }
        failAuth.setOnClickListener {
            viewModel.auth(false)
        }

        lifecycleScope.launchWhenResumed {
            viewModel.dataFlow.collect {
                responseText.text = it.toString()
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.errorFlow.collect(::onError)
        }
    }

    private fun onError(error: Response.Failed) {
        Toast.makeText(
            requireContext(),
            "code: ${error.errorCode}\n ${error.error.message}",
            Toast.LENGTH_LONG).show()
    }

    companion object {
        val TAG = AuthFragment::class.qualifiedName!!

        fun newInstance() = AuthFragment()
    }
}