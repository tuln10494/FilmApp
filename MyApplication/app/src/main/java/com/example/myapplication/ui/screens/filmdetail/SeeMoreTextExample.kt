package com.example.myapplication.ui.screens.filmdetail

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SeeMoreTextExample() {
    Text(
        text = "Trò chơi con mực là một bộ phim truyền hình Hàn Quốc do Hwang Dong-hyuk viết kịch bản và đạo diễn. " +
                "Phim được Netflix phát hành trực tuyến vào ngày 17 tháng 9 năm 2021. " +
                "Phim kể về một nhóm 456 người mạo hiểm tính mạng trong 6 trò chơi",
        modifier = Modifier.padding(start = 8.dp).fillMaxHeight(0.2f)
    )
}