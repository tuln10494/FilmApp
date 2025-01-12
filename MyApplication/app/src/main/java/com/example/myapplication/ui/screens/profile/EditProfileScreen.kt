package com.example.myapplication.ui.screens.profile

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD3D3C5))
    ) {
        // Top Bar
        TopAppBar(
            title = { Text("Thông tin Tài khoản", color = Color.Black) },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                }
            },
        )

        // Main Content
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            // Additional Information Section
            UserInfoScreen()

            Spacer(modifier = Modifier.height(16.dp))

            // Agreement Section
            AgreementSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            ActionButtons()
        }
    }
}

@Composable
fun UserInfoScreen() {
    var name by remember { mutableStateOf("Trần Huy") }
    var isEditingName by remember { mutableStateOf(false) }

    var phone by remember { mutableStateOf("0123456789") }
    var isEditingPhone by remember { mutableStateOf(false) }

    var dateOfBirth by remember { mutableStateOf("Chọn ngày sinh") }
    val context = LocalContext.current

    var gender by remember { mutableStateOf("Chọn giới tính") }
    var expandedGender by remember { mutableStateOf(false) }

    var favoriteCinema by remember { mutableStateOf("Chọn rạp yêu thích") }
    var expandedCinema by remember { mutableStateOf(false) }
    val cinemaList = listOf("Rạp A", "Rạp B", "Rạp C")
    var province by remember { mutableStateOf("Chọn tỉnh/thành phố") }
    var expandedProvince by remember { mutableStateOf(false) }
    val provinceList = listOf("Hà Nội", "Phú Thọ")
    var district by remember { mutableStateOf("Chọn huyện/quận") }
    var expandedDistrict by remember { mutableStateOf(false) }
    val districtList = listOf("quận Cầu Giấy", "quận Nam Từ Liêm")
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .clickable(onClick = { focusManager.clearFocus() })
            .fillMaxSize(),
    ) {

        SectionHeader(title = "TÀI KHOẢN CỦA TÔI LÀ...")
        InfoRow(label = "qua*************com", "")
        SectionHeader(title = "THÔNG TIN THÊM")
        //Name
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { focusManager.clearFocus() })
                .background(color = Color.White)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "Họ tên",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            if (isEditingName) {
                BasicTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.weight(1f).focusRequester(focusRequester),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        textAlign = TextAlign.End
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { isEditingName = !isEditingName }),

                    )
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            } else {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .clickable { isEditingName = !isEditingName }
                )
            }
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
        //Date
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { focusManager.clearFocus() })
                .background(color = Color.White)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "Ngày sinh",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = dateOfBirth,
                style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.End),
                modifier = Modifier
                    .weight(1f).focusRequester(focusRequester)
                    .clickable {
                        focusManager.clearFocus()
                        val datePicker = DatePickerDialog(
                            context,
                            { _, year, month, day ->
                                dateOfBirth = "$day/${month + 1}/$year"
                            },
                            2000, // Default year
                            0, // Default month (January)
                            1 // Default day
                        )
                        datePicker.show()

                    }
            )
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
        //Gene
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .clickable(onClick = { focusManager.clearFocus() })
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 8.dp)
        ) {
            Text(
                text = "Giới tính",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            Row {
                Text(
                    text = gender,
                    style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.End),
                    modifier = Modifier
                        .wrapContentWidth().focusRequester(focusRequester)
                        .clickable {
                            focusManager.clearFocus()
                            expandedGender = true
                        }
                )
                IconButton(modifier = Modifier.size(20.dp), onClick = {
                    focusManager.clearFocus()
                    expandedGender = true
                }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    DropdownMenu(
                        expanded = expandedGender,
                        onDismissRequest = { expandedGender = false }
                    ) {
                        DropdownMenuItem(onClick = {
                            gender = "Nam"
                            expandedGender = false
                        }, text = { Text("Nam") })
                        DropdownMenuItem(onClick = {
                            gender = "Nữ"
                            expandedGender = false
                        }, text = { Text("Nữ") })
                        DropdownMenuItem(onClick = {
                            gender = "Không tiết lộ"
                            expandedGender = false
                        }, text = { Text("Không tiết lộ") })
                    }
                }
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }

            }
        }
        Divider(color = Color.LightGray, thickness = 1.dp)

        //Cinema
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .clickable(onClick = { focusManager.clearFocus() })
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 8.dp)
        ) {
            Text(
                text = "Rạp yêu thích",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            Row {
                Text(
                    text = favoriteCinema,
                    style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.End),
                    modifier = Modifier
                        .wrapContentWidth().focusRequester(focusRequester)
                        .clickable {
                            focusManager.clearFocus()
                            expandedCinema = true
                        }
                )
                IconButton(modifier = Modifier.size(20.dp), onClick = {
                    focusManager.clearFocus()
                    expandedCinema = true
                }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    DropdownMenu(
                        expanded = expandedCinema,
                        onDismissRequest = { expandedCinema = false }
                    ) {
                        cinemaList.forEach { cinema ->
                            DropdownMenuItem(onClick = {
                                favoriteCinema = cinema
                                expandedCinema = false
                            }, text = { Text(cinema) })
                        }
                    }
                }
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }

            }
        }
        Divider(color = Color.LightGray, thickness = 1.dp)

        SectionHeader(title = "LIÊN HỆ")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .clickable(onClick = { focusManager.clearFocus() })
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "SĐT",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            if (isEditingPhone) {
                BasicTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    modifier = Modifier.weight(1f).focusRequester(focusRequester),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        textAlign = TextAlign.End
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { isEditingPhone = false }),

                    )
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            } else {
                Text(
                    text = phone,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .clickable { isEditingPhone = true }
                )
            }
        }
        Divider(color = Color.LightGray, thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { focusManager.clearFocus() })
                .background(color = Color.White)
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 8.dp)
        ) {
            Text(
                text = "Tỉnh/Thành phố",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            Row {
                Text(
                    text = province,
                    style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.End),
                    modifier = Modifier
                        .wrapContentWidth().focusRequester(focusRequester)
                        .clickable {
                            focusManager.clearFocus()
                            expandedProvince = true
                        }
                )
                IconButton(modifier = Modifier.size(20.dp), onClick = {
                    focusManager.clearFocus()
                    expandedProvince = true
                }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    DropdownMenu(
                        expanded = expandedProvince,
                        onDismissRequest = { expandedProvince = false }
                    ) {
                        provinceList.forEach { province1 ->
                            DropdownMenuItem(onClick = {
                                province = province1
                                expandedProvince = false
                            }, text = { Text(province1) })
                        }
                    }
                }
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }

            }
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .clickable(onClick = { focusManager.clearFocus() })
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 8.dp)
        ) {
            Text(
                text = "Huyện/Quận",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            Row {
                Text(
                    text = district,
                    style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.End),
                    modifier = Modifier
                        .wrapContentWidth().focusRequester(focusRequester)
                        .clickable { expandedDistrict = true }
                )
                IconButton(modifier = Modifier.size(20.dp), onClick = { expandedDistrict = true }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    DropdownMenu(
                        expanded = expandedDistrict,
                        onDismissRequest = { expandedDistrict = false }
                    ) {
                        districtList.forEach { district1 ->
                            DropdownMenuItem(onClick = {
                                district = district1
                                expandedDistrict = false
                            }, text = { Text(district1) })
                        }
                    }
                }
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }

            }
        }
        Divider(color = Color.LightGray, thickness = 1.dp)

    }
}

@Composable
fun SectionHeader(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(title, color = Color.Gray, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = value,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.End
        )
    }
    Divider(color = Color.LightGray, thickness = 1.dp)
}

@Composable
fun AgreementSection() {
    Text(
        text = "Khi đăng ký, tôi đã xem xét và đồng ý với Điều Khoản Sử Dụng và Chính Sách Bảo Mật của CGV Việt Nam.",
        color = Color.Gray,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ActionButtons() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Button(
            onClick = { /* Handle update info */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Cập nhật thông tin", color = Color.White)
        }
        Text(
            text = "Xóa tài khoản",
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* Handle delete account */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(
        navController = TODO()
    )
}
