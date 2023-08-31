package com.example.weatherassistant.ui.weatherbrowsing

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.hardware.input.InputManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.navigation.fragment.findNavController
import com.example.weatherassistant.R
import com.example.weatherassistant.databinding.FragmentCitySelectBinding


class CitySelectFragment : Fragment() {

    private var _binding: FragmentCitySelectBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCitySelectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bind()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun bind(){
        val editText = binding.etSelectCity
        // 设置图标与
        // 设置叉叉图标点击事件
        editText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP){
                val drawable = editText.compoundDrawables[2]
                if (drawable != null && event.rawX >= (editText.width - drawable.bounds.width())){
                    editText.text.clear()
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
        // 设置点击空白关闭软键盘事件
        binding.linearlayoutParent.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN){
                closeSoftKeyBoard(editText)
            }
            return@setOnTouchListener false
        }
        // 设置回车点击事件
        binding.etSelectCity.setOnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun closeSoftKeyBoard(editText: EditText){
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
    }

}