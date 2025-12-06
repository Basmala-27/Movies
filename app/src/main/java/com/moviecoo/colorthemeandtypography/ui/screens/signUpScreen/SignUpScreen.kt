

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.AppNameAnimated
import com.moviecoo.colorthemeandtypography.common_components.AuthButton
import com.moviecoo.colorthemeandtypography.common_components.CustomOutlinedTextField
import com.moviecoo.colorthemeandtypography.common_components.LoadingOverlay

import com.moviecoo.colorthemeandtypography.ui.theme.OnPrimary
import kotlinx.coroutines.suspendCancellableCoroutine


@Composable
fun SignUpScreen(
    onSignUpClick: (String, String) -> Unit = { _, _ -> },
    onSignInClick: () -> Unit  = {  }
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var isLoadingScreen by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()

    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {

            // Background image
            Image(
                painter = painterResource(R.drawable.signinsignupscreenbackgound),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // App Name Animated
            AppNameAnimated(
                modifier = Modifier.absoluteOffset(x = 88.dp, y = 140.dp),
                enablePulse = true
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Spacer(modifier = Modifier.weight(1.5f))

                // Email Field
                CustomOutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = ""
                    },
                    labelText = "Email"
                )
                if (emailError.isNotEmpty()) {
                    Text(
                        text = emailError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Password Field
                CustomOutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = ""
                    },
                    labelText = "Password",
                    isPassword = !showPassword
                )
                if (passwordError.isNotEmpty()) {
                    Text(
                        text = passwordError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                // Password Strength
                val passwordStrength = remember(password) {
                    when {
                        password.length < 8 -> "Weak"
                        password.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$")) -> "Strong"
                        else -> "Medium"
                    }
                }

                if (password.isNotEmpty()) {
                    Text(
                        text = "Password strength: $passwordStrength",
                        color = when(passwordStrength) {
                            "Weak" -> Color.Red
                            "Medium" -> Color.Yellow
                            "Strong" -> Color.Green
                            else -> Color.White
                        },
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                // Password Checklist
                Column(modifier = Modifier.padding(top = 4.dp)) {
                    Text("Password must contain:", color = Color.White, fontSize = 12.sp)
                    Text("• At least 8 characters", color = if(password.length>=8) Color.Green else Color.Red, fontSize = 12.sp)
                    Text("• Uppercase letter", color = if(password.any { it.isUpperCase() }) Color.Green else Color.Red, fontSize = 12.sp)
                    Text("• Lowercase letter", color = if(password.any { it.isLowerCase() }) Color.Green else Color.Red, fontSize = 12.sp)
                    Text("• Number", color = if(password.any { it.isDigit() }) Color.Green else Color.Red, fontSize = 12.sp)
                    Text("• Special character (!@#\$%^&*)", color = if(password.any { "!@#\$%^&*".contains(it) }) Color.Green else Color.Red, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Sign Up Button
                AuthButton(
                    buttonText = "Sign Up",
                    email = email,
                    password = password
                ) {
                    isLoadingScreen = true

                    // Email validation
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailError = "Please enter a valid email."
                        isLoadingScreen = false
                        return@AuthButton false
                    }

                    // Password validation
                    val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*]).{8,}\$")
                    if (!passwordPattern.matches(password)) {
                        passwordError = "Password must be at least 8 characters and include uppercase, lowercase, number, and special character."
                        isLoadingScreen = false
                        return@AuthButton false
                    }

                    try {
                        suspendCancellableCoroutine<Boolean> { cont ->
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    isLoadingScreen = false
                                    if (task.isSuccessful) {
                                        Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
                                        onSignUpClick(email, password)
                                        cont.resume(true) {}
                                    } else {
                                        val errorMessage = when (task.exception) {
                                            is FirebaseAuthUserCollisionException -> "Email already in use."
                                            else -> task.exception?.message ?: "Something went wrong."
                                        }
                                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                        cont.resume(false) {}
                                    }
                                }
                        }
                    } catch (e: Exception) {
                        isLoadingScreen = false
                        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
                        return@AuthButton false
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Already have an account? Row
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Already have an account? ",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    TextButton(onClick = { onSignInClick() }) {
                        Text(
                            text = "Sign In",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // Loading Overlay
            if (isLoadingScreen) {
                LoadingOverlay(
                    overlayAlpha = 0.5f,
                    indicatorStroke = 3f,
                    indicatorSize = 64
                )
            }

        }
    }
}


