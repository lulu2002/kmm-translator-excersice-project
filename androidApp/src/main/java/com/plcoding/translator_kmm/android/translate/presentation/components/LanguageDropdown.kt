package com.plcoding.translator_kmm.android.translate.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.plcoding.translator_kmm.android.R
import com.plcoding.translator_kmm.android.core.theme.LightBlue
import com.plcoding.translator_kmm.core.presentation.UiLanguage

@Composable
fun LanguageDropdown(
    language: UiLanguage,
    isOpen: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onSelectLanguage: (UiLanguage) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        DropdownMenu(expanded = isOpen, onDismissRequest = onDismiss) {
            UiLanguage.allLanguages.forEach {
                LanguageDropdownItem(
                    language = it,
                    Modifier.fillMaxWidth()
                ) {
                    onSelectLanguage(it)
                }
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = language.drawableRes,
                contentDescription = language.name,
                Modifier.size(30.dp)
            )
            Spacer(Modifier.size(16.dp))
            Text(text = language.name, color = LightBlue)
            RawIcon(open = isOpen)
        }
    }
}

@Composable
private fun RawIcon(open: Boolean) {
    if (open)
        Icon(
            imageVector = Icons.Default.ArrowDropUp,
            contentDescription = stringResource(id = R.string.close)
        )
    else
        Icon(
            imageVector = Icons.Default.ArrowDropUp,
            contentDescription = stringResource(id = R.string.open)
        )
}

