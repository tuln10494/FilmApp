package com.example.myapplication.ui.screens.login

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var region by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var preferTheater by remember { mutableStateOf("") }
    var isAgreement1Checked by remember { mutableStateOf(false) }
    var isAgreement2Checked by remember { mutableStateOf(false) }
    var isAgreement3Checked by remember { mutableStateOf(false) }
    var isAgreement4Checked by remember { mutableStateOf(false) }

    val isFormValid = name.isNotBlank() && phoneNumber.isNotBlank() && email.isNotBlank() &&
            password.isNotBlank() && region.isNotBlank() && district.isNotBlank() && preferTheater.isNotBlank() &&
            isAgreement1Checked && isAgreement2Checked && isAgreement3Checked && isAgreement4Checked

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Image Section
            Image(
                painter = painterResource(id = R.drawable.login), // Replace with your image resource
                contentDescription = "Header Image",
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
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name *") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Phone number *") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email *") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password *") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    OutlinedTextField(
                        value = dateOfBirth,
                        onValueChange = { dateOfBirth = it },
                        label = { Text("Date of birth") },
                        modifier = Modifier.weight(1f).padding(end = 4.dp)
                    )

                    OutlinedTextField(
                        value = gender,
                        onValueChange = { gender = it },
                        label = { Text("Gender") },
                        modifier = Modifier.weight(1f).padding(start = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
//
//                OutlinedTextField(
//                    value = region,
//                    onValueChange = { region = it },
//                    label = { Text("Region *") },
//                    modifier = Modifier.fillMaxWidth()
//                )
                OutlinedTextField(
                    value = region,
                    onValueChange = { region = it },
                    label = { Text("Region *") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(BorderStroke(1.dp, Color.Transparent), shape = RectangleShape)
                        .padding(0.dp), // Giảm khoảng padding xung quanh text field
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,  // Ẩn border trên khi focus
                        unfocusedBorderColor = Color.Transparent, // Ẩn border trên khi không focus
//                        textColor = Color.Black  // Màu chữ
                    ),
                    // Tùy chỉnh Border
                    shape = RectangleShape,
                    keyboardOptions = KeyboardOptions.Default
                )


                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = district,
                    onValueChange = { district = it },
                    label = { Text("District *") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = preferTheater,
                    onValueChange = { preferTheater = it },
                    label = { Text("Prefer Theater *") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("* Required field", color = Color.Gray, fontSize = 12.sp)

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Checkbox(checked = isAgreement1Checked, onCheckedChange = { isAgreement1Checked = it })
                    Text(
                        text = "By clicking the Register button below, I agree to allow CGV Vietnam to process my personal data in accordance with purposes that CGV Vietnam has announced in the Privacy Policy.",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Checkbox(checked = isAgreement2Checked, onCheckedChange = { isAgreement2Checked = it })
                    Text(
                        text = "Personal information provided here is accurate and matches information in ID card and/or Birth Certificate. Also, the email provided here is accurate and under my sole control.",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Checkbox(checked = isAgreement3Checked, onCheckedChange = { isAgreement3Checked = it })
                    Text(
                        text = "If the information does not match, change or update is not supported and you cannot receive Member Benefits.",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Checkbox(checked = isAgreement4Checked, onCheckedChange = { isAgreement4Checked = it } )
                    Text(
                        text = "I agree to the CGV’s Terms of Use.",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = if (isFormValid) Color(0xFFB71C1C) else Color.Gray),
                    enabled = isFormValid
                ) {
                    Text(text = "REGISTER", color = Color.White)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
