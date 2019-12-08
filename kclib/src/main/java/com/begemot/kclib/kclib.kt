package com.begemot.kclib

import android.widget.Toast
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.foundation.shape.border.Border
import androidx.ui.input.EditorStyle
import androidx.ui.layout.*
import androidx.ui.material.surface.Surface
import androidx.ui.core.dp
import androidx.ui.material.*
import androidx.ui.tooling.preview.Preview
import androidx.ui.graphics.Color


fun hellokclib():String="kkdkdkdk"

/*@Preview
@Composable
fun Test(){
    Text("Holas")
}*/


@Preview
@Composable fun testKContainer(){
    val count = +state { 0 }
    val context = +ambient(ContextAmbient)
  // Text("UNI")
KWindow() {
    KContainer {
        KHeader("logins ")
        KField("email")
        Kline()
        KField("password")
        HeightSpacer(8.dp)
         KButtonBar {
              Button(text = "Loggin",onClick = { Toast.makeText(null, "jsjsjs", Toast.LENGTH_SHORT).show() })
              Button(text = "Register", onClick = { count.value += 1; })
          }
    }
}
}



@Composable
fun KWindow  (kt:kTheme= kTheme.LIGHT,children:@Composable ()()->Unit){
    MaterialTheme(colors = kt.theme) {
        children()
    }
}
@Composable fun KContainer(children: @Composable() () -> Unit) {
    // Container(width = 140.dp) {
    //      Surface(
    // shape = RoundedCornerShape(1.dp),
    // color = +themeColor { surface },
    //              color = Color.Gray,
    //              border = Border(+themeColor { primaryVariant }, 1.dp),
    //            elevation = 1.dp
    //     ) {
    Column(crossAxisAlignment = CrossAxisAlignment.Stretch ) {
        children()
    }
    //    }
//    }
}

@Composable fun Kline(){
    Surface(color= Color.DarkGray) {
        Container(expanded = false, height = 1.dp, width = 150.dp){}
    }
}

@Composable fun KButtonBar(children: @Composable() () -> Unit) {
    Surface(color =+themeColor { primaryVariant } /*, shape = RectangleShape,border = Border(Color.Red,1.dp)*/) {
        Padding(padding = EdgeInsets(top = 6.dp,bottom = 6.dp)) {
            Container(expanded = false, width = 270.dp, alignment = Alignment.CenterLeft) {
                FlexRow(mainAxisAlignment = MainAxisAlignment.SpaceEvenly) {
                    inflexible {
                        children()
                    }
                }
            }
        }
    }
}

@Composable fun KField(txt:String){
    val state = +state { EditorModel("North Poles") }
    Surface(color = +themeColor { surface }//,
            // shape = RectangleShape,border = Border(Color.Red,0.dp)
    ) {
        //      Padding(12.dp) {
        Row(crossAxisAlignment = CrossAxisAlignment.Center) {
            Column {
                Padding(padding = 6.dp) {
                    Text(text = txt, style = (+themeTextStyle { caption }).withOpacity(0.65f))
                }
                val v = themeTextStyle { h2 }
                Container(expanded = false, height = 34.dp, width = 270.dp,padding = EdgeInsets(left=12.dp)) {
                   TextField(
                      value = state.value,
                      onValueChange = { state.value = it },
                          editorStyle = EditorStyle(
                                    /*textStyle = TextStyle(
                                        fontSize = (20.sp)
                                    )*/
                                    textStyle = +themeTextStyle { h5 }
                            )
                    )
                }
            }

        }
        //      }

    }
}

@Composable fun KHeader(txt:String){
    Surface(color =+themeColor { primary } , shape = RectangleShape,border = Border(Color.Yellow,0.dp)) {
        Container(expanded = false, height = 54.dp, width = 270.dp,alignment = Alignment.CenterLeft) {
            Padding(padding = 12.dp) {
                Text(text = txt, style = (+themeTextStyle { h5 }).withOpacity(0.97f))
            }
        }
    }
}

val lightThemeColors = MaterialColors(
        primary = Color(0xFFDD0D3C),
        primaryVariant = Color(0xFFC20029),
        onPrimary = Color.White,
        secondary = Color.White,
        onSecondary = Color.Black,
        background = Color.White,
        onBackground = Color.Black,
        surface = Color.White,
        onSurface = Color.Black,
        error = Color(0xFFD00036),
        onError = Color.White
)
val darkThemeColors = MaterialColors(
        primary = Color(0xFFEA6D7E),
        primaryVariant = Color(0xFFDD0D3E),
        onPrimary = Color.Black,
        secondary = Color(0xFF121212),
        onSecondary = Color.White,
        surface = Color(0xFF121212),
        background = Color(0xFF121212),
        onBackground = Color.White,
        onSurface = Color.White
)

val klistThemes=listOf<MaterialColors>(lightThemeColors,darkThemeColors)

enum class kTheme(val theme:MaterialColors){
    DARK(darkThemeColors),LIGHT(lightThemeColors),NONE(MaterialColors())
}