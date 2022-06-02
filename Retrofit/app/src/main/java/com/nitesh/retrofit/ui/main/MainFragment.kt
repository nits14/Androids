package com.nitesh.retrofit.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.nitesh.retrofit.databinding.FragmentMainBinding
import com.nitesh.retrofit.repository.Repository

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
       // viewModel.getPost()


        binding.button.setOnClickListener(){
            val number = binding.editTextNumber.text.toString()
            viewModel.getPost2(Integer.parseInt(number))

            viewModel.myresponse2.observe(viewLifecycleOwner, Observer { response ->
                if(response.isSuccessful) {
                    Log.d("Response",response.body()?.userId.toString())
                    Log.d("Response",response.body()?.id.toString())
                    Log.d("Response",response.body()?.title.toString())
                    Log.d("Response",response.body()?.body.toString())
                    binding.message.text = response.body().toString()
                }else{
                    Log.e("Response",response.errorBody().toString())
                }
            })
        }
    }
}