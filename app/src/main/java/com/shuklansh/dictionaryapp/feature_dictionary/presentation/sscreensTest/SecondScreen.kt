package com.shuklansh.dictionaryapp.feature_dictionary.presentation.sscreensTest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import com.shuklansh.dictionaryapp.R
import com.shuklansh.dictionaryapp.feature_dictionary.presentation.WordInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondScreen : Fragment() {

    val vm : WordInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {

                //val vm : WordInfoViewModel = hiltViewModel()
                Scaffold(Modifier.fillMaxSize()) {
//                    val query = "vm.tester"
                    val query = vm.tester2
                    Column(
                        Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(text = query)

                    }

                }
            }
        }
    }


}