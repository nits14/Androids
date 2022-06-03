package com.nitesh.retrofit.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nitesh.retrofit.adapter.MyAdapter
import com.nitesh.retrofit.databinding.FragmentMainBinding
import com.nitesh.retrofit.repository.Repository

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private val myAdapter by lazy { MyAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        val options : HashMap<String,String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"
        viewModel.getCustomPost2(2,options)
        viewModel.myCustomPostResponse2.observe(viewLifecycleOwner,Observer{ response ->
            if (response.isSuccessful){
                response.body()?.let { myAdapter.setData(it) }
            }else{
                Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show()
            }


        })

        /*
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
        }*/
    }

    fun setupRecyclerView(){
        binding.recyclerview.adapter = myAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
    }
}