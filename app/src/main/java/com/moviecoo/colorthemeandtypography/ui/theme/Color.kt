package com.moviecoo.colorthemeandtypography.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF0E3E62)          // background of buttons
val OnPrimary = Color(0xFFFFFFFF)         // content on buttons ... text,shadow  and Main text in every place
val Secondary = Color(0xFF051542)         // circle for userAccount when activate  nd symbol in user screen
val OnSecondary = Color(0xFFFFFFFF)      // lines for icons when active
// Bottom bar colors and text field
val Surface = Color(0x4D4D4D66)        // background for both
val OnSurface = Color(0x4D4D4D66)      // icons in default , hint in text field
// background
val GradientBackground = Brush.verticalGradient(
    colorStops = arrayOf(
        0.0f to Color(0xFF0C3260),       // 0%   first color
        0.6254f to Color(0xFF061E3B),    // 62.54%   second color
        1.0f to Color(0xFF061E3B)        // 100%      third color
    ),
    startY = 0f,
    endY = Float.POSITIVE_INFINITY
)
val WatchNowButtonColor = Color(0xCCEC255A)   // watch now button
val UserAccount = Color(0xFF505664)          // for user Account , arrows
// switch
val BackgroundSwitch = Color(0xFFBFB3B2)
val CircleSwitch =  Color(0xFF051542)
// Lines
val Lines =  Color(0xFFA09B9B)
//userName
val UserName = Color(0xFF000000)
// sign up color
val SignUp = Color(0xFF6C47DB)
// Stars color
val StarsColor = Color(0xFFF6C700)
// estimate color
val EstimateColor = Color(0xFFFF8700)
// Notification Number color
val NotificationNumber = Color(0xFFEC5F59)

val OrangeAccent = Color(0xFFF09A32)

