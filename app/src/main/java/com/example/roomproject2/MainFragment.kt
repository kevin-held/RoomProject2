package com.example.roomproject2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.roomproject2.databinding.MainFragmentBinding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainFragment : Fragment() {

    private var _binding : MainFragmentBinding? = null
    private val binding get() = _binding
    //lateinit var binding: FragmentTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = MainFragmentBinding.inflate(inflater,container,false)
        val view = binding!!.root
        //     _binding = LayoutInflater.container: ViewGroup? .inflate(inflater, R.layout.fragment_tasks, container, false)
        //binding.setLifecycleOwner(this)
        val application= requireNotNull(this.activity).application
        val dao = DonutDatabase.getInstance(application).donutDao
        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        binding!!.viewModel = viewModel
        binding!!.lifecycleOwner=viewLifecycleOwner
        return view
    }

}