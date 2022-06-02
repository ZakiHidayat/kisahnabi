package com.example.kisahnabi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.kisahnabi.databinding.ActivityDetailNabiBinding
import com.example.kisahnabi.databinding.ItemNabiBinding
import com.example.kisahnabi.model.ResponseNabiItem

class DetailNabi : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNabiBinding

    companion object{
        const val EXTRA_DATA = "p"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNabiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<ResponseNabiItem>(EXTRA_DATA)

        binding.txtDetailnamanabi.text = data?.nama
        binding.txtDetailtpkelahirannabi.text = data?.tpKelahiran
        binding.txtDetailusianabi.text = data?.usia
        binding.txtDetailtptinggalnabi.text = data?.tempat
        binding.txtDetailkisahnabi.text = data?.deskripsi
        binding.imgDetailnabi.load(data?.avatar)

        binding.icBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}