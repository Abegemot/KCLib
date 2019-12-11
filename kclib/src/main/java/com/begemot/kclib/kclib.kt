package com.begemot.kclib

import android.widget.Toast
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.*

import androidx.ui.foundation.shape.RectangleShape
//import androidx.ui.input.EditorStyle

import androidx.ui.layout.*
import androidx.ui.material.surface.Surface
import androidx.ui.core.dp
import androidx.ui.material.*
import androidx.ui.tooling.preview.Preview
import androidx.ui.graphics.Color


fun hellokclib():String="kkdkdkdk"

@Preview
@Composable
fun Test(){
    Text("Hola")
}
@Preview
@Composable
fun TestKWindow(){
    KWindow {
        Text("Hola")
    }
}
@Preview
@Composable
fun TestKContainer(){
    KWindow() {
        KContainer {
            Text("Hola")
        }
    }
}

@Preview
@Composable fun testKContainer2(){
    val context = +ambient(ContextAmbient)
    val kt = +state {kTheme.NONE}
    KWindow(kt.value) {
        KContainer {
            KHeader("Login")
            KField("e-mail")
            Kline()
            KField("password")
            //HeightSpacer(8.dp)
            KButtonBar {
                  Button(text = "Loggin",onClick = { Toast.makeText(null, "jsjsjs", Toast.LENGTH_SHORT).show() })
                  Button(text = "Register", onClick = { kt.value = kTheme.DARK; })
            }
        }
    }
}

@Composable
fun KWindow  (kt:kTheme= kTheme.DARK  ,children:@Composable ()()->Unit){
    //val kt2 = +state {kt}
    MaterialTheme(colors = kt.theme) {
        children()
    }
}
@Composable fun KContainer(children: @Composable() () -> Unit) {
     Container(width = 270.dp) {
       Column{
       children()
    }
  }
}

@Composable fun Kline(){
    Surface(color= Color.DarkGray) {
        Container(expanded = true, height = 1.dp){}
    }
}

@Composable fun KHeader(txt:String){
   // Surface(color =(+MaterialTheme.colors()).primary , shape = RectangleShape,border = Border(Color.Yellow,0.dp)) {
    Surface(color =(+MaterialTheme.colors()).primary , shape = RectangleShape) {
        Container(modifier = ExpandedWidth, alignment = Alignment.CenterLeft) {
            Padding(padding = 12.dp) {
                   Text(text = txt, style = (+MaterialTheme.typography()).h5.withOpacity(0.97f))
            }
        }
    }
}
@Composable fun KField(txt:String){
    val state = +state { EditorModel("North Poles") }
    Surface(color = (+MaterialTheme.colors()).surface //,
            // shape = RectangleShape,border = Border(Color.Red,0.dp)
    ) {
      Row() {
            Column {
                Padding(padding = EdgeInsets(left=6.dp,top = 4.dp)) {
                    Text(text = txt, style = (+MaterialTheme.typography()).caption.withOpacity(0.65f))
                }
                Container(modifier = ExpandedWidth,padding = EdgeInsets(left=12.dp,bottom = 4.dp)) {
                    TextField(
                            value = state.value,
                            onValueChange = { state.value = it },
                            textStyle = (+MaterialTheme.typography()).h6
                      )
                }
            }
        }
   }
}



@Composable fun KButtonBar(children: @Composable() () -> Unit) {
    Surface(color =(+MaterialTheme.colors()).primaryVariant   /*, shape = RectangleShape,border = Border(Color.Red,1.dp)*/) {
        Padding(padding = EdgeInsets(10.dp)) {
            Container(expanded = false,  alignment = Alignment.Center) {
                  FlexRow(mainAxisAlignment = MainAxisAlignment.SpaceEvenly,modifier = ExpandedWidth){
                  inflexible(children)
                }
            }
        }
     }
}




val lightThemeColors = ColorPalette(
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
val darkThemeColors = ColorPalette(
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


enum class kTheme(val theme:ColorPalette){
    DARK(darkThemeColors),LIGHT(lightThemeColors),NONE(ColorPalette());
    companion object {
        fun next(kt: kTheme): kTheme {
            val i = kTheme.values().size
            var nexttheme = kt.ordinal + 1
            if (nexttheme == i) nexttheme = 0
            return kTheme.values().get(nexttheme)
        }
    }
}