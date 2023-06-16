package com.shuklansh.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Main3Activity : AppCompatActivity() {

    private lateinit var navcontroller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val navhostFrag = supportFragmentManager.findFragmentById(R.id.frag) as NavHostFragment
        navcontroller = navhostFrag.navController

    }
}