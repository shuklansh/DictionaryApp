package com.shuklansh.dictionaryapp.feature_dictionary.presentation.sscreensTest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.shuklansh.dictionaryapp.R
import com.shuklansh.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import com.shuklansh.dictionaryapp.feature_dictionary.domain.use_case.GetWordInfo
import com.shuklansh.dictionaryapp.feature_dictionary.presentation.WordInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class TestFragment : Fragment() {

    val vm : WordInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {

//                val vm : WordInfoViewModel = hiltViewModel()
                Scaffold(androidx.compose.ui.Modifier.fillMaxSize()) {
//                    val query = "vm.tester"
                    val query = vm.tester
                    Column(
                        Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(text = query, modifier = Modifier.clickable {
                            findNavController().navigate(R.id.action_testFragment_to_secondScreen)
                        })

                    }

                }
            }
        }
    }


}