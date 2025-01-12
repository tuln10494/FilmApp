package com.example.myapplication.ui.screens.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R


@Composable
fun ScreenPaymentDetail(navController: NavController) {
    TitlePay()
    SummaryPay()
    TicketInfo()
    ExpandableDiscountList()
    SummaryInfo()
    PayButton()
}

@Composable
fun TitlePay() {
    Row (
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
    ) {
        Icon(Icons.Default.ArrowBack, contentDescription = "",)
        Text(text="Thanh Toan")
    }
}

@Composable
fun SummaryPay() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
            .padding(top = 6.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.cd_poster),
            contentDescription = "Image 1",
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth(0.2f),
            contentScale = ContentScale.FillBounds
        )
        Column (
            modifier = Modifier.padding(start = 6.dp)
        ) {
            Text(text= "Movie Title", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp))
            Text(text = "Phim duoc phep pho bien den moi lua tuoi", style = TextStyle(fontStyle = FontStyle.Italic))
            Text(text= "Thu 4, 8 thang 1, 2025")
            Text(text = "17:00 - 19:00")
            Text(text= "Movie Title")
            Text(text = "CGV Indochina Plaza")
            Text(text = "Cinema 4")
            Text(text = "Seat: C4")
            Text(text = "Tong thanh toan: 100.000", style = TextStyle(fontWeight = FontWeight.Bold), color = Color.Red)
        }
    }
}

@Composable
fun TicketInfo() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = "THONG TIN VE", modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "So Luong")
            Text(text = "1")
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Tong")
            Text(text = "100.000")
        }
    }
}

data class ListDiscountItem(val title: String)

@Composable
fun ExpandableDiscountList() {
    Text(text = "GIAM GIA", modifier = Modifier
        .fillMaxWidth()
        .background(Color.Gray))
    // Sample data for the list
    val items = listOf(
        ListDiscountItem("CGV Voucher"),
        ListDiscountItem("Coupon"),
        ListDiscountItem("Diem Thuong"),
        ListDiscountItem("The uu tien"),
        ListDiscountItem("Doi tac"),
        ListDiscountItem("Ma Khuyen mai")
    )

    // Keep track of which items are expanded
    val expandedStates =
        remember { mutableStateListOf<Boolean>().apply { repeat(items.size) { add(false) } } }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = AbsoluteAlignment.Left
    ) {
        itemsIndexed(items) { index, item ->
            ExpandableDiscountListItem(
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
fun ExpandableDiscountListItem(item: ListDiscountItem, isExpanded: Boolean, onToggleExpanded: () -> Unit) {
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
                Text(
                    text = item.title,
                    modifier = Modifier.padding(start = 8.dp),
                )

            }
        }
    }
}

@Composable
fun SummaryInfo() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = "TONG KET", modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Tong cong")
            Text(text = "100.000")
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Giam gia")
            Text(text = "0")
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "The qua tang")
            Text(text = "0")
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Con lai")
            Text(text = "100.000")
        }
    }
}


@Composable
fun PayButton() {
    Column(
        modifier = Modifier
            .fillMaxWidth() // Fills the entire screen or parent
            .padding(8.dp), // Optional padding for spacing
        verticalArrangement = Arrangement.Center, // Centers vertically
        horizontalAlignment = Alignment.CenterHorizontally // Centers horizontally
    ) {
        OutlinedButton(
            onClick = { /* Handle click */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                Color.Red
            )
        ) {
            Text(text = "Dong y va tiep tuc")
        }
    }
}