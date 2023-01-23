package com.example.disneyheroes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.disneyheroes.databinding.ActivityMainBinding
import com.example.disneyheroes.utils.navigationFragments

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationFragments(supportFragmentManager, StartAppFragment())
    }
}