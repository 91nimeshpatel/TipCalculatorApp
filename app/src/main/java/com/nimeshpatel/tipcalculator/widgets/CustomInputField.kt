package com.nimeshpatel.tipcalculator.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Nimesh Patel on 6/2/2024.
 * Purpose:
 */
object CustomInputField {

    @Composable
    fun OutlineInputField(
        modifier: Modifier = Modifier,
        valueState: MutableState<String>,
        onValueChange: (String) -> Unit,
        labelId: String,
        enabled: Boolean = true,
        isSingleLine: Boolean = true,
        keyboardType: KeyboardType = KeyboardType.Number,
        imeAction: ImeAction = ImeAction.Next,
        onAction: KeyboardActions = KeyboardActions.Default,
        imageVector: ImageVector? = null,
        imageVectorDescription: String? = null,
        maxLength: Int = 5,
        textStyle: TextStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        ),
    ) {
        OutlinedTextField(
            value = valueState.value,
            onValueChange = {
                if (it.length <= maxLength) {
                    valueState.value = it.trim()
                    onValueChange(valueState.value)
                }
            },
            label = { Text(text = labelId, Modifier.fillMaxWidth()) },
            leadingIcon = {
                if (imageVector != null && imageVectorDescription != null) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = imageVectorDescription,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            textStyle = textStyle,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = onAction,
            modifier = modifier.padding(20.dp),
            singleLine = isSingleLine
        )
    }
}