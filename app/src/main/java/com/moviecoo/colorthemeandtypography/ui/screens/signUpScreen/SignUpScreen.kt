package com.moviecoo.colorthemeandtypography.ui.screens.signUpScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.sp


@Composable
fun SignUpScreen(
    onSignInClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            val context = LocalContext.current
            val imageId = remember {
                context.resources.getIdentifier("signin_signupscreen", "drawable", context.packageName)
            }

            if (imageId != 0) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                Spacer(modifier = Modifier.height(460.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email",fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold) },
                    modifier =  Modifier
                        .width(351.dp)
                        .height(57.dp),
                    shape = RoundedCornerShape(13.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password",fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold) },
                    modifier =  Modifier
                        .width(351.dp)
                        .height(57.dp),
                    shape = RoundedCornerShape(13.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = { onSignInClick(email, password) },
                    modifier =  Modifier
                        .width(351.dp)
                        .height(57.dp),
                    shape = RoundedCornerShape(13.dp)



                ) {
                    Text("Sign Up",fontSize = 20.sp,
                        fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Don't have an account? ",
                        color = White
                    )
                    TextButton(
                        onClick = onSignUpClick,
                        modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
                    ) {
                        Text(
                            "Sign Up",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

