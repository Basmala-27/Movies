package com.moviecoo.colorthemeandtypography.ui.screens.signUpScreen


import android.widget.Toast
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.moviecoo.colorthemeandtypography.R

import com.moviecoo.colorthemeandtypography.ui.theme.OnPrimary


@Composable
fun SignUpScreen(
    onSignUpClick: (String, String) -> Unit = { _, _ -> },
    onSignInClick: () -> Unit  = {  }
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
//                .padding(innerPadding)
                .fillMaxSize()
//             .padding(innerPadding)
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

            Box(
                modifier = Modifier
                    .absoluteOffset(x = 88.dp, y = 275.dp)
                    .width(218.dp)
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Moviecoo",
                    fontSize = 96.sp,
                    fontFamily = FontFamily(Font(R.font.romanesco_regular)),
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.sp,
                    lineHeight = 96.sp,
                    color = OnPrimary
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                Spacer(modifier = Modifier.weight(1.5f))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email",fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold) },
                    modifier =  Modifier
                        .width(351.dp)
                        .height(57.dp),
                    shape = RoundedCornerShape(13.dp),
                    textStyle = TextStyle(color = Color.White)

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
                    shape = RoundedCornerShape(13.dp) ,
                     textStyle = TextStyle(color = Color.White),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()

                )
                Spacer(modifier = Modifier.height(20.dp))

                val context = LocalContext.current
                val auth = FirebaseAuth.getInstance()

                Button(
                    onClick = {
                        if (email.isNotEmpty() && password.isNotEmpty()) {
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        val show = Toast.makeText(
                                            context,
                                            "Account created successfully!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        onSignUpClick(email, password)


                                    } else {
                                        Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        } else {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        }
                       },
                    modifier =  Modifier
                        .width(351.dp)
                        .height(57.dp),
                    shape = RoundedCornerShape(13.dp)



                ) {
                    Text("Sign Up",fontSize = 20.sp,
                        fontWeight = FontWeight.Medium)
                }



                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Don't have an account? ",
                        color = White,
                        fontSize = 17.sp
                    )
                    TextButton(
                        onClick = onSignInClick,
                        modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
                    ) {
                        Text(
                            "Sign In",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary ,
                            fontSize = 17.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}