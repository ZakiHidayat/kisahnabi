package com.example.kisahnabi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kisahnabi.R
import com.example.kisahnabi.adapter.NabiAdapter
import com.example.kisahnabi.databinding.FragmentRasulBinding
import com.example.kisahnabi.model.ResponseNabiItem
import com.example.kisahnabi.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RasulFragment : Fragment() {

    private lateinit var binding : FragmentRasulBinding
    private lateinit var adapterNabi : NabiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRasulBinding.inflate(inflater, container, false)

        adapterNabi = NabiAdapter()
        binding.rvRasul.layoutManager = GridLayoutManager(context, 2)
        binding.rvRasul.adapter = adapterNabi
        binding.rvRasul.setHasFixedSize(true)
        binding.refreshlayoutRasul.setOnRefreshListener {
            getdataRasul()
            binding.refreshlayoutRasul.isRefreshing = false
        }

        getdataRasul()

        return binding.root
    }

    private fun getdataRasul() {
        val call = RetrofitService.getService().getDataRasul()

        call.enqueue(object : Callback<List<ResponseNabiItem>>{
            override fun onResponse(
                call: Call<List<ResponseNabiItem>>,
                response: Response<List<ResponseNabiItem>>
            ) {
                if (response.isSuccessful){
                    val list = response.body()!!
                    list.let { it.let {it1 -> adapterNabi.addData(it1) } }
                    binding.progressRasul.visibility = View.GONE
                } else {
                    binding.progressRasul.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<ResponseNabiItem>>, t: Throwable) {

                Toast.makeText(context, "Gagal Memuat... Harap mencoba lagi", Toast.LENGTH_LONG).show()
            }

        })
    }
}

