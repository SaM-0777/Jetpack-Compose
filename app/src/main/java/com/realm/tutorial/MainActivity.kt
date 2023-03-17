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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
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
      Surface(
        color = Color(0xFF101010),
        modifier = Modifier.fillMaxSize()
      ) {
        DropDown(text = "Hello", modifier = Modifier.padding(15.dp)) {
          Text(text = "Revealed Text", modifier = Modifier.fillMaxWidth().height(100.dp).background(Color.Green))
        }
      }
    }
  }
}


@Composable
fun DropDown(
  text: String,
  modifier: Modifier = Modifier,
  initialState: Boolean = false,
  content: @Composable () -> Unit
) {
  var isOpen by remember {
    mutableStateOf(initialState)
  }
  val alpha = animateFloatAsState(targetValue = if (isOpen) 1f else 0f, animationSpec = tween(
    durationMillis = 300
  ))
  val rotateX = animateFloatAsState(targetValue = if (isOpen) 0f else -900f, animationSpec = tween(
    durationMillis = 300
  ))
  
  Column(
    modifier = modifier.fillMaxWidth()
  ) {
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
      modifier = modifier.fillMaxWidth()
    ) {
      Text(
        text = text,
        color = Color.White,
        fontSize = 16.sp
      )
      Icon(
        imageVector = Icons.Default.ArrowDropDown,
        contentDescription = "Open or Close the DropDown.",
        tint = Color.White,
        modifier = Modifier
          .clickable {
            isOpen = !isOpen
          }
          .scale(1f, if (isOpen) -1f else 1f)
      )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier
        .fillMaxWidth()
        .graphicsLayer {
          TransformOrigin(0.5f, 0f)
          rotationX = rotateX.value
        }
        .alpha(alpha = alpha.value)
    ) {
      content()
    }
  }
}

