package com.example.projectmanager.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectmanager.R
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.projectmanager.Domain.NFCStatus
import com.example.projectmanager.Fragment.NFCFragment
import com.example.projectmanager.ViewModel.Coroutines
import com.example.projectmanager.ViewModel.NFCViewModel
import com.example.projectmanager.databinding.ActivityBinder
import com.example.projectmanager.model.Repository.NFCManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NFC : AppCompatActivity , CompoundButton.OnCheckedChangeListener, NfcAdapter.ReaderCallback  {

    companion object {
        private val TAG = NFC::class.java.simpleName
    }

    private var binder : ActivityBinder? = null
    //private val viewModel : MainViewModel by lazy { ViewModelProvider(this@MainActivity).get(MainViewModel::class.java) }
    private val viewModel : NFCViewModel by viewModels<NFCViewModel>()

    constructor() {

    }

    override fun onCreate(savedInstanceState : Bundle?) {
        binder = DataBindingUtil.setContentView(this@NFC, R.layout.activity_nfc)
        binder?.viewModel = viewModel
        binder?.lifecycleOwner = this@NFC
        super.onCreate(savedInstanceState)
        binder?.toggleButton?.setOnCheckedChangeListener(this@NFC)
        Coroutines.main(this@NFC) { scope ->
            scope.launch(block = {
                binder?.viewModel?.observeNFCStatus()?.collectLatest(action = { status ->
                    Log.d(TAG, "observeNFCStatus $status")
                    if (status == NFCStatus.NoOperation) NFCManager.disableReaderMode(
                        this@NFC,
                        this@NFC
                    )
                    else if (status == NFCStatus.Tap) NFCManager.enableReaderMode(
                        this@NFC,
                        this@NFC,
                        this@NFC,
                        viewModel.getNFCFlags(),
                        viewModel.getExtras()
                    )
                })
            })
            scope.launch(block = {
                binder?.viewModel?.observeToast()?.collectLatest(action = { message ->
                    Log.d(TAG, "observeToast $message")
                    Toast.makeText(this@NFC, message, Toast.LENGTH_LONG).show()
                })
            })
            scope.launch(block = {
                binder?.viewModel?.observeTag()?.collectLatest(action = { tag ->
                    Log.d(TAG, "observeTag $tag")
                    binder?.textViewExplanation?.text = tag
                })
            })
        }
    }

    override fun onCheckedChanged(buttonView : CompoundButton?, isChecked : Boolean) {
        if (buttonView == binder?.toggleButton)
            viewModel.onCheckNFC(isChecked)
    }

    override fun onTagDiscovered(tag : Tag?) {
        binder?.viewModel?.readTag(tag)
    }

    private fun launchNFCFragment() {
        if (supportFragmentManager.findFragmentByTag(NFCFragment::class.java.simpleName) == null)
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout, NFCFragment.newInstance(), NFCFragment::class.java.simpleName)
                .addToBackStack(NFCFragment::class.java.simpleName)
                .commit()
    }
}