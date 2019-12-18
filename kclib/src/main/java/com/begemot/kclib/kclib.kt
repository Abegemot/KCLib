package com.begemot.kclib

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.compose.*
import androidx.compose.state
import androidx.ui.core.*

import androidx.ui.foundation.shape.RectangleShape

import androidx.ui.layout.*
import androidx.ui.material.surface.Surface
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.SimpleImage
import androidx.ui.material.*
import androidx.ui.tooling.preview.Preview
import androidx.ui.graphics.Color
import androidx.ui.graphics.imageFromResource
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType


fun hellokclib():String="kkdkdkdk"



@Composable
fun KWindow  (kt:kTheme= kTheme.NONE, size:Int, children:@Composable ()()->Unit){
    MaterialTheme(colors = kt.theme) {
        Surface(color = (+MaterialTheme.colors()).secondary,shape = RectangleShape) {
        Container(width = size.dp,alignment = Alignment.TopLeft) {
            Column {  children() }
            }
        }
    }
}

@Composable fun KText(txt:String) {
    Surface(color = (+MaterialTheme.colors()).surface) {
        Container (modifier = ExpandedWidth,alignment = Alignment.CenterLeft  ) {
            Padding(padding = EdgeInsets(left = 6.dp, top = 4.dp,bottom = 4.dp)) {
                Text(text = txt, style = (+MaterialTheme.typography()).caption.withOpacity(0.65f))
            }
        }
    }
}

@Composable fun Kline(){
    Surface(color= Color.DarkGray) {
        Container(modifier = ExpandedWidth, height = 1.dp){}
    }
}

@Composable fun KHeader(txt:String, onClick: (() -> Unit)? = null){
    val ctx=+ambient(ContextAmbient)
    Surface(color =(+MaterialTheme.colors()).primary , shape = RectangleShape) {
        Container(modifier = ExpandedWidth,alignment = Alignment.CenterLeft) {
            Padding(padding = 12.dp) {
                Row(modifier = ExpandedWidth) {
                    Row(modifier = Flexible(1f)){
                        Text(text = txt  , style = (+MaterialTheme.typography()).h5.withOpacity(0.97f))
                    }
                    Clickable(onClick = onClick) {
                        val img= imageFromResource(ctx.resources,R.drawable.ic_icons8_ruleta_48)
                        SimpleImage(image = img)
                     }
                }
            }
        }
    }
}


@Composable fun KField(txt:String, st:State<String>){
    val context=+ambient(ContextAmbient)
    Surface(color = (+MaterialTheme.colors()).surface) {
        Container(modifier = ExpandedWidth) {
            Column() {
                Padding(padding = EdgeInsets(left = 6.dp, top = 4.dp)) {
                Text(text = txt, style = (+MaterialTheme.typography()).caption.withOpacity(0.65f))
            }
            Row(){
                    WidthSpacer(12.dp)
                        TextField(
                            value = st.value,
                            onValueChange = { st.value = it },
                            textStyle = (+MaterialTheme.typography()).h6,
                            keyboardType = KeyboardType.Text,
                            onImeActionPerformed = {
                                if (it == ImeAction.Done) {
                                       hideKeyboard(context as Activity)
                                  }
                               },
                            imeAction = ImeAction.Done
                             )
            }
            HeightSpacer(3.dp)
           }
        }
    }
}

fun hideKeyboard(activity: Activity) {
    val imm: InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}


/*
Button bar goes wrong when too many buttons and too few space but by now .....
 */
@Composable fun KButtonBar(children: @Composable() () -> Unit) {
    Surface(color =(+MaterialTheme.colors()).primaryVariant   /*, shape = RectangleShape,border = Border(Color.Red,1.dp)*/) {
        Padding(padding = EdgeInsets(10.dp)) {
            Container(modifier = ExpandedWidth) {
                FlexRow(mainAxisAlignment = MainAxisAlignment.SpaceEvenly,modifier = ExpandedWidth) {
                    inflexible(children)
                }
            }
        }
    }
}


/*
PREVIEWS
 */

@Preview
@Composable
fun TWindow(){
    KWindow(size=150) {
        Text("Holaq")
    }
}

@Preview
@Composable
fun THeader(){
    KWindow(size=150) {
        KHeader("H")
    }
}


@Preview
@Composable
fun THeaderField  (){
    val s= +state{"test header"}
    KWindow(size=200) {
        KHeader("Login")
        KField("e-mail",s)
    }
}
@Preview
@Composable fun TLogin() {
    val s1 = +state { "myemail@com" }
    val s2 = +state { "mypassword" }
    KWindow(kTheme.NONE,size=222) {
        KHeader(txt = "Login")
        KField("e-mail", s1)
        Kline()
        KField("password", s2)
        KButtonBar {
            Button(text = "Loggin", onClick = { Toast.makeText(null, "Login clicked", Toast.LENGTH_SHORT).show() })
            Button(text = "Register", onClick = { })
        }
    }
}

@Preview
@Composable fun TAll() {
    val s = +state { "test with long text ssssssssssssssssssssssssssssssssssssssss xxx" }
    KWindow(kTheme.NONE,size=222) {
        Text("exploring limits where size components bigger than kwindow size")
        Text("some more  2lllllllllllllllllllllll xxxxxxxxxxxxx llllllllllllllllllzq")
        KHeader(txt = "Login")
        KField("e-mail", s)
        Kline()
        KText("ktext ")
        Kline()
        KField("password", s)
        KButtonBar {
            Button(text = "Loggin", onClick = { Toast.makeText(null, "jsjsjs", Toast.LENGTH_SHORT).show() })
            Button(text = "Register", onClick = { })
            Button(text = "Register2", onClick = { })
            Button(text = "Register23", onClick = { })
        }
    }
}
