package com.example.vsgarments.view_functions

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbardarkblue

@Composable
fun CheckBoxWithText(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                onCheckedChange(it)
            } ,
            colors = CheckboxColors(
                uncheckedBorderColor = topbardarkblue,
                uncheckedBoxColor = tintGrey ,
                uncheckedCheckmarkColor = Color.White ,
                checkedBoxColor = topbardarkblue ,
                checkedBorderColor = topbardarkblue ,
                checkedCheckmarkColor = Color.White ,
                disabledCheckedBoxColor = tintGrey ,
                disabledUncheckedBoxColor = tintGrey ,
                disabledUncheckedBorderColor = tintGrey ,
                disabledBorderColor = tintGrey ,
                disabledIndeterminateBorderColor = tintGrey ,
                disabledIndeterminateBoxColor = tintGrey
            )
        )
        Text(text = text , color = topbardarkblue)
    }
}