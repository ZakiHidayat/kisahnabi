package com.example.kisahnabi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kisahnabi.R
import com.example.kisahnabi.adapter.NabiAdapter
import com.example.kisahnabi.databinding.FragmentNabiBinding
import com.example.kisahnabi.model.ResponseNabiItem
import com.example.kisahnabi.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NabiFragment : Fragment() {

    private lateinit var binding: FragmentNabiBinding
    private lateinit var adapterNabi: NabiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNabiBinding.inflate(inflater, container, false)

        adapterNabi = NabiAdapter()
        binding.rvNabi.layoutManager = GridLayoutManager(context, 2)
        binding.rvNabi.adapter = adapterNabi
        binding.rvNabi.setHasFixedSize(true)
        binding.refreshlayoutNabi.setOnRefreshListener {
            getdataNabi()
            binding.refreshlayoutNabi.isRefreshing = false
        }

        getdataNabi()

        return binding.root
    }

    private fun getdataNabi() {
        val call  = RetrofitService.getService().getDataNabi()

        call.enqueue(object : Callback<List<ResponseNabiItem>> {
            override fun onResponse(
                call: Call<List<ResponseNabiItem>>,
                response: Response<List<ResponseNabiItem>>
            ) {
                if (response.isSuccessful){
                    val list = response.body()!!
                    list.let { it.let { it1 -> adapterNabi.addData(it1) } }
                    binding.progressNabi.visibility = View.GONE
                } else {
                    binding.progressNabi.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<ResponseNabiItem>>, t: Throwable) {
                binding.progressNabi.visibility = View.VISIBLE
                Toast.makeText(context, "Gagal Memuat... Harap mencoba lagi", Toast.LENGTH_LONG).show()
            }

        })
    }

}