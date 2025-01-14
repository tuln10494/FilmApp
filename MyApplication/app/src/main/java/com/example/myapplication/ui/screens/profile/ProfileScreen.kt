package com.example.myapplication.ui.screens.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.R
import com.example.myapplication.Screen
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD5CCB6))
    ) {
        // Top AppBar
        TopAppBar(
            title = { Text("Thành viên CGV", color = Color.Black) },
            navigationIcon = {
                IconButton(onClick = { /* Handle back action */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                Icon(Icons.Default.Menu, contentDescription = null)
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )

        // Profile Section
        ProfileSection()

        // Membership Progress
        MembershipProgressBar()

        // Options List
        OptionsList(navController)
    }
}

@Composable
fun ProfileSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
        val context = LocalContext.current

        // Launcher để chọn ảnh từ thư viện
        val imagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            selectedImageUri = uri
        }

        Box(contentAlignment = Alignment.BottomEnd) {
            // Hiển thị ảnh đại diện
            Image(
                painter = if (selectedImageUri != null) {
                    rememberAsyncImagePainter(selectedImageUri) // Sử dụng ảnh đã chọn
                } else {
                    painterResource(R.drawable.ic_person) // Ảnh mặc định
                },
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )

            // Nút camera để chọn ảnh
            Icon(
                painter = painterResource(R.drawable.ic_camera),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.White, CircleShape)
                    .padding(4.dp)
                    .clickable {
                        // Khi nhấn vào, mở thư viện chọn ảnh
                        imagePickerLauncher.launch("image/*")
                    }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Trần Quang Huy", style = MaterialTheme.typography.headlineMedium)
    }
}


@Composable
fun OptionsList(navController: NavController) {

    Column(
        modifier = Modifier.background(Color.White)
    ) {

        OptionItem(
            title = "Thông tin Tài khoản",
            icon1 = painterResource(R.drawable.ic_account_box),
            icon2 = painterResource(R.drawable.ic_next),
            onClick={ navController.navigate(Screen.EditProfile.route) }
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
        OptionItem(
            title = "Đổi mật khẩu",
            icon1 = painterResource(R.drawable.ic_lock),
            icon2 = painterResource(R.drawable.ic_next),
            {},
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
        OptionItem(
            title = "Cài đặt mật mã thanh toán",
            icon1 = painterResource(R.drawable.ic_dialpad),
            icon2 = painterResource(R.drawable.ic_next),
            {},
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
        OptionItem(
            title = "Thẻ thành viên",
            icon1 = painterResource(R.drawable.ic_card_membership),
            icon2 = painterResource(R.drawable.ic_next),
            {},
        )
        Divider(color = Color.LightGray, thickness = 1.dp)

    }
    Spacer(modifier = Modifier.height(32.dp))
    Column(
        modifier = Modifier.background(Color.White)
    ) {

        OptionItem(
            title = "Điểm",
            icon1 = painterResource(R.drawable.ic_diamond),
            icon2 = painterResource(R.drawable.ic_next),
            {},
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
        OptionItem(
            title = "Thẻ Quà tặng | Voucher | Coupon",
            icon1 = painterResource(R.drawable.ic_card_giftcard),
            icon2 = painterResource(R.drawable.ic_next),
            {},
        )
        Divider(color = Color.LightGray, thickness = 1.dp)

    }
    Spacer(modifier = Modifier.height(32.dp))
    Column(
        modifier = Modifier.background(Color.White)
    ) {

        OptionItem(
            title = "Lịch sử",
            icon1 = painterResource(R.drawable.ic_history),
            icon2 = painterResource(R.drawable.ic_next),
            {},
        )
        Divider(color = Color.LightGray, thickness = 1.dp)

    }
}

@Composable
fun OptionItem(
    title: String, icon1: Painter, icon2: Painter, onClick: () -> Unit,

    ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable (onClick=onClick )
            .padding(16.dp)
    ) {
        Icon(icon1, contentDescription = null, tint = Color.Red)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Icon(icon2, contentDescription = null, tint = Color(0xFFD5CCB6))

    }
}

@Composable
fun MembershipProgressBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Markers Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MembershipMarker("MEMBER")
            MembershipMarker("VIP")
            MembershipMarker("VVIP")
        }

        // Progress Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(8.dp)
                .background(Color(0xFFD3D3D3), shape = RoundedCornerShape(4.dp))
        )

        // Values Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("0", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.width(46.dp))
            Text("4.000.000", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.width(1.dp)) // Empty space for alignment
            Text("8.000.000", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun MembershipMarker(label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    fontSize = 8.sp
                ),
                textAlign = TextAlign.Center,
            )
        }

        Canvas(modifier = Modifier.size(8.dp)) {
            val trianglePath = Path().apply {
                moveTo(size.width / 2, size.height)
                lineTo(0f, 0f)
                lineTo(size.width, 0f)
                close()
            }
            drawPath(
                path = trianglePath,
                color = Color.Gray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        navController = TODO()
    )
}
