package com.realm.tutorial

import android.graphics.Paint.Align
import android.graphics.drawable.shapes.ArcShape
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
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
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
      ) {
        CircularProgress(percentage = 0.8f, number = 100)
      }
    }
  }
}


@Composable
fun CircularProgress(
  percentage: Float,
  number: Int,
  fontSize: TextUnit = 28.sp,
  radius: Dp = 50.dp,
  color: Color = Color.Green,
  strokeWidth: Dp = 8.dp,
  animationDuration: Int = 1000,
  animationDelay: Int = 0,
) {
  var isAnimationPlayed by remember {
    mutableStateOf(false)
  }
  val currentPercentage = animateFloatAsState(
    targetValue = if(isAnimationPlayed) percentage else 0f,
    animationSpec = tween(
      durationMillis = animationDuration,
      delayMillis = animationDelay
    )
  )
  
  LaunchedEffect(key1 = true) {
    isAnimationPlayed = true
  }
  
  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.size(radius * 2f)
  ) {
    Canvas(modifier = Modifier.size(radius * 2f)) {
      drawArc(
        color = color,
        -90f,
        360 * currentPercentage.value,
        useCenter = false,
        style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
      )
    }
    Text(
      text = (currentPercentage.value * number).toInt().toString(),
      color = Color.Black,
      fontSize = fontSize,
      fontWeight = FontWeight.Bold
    )
  }
}
