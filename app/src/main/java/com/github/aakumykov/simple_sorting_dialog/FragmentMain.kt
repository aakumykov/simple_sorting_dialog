package com.github.aakumykov.simple_sorting_dialog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.github.aakumykov.simple_sorting_dialog.databinding.FragmentMainBinding
import com.github.aakumykov.simple_sorting_dialog.extensions.showToast

class FragmentMain :
    Fragment(R.layout.fragment_main),
    SimpleSortingDialog.Callbacks
{
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        binding.button1.setOnClickListener { showDialog() }
    }

    override fun onResume() {
        super.onResume()
        SimpleSortingDialog
            .find(parentFragmentManager)
            ?.setCallbacks(this)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onSortingApplied(sortingSettings: SortingSettings) {
        Log.d(TAG, sortingSettings.toString())
        showToast(sortingSettings.toHumanString())
        showInfo(sortingSettings.toHumanString())
    }

    override fun onSortingCancelled() {
        showToast(R.string.sorting_cancelled)
    }

    private fun showInfo(text: String) {
        binding.logView.text = text
    }

    private fun showDialog() {
        SimpleSortingDialog
            .createAndShow(parentFragmentManager)
            .setCallbacks(this)
    }

    companion object {
        val TAG: String = FragmentMain::class.java.simpleName
        const val SORTING_SETTINGS = "SORTING_SETTINGS"
    }
}