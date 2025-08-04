package com.horizon.bancamovil.components.fontStyles

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.horizon.bancamovil.ui.theme.abelFont
import com.horizon.bancamovil.ui.theme.robotoFont

@Composable
fun HeadLineLarge(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 22.sp,
    fontWeight: FontWeight = FontWeight.W900,
    letterSpacing: TextUnit = 2.sp,
    fontFamily: FontFamily = abelFont,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    Text(
        text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        fontFamily = fontFamily,
        color = color,
        modifier = modifier
    )
}

@Composable
fun BodyLarge(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.W800,
    letterSpacing: TextUnit = 2.sp,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    Text(
        text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        fontFamily = robotoFont,
        color = color,
        modifier = modifier
    )
}

@Composable
fun BodyMedium(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 13.sp,
    fontWeight: FontWeight = FontWeight.W400,
    letterSpacing: TextUnit = 1.sp,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontFamily: FontFamily = robotoFont
) {
    Text(
        text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        fontFamily = fontFamily,
        color = color,
        modifier = modifier
    )
}

@Composable
fun BodySmall(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 11.sp,
    fontWeight: FontWeight = FontWeight.W200,
    letterSpacing: TextUnit = 1.sp,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontFamily: FontFamily = robotoFont
) {
    Text(
        text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        fontFamily = fontFamily,
        color = color,
        modifier = modifier
    )
}