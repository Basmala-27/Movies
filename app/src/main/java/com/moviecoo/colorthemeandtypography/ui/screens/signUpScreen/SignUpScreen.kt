package com.moviecoo.colorthemeandtypography.ui.Screens.signUpScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.moviecoo.colorthemeandtypography.R

import androidx.compose.foundation.layout.Box

import androidx.compose.ui.platform.LocalContext


import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseAuth





@Composable
fun SignUpScreen( onSignUpClick: (String, String) -> Unit = { _, _ ->  }, onSignInClick: () -> Unit = { } ){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.signinscreenbackground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

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
                color = Color.White
            )
        }


        var email by remember { mutableStateOf("") }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = {
                Text(
                    "E-mail",
                    color = Color(0x4D4D4D66),
                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    lineHeight = 20.sp ,
                    letterSpacing = 0.sp
                )
            },
            modifier = Modifier
                .offset(x = 21.dp, y = 522.dp)
                .width(351.dp)
                .height(57.dp)
                .clip(RoundedCornerShape(13.dp))
                .background(Color(0x4D4D4D66)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                focusedContainerColor = Color(0x4D4D4D66),
                unfocusedContainerColor = Color(0x4D4D4D66)
            ),
            textStyle = TextStyle(color = Color.White),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )


        var password by remember { mutableStateOf("") }

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = {
                Text(
                    "Password",
                    color = Color(0x4D4D4D66),
                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    lineHeight = 20.sp ,
                    letterSpacing = 0.sp
                )
            },
            modifier = Modifier
                .offset(x = 21.dp, y = 600.dp)
                .width(351.dp)
                .height(57.dp)
                .clip(RoundedCornerShape(13.dp))
                .background(Color(0x4D4D4D66)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                focusedContainerColor = Color(0x4D4D4D66),
                unfocusedContainerColor = Color(0x4D4D4D66)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = TextStyle(color = Color.White),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        val context = LocalContext.current
        val auth = FirebaseAuth.getInstance()

        Button(

            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
                                onSignUpClick(email, password)


                            } else {
                                Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0E3E62)
            ),
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .offset(x = 21.dp, y = 696.dp)
                .width(351.dp)
                .height(57.dp)
        ) {
            Text(
                text = "Sign Up",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.White,
                        offset = Offset(0f, 0f),
                        blurRadius = 6f
                    )
                )
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 770.dp),
            contentAlignment = Alignment.Center
        ) {
            val signUpColor = Color(0xFF6C47DB)
            TextButton(onClick = onSignInClick) {
                Text(
                    buildAnnotatedString {
                        append("Don't you have an account? ")

                        withStyle(
                            style = SpanStyle(
                                color = signUpColor,
                                fontWeight = FontWeight.SemiBold,
                                shadow = Shadow(
                                    color = signUpColor,
                                    offset = Offset(0f, 0f),
                                    blurRadius = 10f
                                )
                            )
                        ) {
                            append("Sign In")


                        }

                        append(" Now!")
                    }
                    ,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.sp,
                    lineHeight = 10.sp,
                    softWrap = false,
                    maxLines = 1
                )
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(onSignUpClick = { _, _ -> })
}
