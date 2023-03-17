package com.realm.tutorial

import android.graphics.Paint.Align
import android.graphics.drawable.shapes.ArcShape
import android.os.Bundle
import android.view.MotionEvent
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
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
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.roundToInt
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
        contentAlignment = Alignment.Center,
        modifier = Modifier
          .fillMaxSize()
          .background(Color(0xFF101010))
      ) {
        Row(
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
            .padding(30.dp)
        ) {
          var volume by remember {
            mutableStateOf(0f)
          }
          val barCount = 20
          MusicKnob(modifier = Modifier.size(100.dp)) {
            volume = it
          }
          Spacer(modifier = Modifier.width(20.dp))
          VolumeBar(
            modifier = Modifier.fillMaxWidth().height(30.dp),
            activeBars = (barCount * volume).roundToInt(),
            barCount = barCount
          )
        }
      }
    }
  }
}

@Composable
fun VolumeBar(
  modifier: Modifier = Modifier,
  activeBars: Int = 0,
  barCount: Int = 10,
) {
  BoxWithConstraints (
    contentAlignment = Alignment.Center,
    modifier = modifier
  ) {
    val barWidth = remember {
      constraints.maxWidth / (2f * barCount)
    }
    Canvas(modifier = modifier) {
      for (i in 0 until barCount) {
        drawRoundRect(
          color = if (i in 0..activeBars) Color.Green else Color.DarkGray,
          topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
          size = Size(barWidth, constraints.maxHeight.toFloat()),
          cornerRadius = CornerRadius(0.05f)
        )
      }
    }
  }
}




@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob(
  modifier: Modifier = Modifier,
  limitingAngle: Float = 25f,
  onValueChange: (Float) -> Unit
){
  var rotation by remember {
    mutableStateOf(limitingAngle)
  }
  var touchX by remember {
    mutableStateOf(0f)
  }
  var touchY by remember {
    mutableStateOf(0f)
  }
  var centerX by remember {
    mutableStateOf(0f)
  }
  var centerY by remember {
    mutableStateOf(0f)
  }

  Image(
    painter = painterResource(id = R.drawable.knob),
    contentDescription = "Music knob",
    modifier = modifier
      .fillMaxSize()
      .onGloballyPositioned {   // once the position of the image in known in whole screen then the corresponding co-ordinates will be delivered.
        val windowBounds = it.boundsInWindow()
        centerX = windowBounds.size.width / 2f
        centerY = windowBounds.size.height / 2f
      }
      .pointerInteropFilter { event ->
        touchX = event.x
        touchY = event.y
        val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()

        when (event.action) {
          MotionEvent.ACTION_DOWN,
          MotionEvent.ACTION_MOVE -> {
            if (angle !in -limitingAngle..limitingAngle) {
              val fixedAngle = if (angle in -180f..-limitingAngle) {
                360f + angle
              } else {
                angle
              }
              rotation = fixedAngle.toFloat()
              val percent = (fixedAngle - limitingAngle / (360f - 2 * limitingAngle))
              onValueChange(percent.toFloat())
              true
            } else false
          }
          else -> false
        }
      }
  )
}

