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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.R
import com.example.myapplication.Screen
import com.example.myapplication.data.user.UserInfo
import com.example.myapplication.ui.screens.login.UserViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, userId: Int, userViewModel: UserViewModel) {
    var userInfo by remember { mutableStateOf<UserInfo?>(null) }

    LaunchedEffect(userId) {
        userViewModel.getUserById(userId) { user ->
            userInfo = user
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        // Top AppBar
        TopAppBar(
            title = {
                Text(
                    stringResource(R.string.cgv_member),
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            navigationIcon = {
                IconButton(onClick = { /* Handle back action */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                Icon(Icons.Default.Menu, contentDescription = null)
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.onPrimary)
        )

        // Profile Section
        userInfo?.let { ProfileSection(userInfo = it) }

        // Membership Progress
        MembershipProgressBar()

        // Options List
        OptionsList(navController,userId)
    }
}

@Composable
fun ProfileSection(userInfo: UserInfo) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp)
    ) {
        var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
        val context = LocalContext.current
        val imagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            selectedImageUri = uri
        }

        Box(contentAlignment = Alignment.BottomEnd) {
            Image(
                painter = if (selectedImageUri != null) {
                    rememberAsyncImagePainter(selectedImageUri) // Sử dụng ảnh đã chọn
                } else {
                    rememberAsyncImagePainter(userInfo.userImage) // Ảnh từ database
                },
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.onTertiaryContainer, CircleShape),
                contentScale = ContentScale.Crop
            )

            Icon(
                painter = painterResource(R.drawable.ic_camera),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .background(MaterialTheme.colorScheme.onPrimary, CircleShape)
                    .padding(4.dp)
                    .clickable {
                        imagePickerLauncher.launch(context.getString(R.string.resource_image))
                    }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = userInfo.userName, style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun ProfileSection(userInfo: UserInfo, userViewModel: UserViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp)
    ) {
        var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
        val context = LocalContext.current
        val imagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            selectedImageUri = uri
            uri?.let {
                // Update the user image in the database
                userViewModel.updateUserImage(userInfo.id, it.toString()) { success ->
                    if (success) {
                        // Handle success (e.g., show a success message)
                    } else {
                        // Handle failure (e.g., show an error message)
                    }
                }
            }
        }

        Box(contentAlignment = Alignment.BottomEnd) {
            Image(
                painter = if (selectedImageUri != null) {
                    rememberAsyncImagePainter(selectedImageUri) // Use the selected image
                } else {
                    rememberAsyncImagePainter(userInfo.userImage) // Use the image from the database
                },
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.onTertiaryContainer, CircleShape),
                contentScale = ContentScale.Crop
            )

            Icon(
                painter = painterResource(R.drawable.ic_camera),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .background(MaterialTheme.colorScheme.onPrimary, CircleShape)
                    .padding(4.dp)
                    .clickable {
                        imagePickerLauncher.launch(context.getString(R.string.resource_image))
                    }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = userInfo.userName, style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun OptionsList(navController: NavController,userId: Int) {

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
    ) {

        OptionItem(
            title = stringResource(R.string.account_info),
            icon1 = painterResource(R.drawable.ic_account_box),
            icon2 = painterResource(R.drawable.ic_next),
            onClick = { navController.navigate("${ Screen.EditProfile.route }/$userId") }
        )
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)
        OptionItem(
            title = stringResource(R.string.reset_password),
            icon1 = painterResource(R.drawable.ic_lock),
            icon2 = painterResource(R.drawable.ic_next),
            {},
        )
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)
        OptionItem(
            title = stringResource(R.string.set_up_payment_password),
            icon1 = painterResource(R.drawable.ic_dialpad),
            icon2 = painterResource(R.drawable.ic_next),
            {},
        )
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)
        OptionItem(
            title = stringResource(R.string.card_membership),
            icon1 = painterResource(R.drawable.ic_card_membership),
            icon2 = painterResource(R.drawable.ic_next),
            {},
        )
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)

    }
    Spacer(modifier = Modifier.height(32.dp))
    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
    ) {

        OptionItem(
            title = stringResource(R.string.Point),
            icon1 = painterResource(R.drawable.ic_diamond),
            icon2 = painterResource(R.drawable.ic_next),
            {},
        )
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)
        OptionItem(
            title = stringResource(R.string.card_giftcard),
            icon1 = painterResource(R.drawable.ic_card_giftcard),
            icon2 = painterResource(R.drawable.ic_next),
            {},
        )
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)

    }
    Spacer(modifier = Modifier.height(32.dp))
    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
    ) {

        OptionItem(
            title = stringResource(R.string.history),
            icon1 = painterResource(R.drawable.ic_history),
            icon2 = painterResource(R.drawable.ic_next),
            {},
        )
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)

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
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Icon(icon1, contentDescription = null, tint = Color.Red)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Icon(icon2, contentDescription = null, tint = MaterialTheme.colorScheme.background)

    }
}

@Composable
fun MembershipProgressBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary)
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
            MembershipMarker(stringResource(R.string.member))
            MembershipMarker(stringResource(R.string.vip))
            MembershipMarker(stringResource(R.string.vvip))
        }

        // Progress Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(8.dp)
                .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(4.dp))
        )

        // Values Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(stringResource(R.string.zero), style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.width(46.dp))
            Text(stringResource(R.string.four_millions), style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.width(1.dp)) // Empty space for alignment
            Text(stringResource(R.string.eight_millions), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun MembershipMarker(label: String) {
    val density = LocalDensity.current
    val trianglePath = remember {
        Path().apply {
            with(density) {
                moveTo(4.dp.toPx(), 8.dp.toPx())
                lineTo(0f, 0f)
                lineTo(8.dp.toPx(), 0f)
                close()
            }
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.onTertiaryContainer, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontSize = 8.sp
                ),
                textAlign = TextAlign.Center,
            )
        }


        Canvas(modifier = Modifier.size(8.dp)) {
            drawPath(
                path = trianglePath,
                color = Color.Gray
            )
        }

    }
}



