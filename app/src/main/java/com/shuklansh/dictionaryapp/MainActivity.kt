package com.shuklansh.dictionaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shuklansh.dictionaryapp.feature_dictionary.presentation.UiEvent
import com.shuklansh.dictionaryapp.feature_dictionary.presentation.WordInfoViewModel
import com.shuklansh.dictionaryapp.ui.WordInfoItem
import com.shuklansh.dictionaryapp.ui.theme.DictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val vm: WordInfoViewModel = hiltViewModel()
            val state = vm.state.collectAsState()
            DictionaryAppTheme {

                val scaffoldState = rememberScaffoldState()

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
                                value = vm.searchQuery.value,
                                onValueChange = vm::onSearch,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(72.dp),
                                placeholder = { Text(text = "Search") }
                            )
                            Spacer(Modifier.height(12.dp))
                            LazyColumn(Modifier.fillMaxSize()) {
                                items(state.value.wordInfoItems.size) { i ->
                                    val wordInfo = state.value.wordInfoItems[i]
                                    if (i > 0) {
                                        Spacer(Modifier.height(8.dp))
                                    }
                                    WordInfoItem(wordInfo = wordInfo)
                                    if (i < state.value.wordInfoItems.size - 1) {
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

