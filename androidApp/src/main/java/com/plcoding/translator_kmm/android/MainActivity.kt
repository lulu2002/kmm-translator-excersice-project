package com.plcoding.translator_kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.translator_kmm.Greeting
import com.plcoding.translator_kmm.android.core.theme.darkColors
import com.plcoding.translator_kmm.android.core.theme.lightColors

@Composable
fun TranslatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColors else lightColors
    val sfProText = FontFamily(
        Font(R.font.sf_pro_text_regular, weight = FontWeight.Normal),
        Font(R.font.sf_pro_text_medium, weight = FontWeight.Medium),
        Font(R.font.sf_pro_text_bold, weight = FontWeight.Bold),
    )
    val typography = Typography(
        h1 = sfProText.createHeading(30.sp),
        h2 = sfProText.createHeading(24.sp),
        h3 = sfProText.createHeading(20.sp),
        body1 = sfProText.createBodyText(14.sp),
        body2 = sfProText.createBodyText(12.sp)
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

private fun FontFamily.createHeading(size: TextUnit) = TextStyle(
    fontFamily = this,
    fontWeight = FontWeight.Bold,
    fontSize = size
)

private fun FontFamily.createBodyText(size: TextUnit) = TextStyle(
    fontFamily = this,
    fontWeight = FontWeight.Normal,
    fontSize = size
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TranslatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    TranslatorTheme {
    }
}
