package com.realm.tutorial

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.realm.tutorial.ui.theme.TutorialTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val fontFamily = FontFamily(
      listOf(
        Font(R.font.lexend_thin, FontWeight.Thin),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_medium, FontWeight.Medium),
        Font(R.font.lexend_semibold, FontWeight.SemiBold),
        Font(R.font.lexend_black, FontWeight.Bold),
        Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
      )
    )

    setContent {
      var sizeState by remember {
        mutableStateOf(200.dp)
      }
      val size by animateDpAsState(
        targetValue = sizeState,
        keyframes {
          durationMillis = 5000
          sizeState at 0 with LinearEasing
          sizeState * 1.5f at 1000 with FastOutLinearInEasing
          sizeState * 2f at 5000
        }
//        spring(
//          dampingRatio = Spring.DampingRatioHighBouncy
//        )
//        tween(
//          durationMillis = 3000,
//          delayMillis = 300,
//          easing = LinearOutSlowInEasing
//        )
      )

      val infiniteTransition = rememberInfiniteTransition ()
      val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
          tween(durationMillis = 2000),
          repeatMode = RepeatMode.Reverse
        )
      )


      Box(modifier = Modifier
        .size(size)
        .background(color),
        contentAlignment = Alignment.Center
      ) {
        Button(onClick = { sizeState += 50.dp }) {
          Text(text = "Increase Size")
        }
      }
    }
  }
}
