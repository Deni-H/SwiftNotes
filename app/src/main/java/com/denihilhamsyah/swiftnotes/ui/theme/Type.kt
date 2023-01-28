package com.denihilhamsyah.swiftnotes.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.denihilhamsyah.swiftnotes.R


private val MulishFont = FontFamily(
    Font(R.font.mulish_bold, FontWeight.Bold),
    Font(R.font.mulish_semi_bold, FontWeight.SemiBold),
    Font(R.font.mulish_regular, FontWeight.Normal),
    Font(R.font.mulish_light, FontWeight.Light)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = MulishFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    body2 = TextStyle(
        fontFamily = MulishFont,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp

    ),
    button = TextStyle(
        fontFamily = MulishFont,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = MulishFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)