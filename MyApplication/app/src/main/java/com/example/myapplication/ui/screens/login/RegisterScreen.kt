package com.example.myapplication.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Dialog

@Composable
fun RegisterScreen() {
    val context = LocalContext.current
    val userFormState = remember { mutableStateOf(UserFormState()) }

    val showDatePicker = remember { mutableStateOf(false) }
    val showGenderPicker = remember { mutableStateOf(false) }
    val showTheaterDialog = remember { mutableStateOf(false) }
    val showRegionDialog = remember { mutableStateOf(false) }
    val showDistrictDialog = remember { mutableStateOf(false) }

    val regionList = listOf("Hà Nội", "TP.HCM", "Đà Nẵng")
    val districtMap = mapOf(
        "Hà Nội" to listOf("Ba Đình", "Hoàn Kiếm", "Hai Bà Trưng", "Đống Đa", "Tây Hồ"),
        "TP.HCM" to listOf("Quận 1", "Quận 3", "Quận 4", "Quận 5", "Quận 7"),
        "Đà Nẵng" to listOf("Hải Châu", "Thanh Khê", "Sơn Trà", "Ngũ Hành Sơn")
    )
    val theaterList = listOf(
        "CGV Vincom Center Bà Triệu",
        "CGV Vincom Royal City",
        "CGV Mipec Tower",
        "CGV Hồ Gươm Plaza",
        "CGV Vincom Times City",
        "CGV Tràng Tiền Plaza",
        "Beta Cinemas Thanh Xuân",
        "Beta Cinemas Mỹ Đình",
        "Lotte Cinema Hà Đông",
        "Lotte Cinema Long Biên"
    )

    val currentFormState = userFormState.value

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = stringResource(R.string.header_image_desc),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Register Form

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.Start
            ) {
                val formState = userFormState.value

                // Name Field
                OutlinedTextField(
                    value = formState.name,
                    onValueChange = { userFormState.value = formState.copy(name = it) },
                    label = { Text(stringResource(R.string.name_label)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Phone Number Field
                OutlinedTextField(
                    value = formState.phoneNumber,
                    onValueChange = { userFormState.value = formState.copy(phoneNumber = it) },
                    label = { Text(stringResource(R.string.phone_number_label)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Email Field
                OutlinedTextField(
                    value = formState.email,
                    onValueChange = { userFormState.value = formState.copy(email = it) },
                    label = { Text(stringResource(R.string.email_label)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Password Field
                OutlinedTextField(
                    value = formState.password,
                    onValueChange = { userFormState.value = formState.copy(password = it) },
                    label = { Text(stringResource(R.string.password_label)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Date of Birth and Gender Fields
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = formState.dateOfBirth,
                        onValueChange = { userFormState.value = formState.copy(dateOfBirth = it) },
                        label = { Text(stringResource(R.string.dob_label)) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp)
                            .fillMaxWidth(),
                        readOnly = true,
                        interactionSource = remember { MutableInteractionSource() }
                            .also { interactionSource ->
                                LaunchedEffect(interactionSource) {
                                    interactionSource.interactions.collect {
                                        if (it is PressInteraction.Release) {
                                            showDatePicker.value = true
                                        }
                                    }
                                }
                            }
                    )

                    OutlinedTextField(
                        value = formState.gender,
                        onValueChange = { userFormState.value = formState.copy(gender = it) },
                        label = { Text(stringResource(R.string.gender_label)) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp),
                        readOnly = true,
                        interactionSource = remember { MutableInteractionSource() }
                            .also { interactionSource ->
                                LaunchedEffect(interactionSource) {
                                    interactionSource.interactions.collect {
                                        if (it is PressInteraction.Release) {
                                            showGenderPicker.value = true
                                        }
                                    }
                                }
                            }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = formState.region,
                    onValueChange = { },
                    label = { Text("Khu vực") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    readOnly = true,
                    interactionSource = remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) {
                                        showRegionDialog.value = true
                                    }
                                }
                            }
                        }
                )

                Spacer(modifier = Modifier.height(8.dp))


                OutlinedTextField(
                    value = currentFormState.district,
                    onValueChange = { },
                    label = { Text("Quận/Huyện") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    readOnly = true,
                    interactionSource = remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) {
                                            showDistrictDialog.value = true
                                    }
                                }
                            }
                        },
                    enabled = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Thay thế OutlinedTextField của Prefer Theater hiện tại
                OutlinedTextField(
                    value = formState.preferTheater,
                    onValueChange = { },
                    label = { Text(stringResource(R.string.prefer_theater_label)) },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    interactionSource = remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) {
                                        showTheaterDialog.value = true
                                    }
                                }
                            }
                        }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    stringResource(R.string.required_field_note),
                    color = Color.Gray,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Agreement Checkboxes
                formState.agreements.keys.forEach { key ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Checkbox(
                            checked = formState.agreements[key] == true,
                            onCheckedChange = { checked ->
                                userFormState.value = formState.copy(
                                    agreements = formState.agreements.toMutableMap().apply {
                                        this[key] = checked
                                    }
                                )
                            }
                        )
                        Text(
                            text = when (key) {
                                "agreement1" -> stringResource(R.string.agreement1_text)
                                "agreement2" -> stringResource(R.string.agreement2_text)
                                "agreement3" -> stringResource(R.string.agreement3_text)
                                "agreement4" -> stringResource(R.string.agreement4_text)
                                else -> ""
                            },
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Register Button
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            """
                            Name: ${formState.name}
                            Phone: ${formState.phoneNumber}
                            Email: ${formState.email}
                            Password: ${formState.password}
                            DOB: ${formState.dateOfBirth}
                            Gender: ${formState.gender}
                            Region: ${formState.region}
                            District: ${formState.district}
                            Prefer Theater: ${formState.preferTheater}
                        """.trimIndent(),
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (formState.isFormValid()) Color(
                            0xFFB71C1C
                        ) else Color.Gray
                    ),
                    enabled = formState.isFormValid()
                ) {
                    Text(text = stringResource(R.string.register_button), color = Color.White)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
    // Hiển thị DatePicker Dialog
    if (showDatePicker.value) {
        DatePickerDialog(
            onDateSelected = { date ->
                userFormState.value = userFormState.value.copy(dateOfBirth = date)
                showDatePicker.value = false
            },
            onDismissRequest = {
                showDatePicker.value = false
            }
        )
    }
// Hiển thị GenderPicker Dialog
    if (showGenderPicker.value) {
        GenderPickerDialog(
            selectedGender = userFormState.value.gender,
            options = listOf("Male", "Female"),
            onGenderSelected = { gender ->
                userFormState.value = userFormState.value.copy(gender = gender)
                showGenderPicker.value = false
            },
            onDismissRequest = { showGenderPicker.value = false }
        )
    }
    // Thêm dialogs
    if (showRegionDialog.value) {
        AlertDialog(
            onDismissRequest = { showRegionDialog.value = false },
            title = { Text("Chọn Khu vực") },
            text = {
                LazyColumn {
                    items(regionList) { region ->
                        Text(
                            text = region,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    userFormState.value = userFormState.value.copy(
                                        region = region,
                                        district = ""
                                    )
                                    showRegionDialog.value = false
                                }
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            },
            confirmButton = { }
        )
    }

    if (showDistrictDialog.value) {
        AlertDialog(
            onDismissRequest = { showDistrictDialog.value = false },
            title = { Text("Chọn Quận/Huyện") },
            text = {
                LazyColumn {
                    items(districtMap[currentFormState.region] ?: emptyList()) { district ->
                        Text(
                            text = district,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    userFormState.value = userFormState.value.copy(
                                        district = district
                                    )
                                    showDistrictDialog.value = false
                                }
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            },
            confirmButton = { }
        )
    }
    // Thêm vào phần cuối cùng của RegisterScreen, cùng vị trí với các dialog khác
    if (showTheaterDialog.value) {
        AlertDialog(
            onDismissRequest = { showTheaterDialog.value = false },
            title = { Text("Chọn Rạp Yêu Thích") },
            text = {
                LazyColumn {
                    items(theaterList) { theater ->
                        Text(
                            text = theater,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    userFormState.value = userFormState.value.copy(
                                        preferTheater = theater
                                    )
                                    showTheaterDialog.value = false
                                }
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            },
            confirmButton = { }
        )
    }
}


