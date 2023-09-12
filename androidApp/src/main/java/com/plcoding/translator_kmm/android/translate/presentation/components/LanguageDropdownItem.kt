package com.plcoding.translator_kmm.android.translate.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.plcoding.translator_kmm.core.presentation.UiLanguage

@Composable
fun LanguageDropdownItem(
    language: UiLanguage,
    modifier: Modifier,
    onClick: () -> Unit
) {

    DropdownMenuItem(onClick, modifier) {
        Image(
            painter = painterResource(id = language.drawableRes),
            contentDescription = language.name,
            Modifier.size(40.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(language.name)
    }

}