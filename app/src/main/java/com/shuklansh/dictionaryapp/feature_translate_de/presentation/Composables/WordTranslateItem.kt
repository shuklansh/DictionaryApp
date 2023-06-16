package com.shuklansh.dictionaryapp.feature_translate_de.presentation.Composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.Def

@Composable
fun WordTranslateItem(
    deTranslate: Def,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = deTranslate.text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Magenta
        )

        deTranslate.tr.forEach { meaning ->
            meaning.text?.let { Text(text = it, fontWeight = FontWeight.Bold) }
            meaning.mean?.forEachIndexed { i, definition ->
                Text(text = "${i + 1}. ${definition.text }")
                Spacer(modifier = Modifier.height(8.dp))
                //Text(text = " Synonymn : ${meaning.syn}")
                //Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}