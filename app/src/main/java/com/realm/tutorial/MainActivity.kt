package com.realm.tutorial

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
      val constraints = ConstraintSet{
        val greenBox = createRefFor("greenbox")
        val redBox = createRefFor("redbox")
        val guideLine = createGuidelineFromTop(0.5f)

        constrain(greenBox) {
          top.linkTo(guideLine)
          start.linkTo(parent.start)
          width = Dimension.value(100.dp)
          height = Dimension.value(100.dp)
        }
        constrain(redBox) {
          top.linkTo(parent.top)
          start.linkTo(greenBox.end)
          width = Dimension.value(100.dp)
          height = Dimension.value(100.dp)
        }
        createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)
      }

      ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
          .background(Color.Green)
          .layoutId("greenbox")
        )
        Box(modifier = Modifier
          .background(Color.Red)
          .layoutId("redbox")
        )
      }
//      val painter = painterResource(id = R.drawable.female)
//      val description = "A female model wearing nice goggle"
//      val title = "Devente Ebison Collections"
//      val color = remember {
//        mutableStateOf(Color.Yellow)
//      }
//      val scaffoldState = rememberScaffoldState()
//      var textFieldState by remember {
//        mutableStateOf("")
//      }
//      val scope = rememberCoroutineScope()
//
//      Column(
//        Modifier.fillMaxSize()
//      ) {
//        Box(modifier = Modifier
//          .fillMaxWidth(0.5f)
//          .padding(12.dp)
//        ) {
//         ImageCard(painter = painter, contentDescription = description, title = title)
//       }
//        Box(modifier = Modifier
//          .fillMaxWidth()
//          .background(Color(0xFF101010))
//        ) {
//         Text(
//           text = buildAnnotatedString {
//              withStyle(
//                style = SpanStyle(
//                  color = Color.Green,
//                  fontSize = 50.sp
//                )
//              ) {
//                append("J")
//              }
//             append("etpack ")
//             withStyle(
//               style = SpanStyle(
//                 color = Color.Green,
//                 fontSize = 50.sp
//               )
//             ) {
//               append("C")
//             }
//             append("ompose")
//           },
//           color= Color.White,
//           fontSize = 30.sp,
//           fontFamily = fontFamily,
//           fontWeight = FontWeight.Bold,
//           fontStyle = FontStyle.Italic,
//           textAlign = TextAlign.Center,
//           textDecoration = TextDecoration.Underline
//         )
//       }
//        ColorBox(modifier = Modifier.weight(1f).fillMaxSize()) {
//          color.value = it
//        }
//        Box(modifier = Modifier.background(color.value).weight(1f).fillMaxSize())
//        Scaffold(modifier = Modifier
//          .fillMaxWidth()
//          .background(Color.Magenta), scaffoldState = scaffoldState) {it
//          Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier
//              .padding(horizontal = 30.dp)
//          ) {
//              TextField(
//                value = textFieldState,
//                label = {
//                  Text(text = "Enter your name")
//                },
//                onValueChange = {
//                  textFieldState = it
//                },
//                singleLine = true,
//                modifier = Modifier.fillMaxWidth()
//              )
//              Spacer(modifier = Modifier.height(16.dp))
//              Button(onClick = {
//                scope.launch {
//                  scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
//                }
//              }) {
//                Text(text = "Please greet me")
//              }
//          }
//        }
//        LazyColumn(
//          modifier = Modifier
//        ) {
//          itemsIndexed(
//            listOf("This", "is", "Jetpack", "Compose")
//          ) {index, string ->
//            Text(
//              text = string,
//              modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 24.dp),
//              fontSize = 24.sp,
//              fontWeight = FontWeight.Bold,
//              textAlign = TextAlign.Center,
//            )
//          }
//        }
//      }
//    }
//  }
}}}

//@Composable
//fun ImageCard(
//  painter: Painter,
//  contentDescription: String,
//  title: String,
//  modifier: Modifier = Modifier
//){
//  Card(
//    modifier = modifier.fillMaxWidth(),
//    shape = RoundedCornerShape(15.dp),
//    elevation = 5.dp
//  ) {
//    Box(modifier = Modifier.height(200.dp)) {
//      Image(
//        painter = painter,
//        contentDescription = contentDescription,
//        contentScale = ContentScale.Crop
//      )
//      Box(modifier= Modifier
//        .fillMaxSize()
//        .background(
//          brush = Brush.verticalGradient(
//            colors = listOf(
//              Color.Transparent,
//              Color.Black
//            ),
//            startY = 300f
//          )
//        )
//      ){}
//      Box(
//        modifier = Modifier
//          .fillMaxSize()
//          .padding(12.dp),
//        contentAlignment = Alignment.BottomStart
//      ) {
//        Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
//      }
//    }
//  }
//}

//@Composable
//fun ColorBox(
//  modifier: Modifier = Modifier,
//  updateColor: (Color) -> Unit
//) {
//  Box(modifier = modifier
//    .background(Color.Red)
//    .clickable {
//      updateColor(
//        Color(
//          Random.nextFloat(),
//          Random.nextFloat(),
//          Random.nextFloat(),
//        )
//      )
//    }
//  )
//}}}

