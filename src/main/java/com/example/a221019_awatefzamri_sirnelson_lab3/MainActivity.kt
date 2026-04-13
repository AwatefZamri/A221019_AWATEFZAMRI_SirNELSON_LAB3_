package com.example.a221019_awatefzamri_sirnelson_lab3

import android.os.Bundle
import androidx.compose.ui.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a221019_awatefzamri_sirnelson_lab3.ui.theme.A221019_AWATEFZAMRI_SirNELSON_LAB3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A221019_AWATEFZAMRI_SirNELSON_LAB3Theme {
                Lab3SDGApp()
            }
        }
    }
}

private data class Lab3SDGInfo(
    val title: String,
    val shortDescription: String
)

private val lab3Items = listOf(
    Lab3SDGInfo(
        title = "Save Electricity",
        shortDescription = "Reduce unnecessary electricity usage at home or campus."
    ),
    Lab3SDGInfo(
        title = "Use Public Transport",
        shortDescription = "Choose buses, trains, or carpooling when possible."
    ),
    Lab3SDGInfo(
        title = "Practice Recycling",
        shortDescription = "Separate recyclable waste and reduce single-use materials."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Lab3SDGApp() {
    val completedActions = remember { mutableStateOf(0) }
    val progress = completedActions.value.toFloat() / 10f

    val actionText = remember { mutableStateOf("") }
    val actionLogs = remember { mutableStateListOf<String>() }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            Surface(shadowElevation = 6.dp) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .statusBarsPadding()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Eco,
                            contentDescription = "Responsible Consumption",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "Responsible Consumption Tracker",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Track your daily sustainable actions for SDG 12",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = actionText.value,
                            onValueChange = { actionText.value = it },
                            modifier = Modifier.weight(1f),
                            placeholder = { Text("Log your action...") },
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                                cursorColor = MaterialTheme.colorScheme.tertiary
                            )
                        )

                        IconButton(
                            onClick = {
                                if (actionText.value.isNotBlank()) {
                                    actionLogs.add(actionText.value)
                                    if (completedActions.value < 10) {
                                        completedActions.value += 1
                                    }
                                    actionText.value = ""
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    LinearProgressIndicator(
                        progress = progress,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp),
                        color = Color(0xFFA3BF77),
                        trackColor = Color(0xFFD8E4C0),
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Progress: ${completedActions.value} actions logged",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    if (actionLogs.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Recent: ${actionLogs.last()}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(lab3Items) { item ->
                Lab3ExpandableCard(
                    item = item,
                    onActionDone = {
                        if (completedActions.value < 10) {
                            completedActions.value += 1
                        }
                    },
                    onActionUndo = {
                        if (completedActions.value > 0) {
                            completedActions.value -= 1
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun Lab3ExpandableCard(
    item: Lab3SDGInfo,
    onActionDone: () -> Unit,
    onActionUndo: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var localProgress by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .animateContentSize(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val taskIcon = when (item.title) {
                    "Save Electricity" -> Icons.Default.Bolt
                    "Use Public Transport" -> Icons.Default.DirectionsBus
                    else -> Icons.Default.Recycling
                }

                Icon(
                    imageVector = taskIcon,
                    contentDescription = item.title,
                    tint = MaterialTheme.colorScheme.onSecondary
                )

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.shortDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = {
                            localProgress += 1
                            onActionDone()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add to tracker",
                            tint = MaterialTheme.colorScheme.onTertiary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Add",
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }

                    Button(
                        onClick = {
                            if (localProgress > 0) {
                                localProgress -= 1
                                onActionUndo()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Undo",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Undo",
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }
    }
}