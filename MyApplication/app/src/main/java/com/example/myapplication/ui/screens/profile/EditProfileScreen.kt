package com.example.myapplication.ui.screens.profile

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.user.UserInfo
import com.example.myapplication.ui.screens.login.UserFormState
import com.example.myapplication.ui.screens.login.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController, userId: Int, userViewModel: UserViewModel) {
    var userInfo by remember { mutableStateOf<UserInfo?>(null) }
    val userFormState = remember { mutableStateOf(UserFormState()) }

    LaunchedEffect(userId) {
        userViewModel.getUserById(userId) { user ->
            userInfo = user
            user?.let {
                userFormState.value = UserFormState(
                    name = it.userName,
                    phoneNumber = it.userPhoneNumber,
                    email = it.email,
                    dateOfBirth = it.userBirthday,
                    gender = if (it.userGender == 0) "Male" else "Female",
                    region = it.userProvince,
                    district = it.userDistrict,
                    preferTheater = it.userFavoriteCinema
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        // Top Bar
        TopAppBar(
            title = { Text(text = stringResource(R.string.account_info), color = MaterialTheme.colorScheme.onSecondary) },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onSecondary)
                }
            },
        )

        // Main Content
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            // Additional Information Section
            UserInfoScreen(userFormState.value)

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
fun UserInfoScreen(userFormState: UserFormState) {
    var name by remember { mutableStateOf(userFormState.name) }
    var isEditingName by remember { mutableStateOf(false) }

    var phone by remember { mutableStateOf(userFormState.phoneNumber) }
    var isEditingPhone by remember { mutableStateOf(false) }

    var dateOfBirth by remember { mutableStateOf(userFormState.dateOfBirth) }
    val context = LocalContext.current

    var gender by remember { mutableStateOf(userFormState.gender) }
    var expandedGender by remember { mutableStateOf(false) }

    var favoriteCinema by remember { mutableStateOf(userFormState.preferTheater) }
    var expandedCinema by remember { mutableStateOf(false) }
    val cinemaList = listOf("Rạp A", "Rạp B", "Rạp C")
    var province by remember { mutableStateOf(userFormState.region) }
    var expandedProvince by remember { mutableStateOf(false) }
    val provinceList = listOf("Hà Nội", "Phú Thọ")
    var district by remember { mutableStateOf(userFormState.district) }
    var expandedDistrict by remember { mutableStateOf(false) }
    val districtList = listOf("quận Cầu Giấy", "quận Nam Từ Liêm")
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .clickable(onClick = { focusManager.clearFocus() })
            .fillMaxSize(),
    ) {
        SectionHeader(title = stringResource(R.string.my_account_is))
        InfoRow(label = userFormState.email, "")
        SectionHeader(title = stringResource(R.string.more_info))
        // Name
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { focusManager.clearFocus() })
                .background(color = MaterialTheme.colorScheme.onPrimary)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.full_name),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            if (isEditingName) {
                BasicTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester),
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
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)
        // Date of Birth
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { focusManager.clearFocus() })
                .background(color = Color.White)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.birthday),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = dateOfBirth,
                style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.End),
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester)
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
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)
        // Gender
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onPrimary)
                .clickable(onClick = { focusManager.clearFocus() })
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.gender),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            Row {
                Text(
                    text = gender,
                    style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.End),
                    modifier = Modifier
                        .wrapContentWidth()
                        .focusRequester(focusRequester)
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
                            gender = context.getString(R.string.man)
                            expandedGender = false
                        }, text = { Text(stringResource(R.string.man)) })
                        DropdownMenuItem(onClick = {
                            gender = context.getString(R.string.woman)
                            expandedGender = false
                        }, text = { Text(stringResource(R.string.woman)) })
                        DropdownMenuItem(onClick = {
                            gender = context.getString(R.string.undisclosed)
                            expandedGender = false
                        }, text = { Text(stringResource(R.string.undisclosed)) })
                    }
                }
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            }
        }
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)
        // Favorite Cinema
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onPrimary)
                .clickable(onClick = { focusManager.clearFocus() })
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.favoriteCinema),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            Row {
                Text(
                    text = favoriteCinema,
                    style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.End),
                    modifier = Modifier
                        .wrapContentWidth()
                        .focusRequester(focusRequester)
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
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)
        // Province
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onPrimary)
                .clickable(onClick = { focusManager.clearFocus() })
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.province),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            Row {
                Text(
                    text = province,
                    style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.End),
                    modifier = Modifier
                        .wrapContentWidth()
                        .focusRequester(focusRequester)
                        .clickable {
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
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)
        // District
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onPrimary)
                .clickable(onClick = { focusManager.clearFocus() })
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.district),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            Row {
                Text(
                    text = district,
                    style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.End),
                    modifier = Modifier
                        .wrapContentWidth()
                        .focusRequester(focusRequester)
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
        Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)
    }
}
@Composable
fun SectionHeader(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(title, color = MaterialTheme.colorScheme.onTertiaryContainer, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onPrimary)
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
    Divider(color = MaterialTheme.colorScheme.onTertiary, thickness = 1.dp)
}

@Composable
fun AgreementSection() {
    Text(
        text = stringResource(R.string.policy),
        color = MaterialTheme.colorScheme.onTertiaryContainer,
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
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
        ) {
            Text(stringResource(R.string.update_info), color =MaterialTheme.colorScheme.onPrimary)
        }
        Text(
            text = stringResource(R.string.delete_account),
            color =  MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* Handle delete account */ }
        )
    }
}


