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

        val options : HashMap<String,String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"


        binding.button.setOnClickListener(){
            val number = binding.editTextNumber.text.toString()
            viewModel.getCustomPost2(Integer.parseInt(number),options)

            viewModel.myCustomPostResponse2.observe(viewLifecycleOwner, Observer { response ->
                if(response.isSuccessful) {
                    response.body()?.forEach{
                        Log.d("Response",it.userId.toString())
                        Log.d("Response",it.id.toString())
                        Log.d("Response", it.title)
                        Log.d("Response", it.body)
                        Log.d("Response", "-----------")
                    }

                    binding.message.text = response.body().toString()
                }else{
                    //Log.e("Response",response.errorBody().toString())
                    binding.message.text = response.code().toString()
                }
            })
        }
    }
}