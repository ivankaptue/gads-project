package com.klid.gads.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.klid.gads.R
import com.klid.gads.databinding.ActivitySubmissionBinding
import com.klid.gads.databinding.DialogConfirmLayoutBinding
import com.klid.gads.databinding.DialogErrorLayoutBinding
import com.klid.gads.databinding.DialogSuccessLayoutBinding

class SubmissionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubmissionBinding
    private val viewModel: SubmissionViewModel by lazy {
        return@lazy SubmissionViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_submission)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0F

        title = ""

        initViews()
    }

    private fun initViews() {
        binding.submitButton.setOnClickListener {
            val dialogBinding = DialogConfirmLayoutBinding.inflate(LayoutInflater.from(this))

            val alertDialog = AlertDialog.Builder(this).apply {
                setView(dialogBinding.root)
            }.create()

            dialogBinding.closeButton.setOnClickListener { _ ->
                alertDialog.hide()
            }
            dialogBinding.yesButton.setOnClickListener {
                alertDialog.hide()
                onSubmitted()
            }

            alertDialog.show()
        }
    }

    private fun onSubmitted() {
        val firstname = binding.firstnameEditText.text.toString().trim()
        val lastname = binding.lastnameEditText.text.toString().trim()
        val email = binding.emailEditText.text.toString().trim()
        val githubLink = binding.emailEditText.text.toString().trim()

        viewModel.submitProject(email, firstname, lastname, githubLink)
            .observe(this, Observer { response ->
                if (response.isSuccessful) {
                    displaySuccessfulDialog()
                } else {
                    displayErrorDialog()
                }
            })
    }

    private fun displaySuccessfulDialog() {
        val dialogBinding = DialogSuccessLayoutBinding.inflate(LayoutInflater.from(this))
        AlertDialog.Builder(this).apply {
            setView(dialogBinding.root)
        }.show()
    }

    private fun displayErrorDialog() {
        val dialogBinding = DialogErrorLayoutBinding.inflate(LayoutInflater.from(this))
        AlertDialog.Builder(this).apply {
            setView(dialogBinding.root)
        }.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}