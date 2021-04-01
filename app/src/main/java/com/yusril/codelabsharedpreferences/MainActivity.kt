package com.yusril.codelabsharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yusril.codelabsharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mUserPreference: UserPreference

    private var isPreferenceEmpty = false
    private lateinit var userModel: UserModel

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "My User Preference"
        mUserPreference = UserPreference(this)
        showExistingPreference()

        binding.btnSave.setOnClickListener(this)

    }

    private fun showExistingPreference() {
        userModel=mUserPreference.getUser()
        populateView(userModel)
        checkForm(userModel)
    }

    private fun populateView(userModel: UserModel) {
        binding.tvName.text = if (userModel.name.toString().isEmpty()) "Tidak Ada" else userModel.name
        binding.tvAge.text = if (userModel.age.toString().isEmpty()) "Tidak Ada" else userModel.age.toString()
        binding.tvIsLoveMu.text = if (userModel.isLove) "Ya" else "Tidak"
        binding.tvEmail.text = if (userModel.email.toString().isEmpty()) "Tidak Ada" else userModel.email
        binding.tvPhone.text = if (userModel.phoneNumber.toString().isEmpty()) "Tidak Ada" else userModel.phoneNumber
    }
    private fun checkForm(userModel: UserModel) {
        when {
            userModel.name.toString().isNotEmpty() -> {
                binding.btnSave.text = getString(R.string.change)
                isPreferenceEmpty = false
            }
            else -> {
                binding.btnSave.text = getString(R.string.save)
                isPreferenceEmpty = true
            }
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_save) {
            val intent = Intent(this@MainActivity, FormUserPreferenceActivity::class.java)
            when {
                // jika true kosong type form e add
                isPreferenceEmpty -> {
                    intent.putExtra(FormUserPreferenceActivity.EXTRA_TYPE_FORM, FormUserPreferenceActivity.TYPE_ADD)
                    intent.putExtra("USER", userModel)
                }
                else -> {
                    intent.putExtra(FormUserPreferenceActivity.EXTRA_TYPE_FORM, FormUserPreferenceActivity.TYPE_EDIT)
                    intent.putExtra("USER", userModel)
                }
            }
            startActivityForResult(intent, REQUEST_CODE)
        }
    }
}