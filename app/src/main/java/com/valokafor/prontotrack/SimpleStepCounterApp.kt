package com.valokafor

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valokafor.prontotrack.R
import com.valokafor.prontotrack.TrackerViewModel

const val RUN = "Run"
const val WALK = "Walk"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleStepCounterApp(viewModel: TrackerViewModel) {
    val radioOptions = listOf(WALK, RUN)
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    val distanceInMeters = viewModel.distanceInMeters
    val totalCalorie = viewModel.totalCalorie
    val stepCounter = viewModel.stepCounter

//    val updateCounter: (Int) -> Unit = { value ->
//        stepCounter.value = value
//        val distance = stepCounter.value * 0.762
//        val roundedDistance = distance.toBigDecimal().setScale(2, RoundingMode.DOWN)
//        distanceInMeters.value = roundedDistance.toString()
//    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Demo Step Counter")
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onResetState()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Clear,
                            contentDescription = "Hide keyboard"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { viewModel.onDrinkWater() }) {
                        Image(
                            painter = painterResource(id = R.drawable.glass_of_water),
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { viewModel.onEatApple() }) {
                        Image(
                            painter = painterResource(id = R.drawable.apple),
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { viewModel.onEatPizza() }) {
                        Image(
                            painter = painterResource(id = R.drawable.pizza),
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { viewModel.onDrinkCoffee() }) {
                        Image(
                            painter = painterResource(id = R.drawable.coffee),
                            contentDescription = "Localized description",
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            if (selectedOption == RUN) {
                                viewModel.onIncreaseStep(10)
                            } else {
                                viewModel.onIncreaseStep(5)
                            }
                        },
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        if (selectedOption == RUN) {
                            Icon(Icons.Filled.DirectionsRun, "Add button")
                        } else {
                            Icon(Icons.Filled.DirectionsWalk, "Add button")
                        }
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = innerPadding.calculateTopPadding(), horizontal = 16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 100.dp),
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    val borderColor: Color = if (selectedOption == RUN)
                        MaterialTheme.colorScheme.outlineVariant else MaterialTheme.colorScheme.outline
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        ),
                        shape = CircleShape,
                        border = BorderStroke(6.dp, borderColor),
                        modifier = Modifier.size(125.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = stepCounter.value.toString(),
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.Center)
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = "Calorie",
                                style = MaterialTheme.typography.labelSmall
                            )
                            Text(
                                text = totalCalorie.value.toString(),
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                        Column {
                            Text(
                                text = "Distance",
                                style = MaterialTheme.typography.labelSmall
                            )
                            Text(
                                text = distanceInMeters.value,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }

            Column(Modifier.selectableGroup()) {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = {
                                    onOptionSelected(text)
                                },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null // null recommended for accessibility with screenreaders
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

//@Composable
//fun UpdatedActivity(label: String, value: State<String>) {
//    Column {
//        Text(
//            text = label,
//            style = MaterialTheme.typography.labelSmall
//        )
//        Text(
//            text = value.value,
//            style = MaterialTheme.typography.labelMedium
//        )
//    }
//}

@Preview
@Composable
fun CounterPreview() {
   // SimpleStepCounterApp()
}


