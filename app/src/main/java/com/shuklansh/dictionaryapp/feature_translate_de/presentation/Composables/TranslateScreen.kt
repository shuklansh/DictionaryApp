package com.shuklansh.dictionaryapp.feature_translate_de.presentation.Composables

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.shuklansh.dictionaryapp.feature_dictionary.presentation.UiEvent
import com.shuklansh.dictionaryapp.feature_translate_de.presentation.TranslateViewModel
import com.shuklansh.dictionaryapp.ui.theme.DictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TranslateScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                //val vm: WordInfoViewModel = hiltViewModel()
                val vm : TranslateViewModel by viewModels()
                val state = vm.state.collectAsState()
                DictionaryAppTheme {

                    val scaffoldState = rememberScaffoldState()

                    val queryState = vm.query.collectAsState().value

                    LaunchedEffect(key1 = true) {
                        vm.eventFlow.collectLatest { event ->
                            when (event) {
                                is UiEvent.showSnackBar -> {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        event.message
                                    )
                                }
                            }
                        }
                    }

                    Scaffold(
                        scaffoldState = scaffoldState
                    ) {


                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(4.dp)
                            )
                            {

                                TextField(
                                    value = queryState.queryWord ,
                                    label = { Text(text = "Inpute ENG word for GER translation")},
                                    //vm.searchQuery.value,
                                    shape = RoundedCornerShape(16.dp),
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = Color(204, 204, 204, 255),
                                        cursorColor = Color.Black,
                                        textColor = Color.Black,
                                        disabledLabelColor = Color(66, 66, 66, 255),
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent
                                    ),
                                    maxLines = 1,
                                    onValueChange = vm::getTranslation,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(72.dp),
                                    placeholder = { Text(text = "Search") }
                                )
                                Spacer(Modifier.height(12.dp))
                                LazyColumn(Modifier.fillMaxSize()) {
                                    items(state.value.translatedWordItems.size) { i ->
                                        val wordInfo = state.value.translatedWordItems[i]
                                        if (i > 0) {
                                            Spacer(Modifier.height(8.dp))
                                        }

                                        WordTranslateItem(wordInfo)


                                        if (i < state.value.translatedWordItems.size - 1) {
                                            Divider()
                                        }

                                    }
                                }


                            }
                            if (state.value.isLoading) {
                                CircularProgressIndicator(Modifier.align(Alignment.Center))
                            }
                        }

                    }


                }
            }
        }
    }


}