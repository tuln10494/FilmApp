package com.example.myapplication.ui.screens.ticket

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

@Composable
fun ScreenBookTicket(navController: NavController) {
    Column {
        TitleAndMenu()
        ImageCover()
        ExpandableList()
    }
}

@Composable
fun TitleAndMenu() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Text(text = "Movie Title")
        Icon(Icons.Default.Menu, contentDescription = "")
    }
}

@Composable
fun ImageCover() {
    Image(
        painter = painterResource(id = R.drawable.cd_poster),
        contentDescription = "Image 2",
        modifier = Modifier
            .fillMaxHeight(0.25f)
            .fillMaxWidth(),
        contentScale = ContentScale.FillBounds
    )
}

data class ListItem(val title: String, val content: String)

@Composable
fun ExpandableList() {
    // Sample data for the list
    val items = listOf(
        ListItem("CGV Vincom Sky Lake", "This is the content for item 1"),
        ListItem("CGV Indochina Plaza", "This is the content for item 2"),
        ListItem("CGV Tran Duy Hung", "This is the content for item 1"),
        ListItem("CGV Aeon Mall", "This is the content for item 2"),
        ListItem("CGV Nguyen Chi Thanh", "This is the content for item 1"),
        ListItem("CGV Bac Tu Liem", "This is the content for item 2"),
        ListItem("CGV Ho Guom Plaza", "This is the content for item 1"),
        ListItem("CGV Xuan Dieu", "This is the content for item 2"),
        ListItem("CGV Trang Tien", "This is the content for item 3")
    )

    // Keep track of which items are expanded
    val expandedStates =
        remember { mutableStateListOf<Boolean>().apply { repeat(items.size) { add(false) } } }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = AbsoluteAlignment.Left
    ) {
        itemsIndexed(items) { index, item ->
            ExpandableListItem(
                item = item,
                isExpanded = expandedStates[index],
                onToggleExpanded = {
                    // Toggle the expanded state for the clicked item
                    expandedStates[index] = !expandedStates[index]
                }
            )
        }
    }
}

@Composable
fun ExpandableListItem(item: ListItem, isExpanded: Boolean, onToggleExpanded: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onToggleExpanded),
    ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.title,
                    modifier = Modifier.padding(start = 8.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp)
                )
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            if (isExpanded) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedButton(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "11:00")
                    }
                    OutlinedButton(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "11:00")
                    }
                    OutlinedButton(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "11:00")
                    }
                    OutlinedButton(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "11:00")
                    }
                }

            }
        }
    }
}