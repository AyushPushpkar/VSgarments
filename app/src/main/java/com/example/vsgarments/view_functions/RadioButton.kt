package com.example.vsgarments.view_functions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbardarkblue

data class ToggleableInfo(
    val isChecked: Boolean,
    val nametext : String ,
    val addresstext : String ,
    val pincode : String
)

@Composable
fun RadioButtons(
    options: List<ToggleableInfo>,
    onOptionSelected: (ToggleableInfo) -> Unit ,
    selectedOption : ToggleableInfo?
) {

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        options.forEachIndexed { index ,  option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = selectedOption == option,
                        onClick = {
                            onOptionSelected(option)
                        }
                    )
                    .padding(
                        end = 8.dp,
                        start = 0.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = null , // Handled by Row's selectable
                    colors = RadioButtonDefaults.colors(
                        selectedColor = topbardarkblue,
                        unselectedColor = tintGrey
                    )
                )
                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth() ,
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = option.nametext + " , " + option.pincode,
                        color = textcolorgrey,
                        fontFamily = fontInter,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = option.addresstext,
                        color = tintGrey,
                        fontFamily = fontInter,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
