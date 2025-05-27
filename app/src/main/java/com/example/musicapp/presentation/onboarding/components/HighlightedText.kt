package com.example.musicapp.presentation.onboarding.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle

@Composable
fun HighlightedText(
    modifier: Modifier = Modifier,
    rawText: String,
    spanStyle: SpanStyle,
    textStyle: TextStyle,
    textAlign: TextAlign? = null
) {
    val annotatedString = buildAnnotatedString {
        val regex = "<highlight>(.*?)</highlight>".toRegex()
        var lastIndex = 0

        for (match in regex.findAll(rawText)) {
            val start = match.range.first
            val end = match.range.last + 1
            val beforeText = rawText.substring(lastIndex, start)
            val highlightText = match.groupValues[1]
            append(beforeText)
            withStyle(style = spanStyle) {
                append(highlightText)
            }
            lastIndex = end
        }

        if (lastIndex < rawText.length) {
            append(rawText.substring(lastIndex))
        }
    }

    Text(
        modifier = modifier,
        text = annotatedString,
        style = textStyle,
        textAlign = textAlign
    )
}