package com.example.projectmanager.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.projectmanager.R
import com.example.projectmanager.ViewModel.Coroutines
import com.example.projectmanager.ViewModel.NFCViewModel
import com.example.projectmanager.databinding.FragmentBinder
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NFCFragment : Fragment, CompoundButton.OnCheckedChangeListener {

    companion object {
        private val TAG : String = NFCFragment::class.java.getSimpleName()

        public fun newInstance() : NFCFragment = NFCFragment()
    }

    private var binder : FragmentBinder? = null
    //private val viewModel : MainViewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }
    private val viewModel : NFCViewModel by viewModels<NFCViewModel> ()

    constructor() {

    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_nfc,container,false)
        binder?.setViewModel(viewModel)
        binder?.setLifecycleOwner(this@NFCFragment)
        return binder?.root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        Coroutines.main(this@NFCFragment) { scope ->
            scope.launch(block = {
                binder?.getViewModel()?.observeToast()?.collectLatest(action = { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                })
            })
            scope.launch(block = {
                binder?.getViewModel()?.observeTag()?.collectLatest(action = { tag ->
                    binder?.textViewExplanation?.setText(tag)
                })
            })
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCheckedChanged(buttonView : CompoundButton?, isChecked : Boolean) {
        if (buttonView == binder?.toggleButton)
            viewModel.onCheckNFC(isChecked)
    }
}