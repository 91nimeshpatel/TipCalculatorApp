package com.nimeshpatel.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nimeshpatel.tipcalculator.ui.theme.TipCalculatorAppTheme
import com.nimeshpatel.tipcalculator.widgets.CustomIcon
import com.nimeshpatel.tipcalculator.widgets.CustomInputField.OutlineInputField
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            myApp {

            }
        }
    }
}


@Composable
fun myApp(conent: @Composable () -> Unit) {
    TipCalculatorAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            BodyContent()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BodyContent() {
    val totalPerPersonState = remember {
        mutableStateOf(0.0)
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TotalSection(totalPerPersonState = totalPerPersonState)
        EnterBillAmountSection(totalPerPersonState = totalPerPersonState)
    }
}


@Composable
fun EnterBillAmountSection(totalPerPersonState: MutableState<Double>) {
    val totalBillState = remember {
        mutableStateOf("")
    }
    val splitBillPerPerson = remember {
        mutableStateOf(1)
    }
    val tipAmountState = remember {
        mutableStateOf(0.0)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 20.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column {
            OutlineInputField(
                modifier = Modifier.fillMaxWidth(),
                valueState = totalBillState,
                labelId = "Enter Bill",
                imageVector = Icons.Rounded.AttachMoney,
                imageVectorDescription = "Money Icon",
                onValueChange = {
                    calculateTotalPerPerson(
                        splitBillPerPerson = splitBillPerPerson,
                        totalBillState = totalBillState,
                        tipAmountState = tipAmountState,
                        totalPerPersonState = totalPerPersonState

                    )
                }
            )
            SplitBillSection(
                splitBillPerPerson = splitBillPerPerson,
                totalBillState = totalBillState,
                tipAmountState = tipAmountState,
                totalPerPersonState = totalPerPersonState
            )
            TipSection(
                splitBillPerPerson = splitBillPerPerson,
                totalBillState = totalBillState,
                tipAmountState = tipAmountState,
                totalPerPersonState = totalPerPersonState
            )
        }

    }
}

@Composable
fun TipSection(
    splitBillPerPerson: MutableState<Int>,
    totalBillState: MutableState<String>,
    tipAmountState: MutableState<Double>,
    totalPerPersonState: MutableState<Double>
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 5.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Tip",
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
            Text(
                text = "$${tipAmountState.value}",
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }

        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
            text = "${tipAmountState.value.toInt()}%",
            style = TextStyle(
                fontSize = 18.sp
            )
        )

        Slider(
            value = (tipAmountState.value.toFloat()),
            onValueChange = {
                tipAmountState.value = it.toDouble()
                calculateTotalPerPerson(
                    splitBillPerPerson = splitBillPerPerson,
                    totalBillState = totalBillState,
                    tipAmountState = tipAmountState,
                    totalPerPersonState = totalPerPersonState

                )
            },
            steps = 7,
            valueRange = 0f..100f,
        )
    }
}

@Composable
fun SplitBillSection(
    splitBillPerPerson: MutableState<Int>,
    totalBillState: MutableState<String>,
    tipAmountState: MutableState<Double>,
    totalPerPersonState: MutableState<Double>
) {
    Row(
        modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "Split",
            style = TextStyle(
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.width(120.dp))
        Row(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            CustomIcon.RoundedCornerIcon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Decrease",
                onClick = {
                    if (splitBillPerPerson.value > 1)
                        splitBillPerPerson.value--

                    calculateTotalPerPerson(
                        splitBillPerPerson = splitBillPerPerson,
                        totalBillState = totalBillState,
                        tipAmountState = tipAmountState,
                        totalPerPersonState = totalPerPersonState

                    )
                })
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 10.dp),
                text = splitBillPerPerson.value.toString(),
                style = TextStyle(
                    fontSize = 18.sp
                )
            )

            CustomIcon.RoundedCornerIcon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase",
                onClick = {
                    if (splitBillPerPerson.value <= 10)
                        splitBillPerPerson.value++

                    calculateTotalPerPerson(
                        splitBillPerPerson = splitBillPerPerson,
                        totalBillState = totalBillState,
                        tipAmountState = tipAmountState,
                        totalPerPersonState = totalPerPersonState
                    )
                })
        }

    }
}

fun calculateTotalPerPerson(
    splitBillPerPerson: MutableState<Int>,
    totalBillState: MutableState<String>,
    tipAmountState: MutableState<Double>,
    totalPerPersonState: MutableState<Double>
) {
    totalPerPersonState.value = if (totalBillState.value.isNotEmpty()) {
        (totalBillState.value.toDouble() + tipAmountState.value) / splitBillPerPerson.value
    } else {
        0.0
    }
}

@Composable
fun TotalSection(
    modifier: Modifier = Modifier,
    totalPerPersonState: MutableState<Double>
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = RoundedCornerShape(CornerSize(12.dp))),
        color = Color(0xFFE9D7F7)
    ) {
        Column(
            modifier = modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Total Per Person",
                style = TextStyle(
                    fontSize = 22.sp, fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = String.format(Locale.getDefault(), "$%.2f", totalPerPersonState.value),
                style = TextStyle(
                    fontSize = 22.sp, fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}
