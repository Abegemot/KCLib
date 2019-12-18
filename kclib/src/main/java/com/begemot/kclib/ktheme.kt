package com.begemot.kclib

import android.annotation.SuppressLint
import androidx.compose.Composable
import androidx.ui.core.*
import androidx.ui.graphics.Color
import androidx.ui.graphics.Paint
import androidx.ui.layout.Container
import androidx.ui.layout.FlowRow
import androidx.ui.layout.Padding
import androidx.ui.layout.Stack
import androidx.ui.material.ColorPalette
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview

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

/*
Provides an enum of Color palettes that can be cycled through the next function
In the future it will encompass the typography for becoming a true Matherial Theme ...
*/


enum class kTheme(val theme: ColorPalette){
    DARK(darkThemeColors),LIGHT(lightThemeColors),NONE(ColorPalette());
    companion object {
        fun next(kt: kTheme): kTheme {
            val i = values().size
            var nexttheme = kt.ordinal + 1
            if (nexttheme == i) nexttheme = 0
            return values().get(nexttheme)
        }
    }
}



/*
Utility to show up Themes in preview (fast and dirty) well not so fast but definitely dirty .....
any way purpose fulfilled
*/

@Preview
@Composable
fun TThemes(){
    KWindow(size=273){
        for(kT in kTheme.values() ) {
            Text(kT.name)
            FlowRow {
                DrawTheme(kT.theme)
            }
        }
    }
}

@Composable
fun DrawTheme(teme: ColorPalette){
    val size=68
    DrawSquareText(size.dp,teme.primary,"primary")
    DrawSquareText(size.dp,teme.onPrimary,"onPrimary")
    DrawSquareText(size.dp,teme.primaryVariant,"primaryVariant")
    DrawSquareText(size.dp,teme.secondary,"secondary")
    DrawSquareText(size.dp,teme.onSecondary,"onSecondary")
    DrawSquareText(size.dp,teme.secondaryVariant,"onSecondaryVariant")
    DrawSquareText(size.dp,teme.surface,"surface")
    DrawSquareText(size.dp,teme.onSurface,"onSurface")
    DrawSquareText(size.dp,teme.background,"background")
    DrawSquareText(size.dp,teme.onBackground,"onBackground")
    DrawSquareText(size.dp,teme.error,"error")
    DrawSquareText(size.dp,teme.onError,"onError")

}

@Composable
fun DrawSquareText(dim: Dp, col: Color, text:String?){
    Stack{
        aligned(Alignment.Center){
            Container ( width=dim,height = dim){
                val paint= Paint()
                paint.color=col
                Draw{canvas,parentSize->canvas.drawRect(parentSize.toRect(),paint)}
            }
        }
        if(text!=null){
            aligned(Alignment.TopCenter){
                Container(width = dim, height = dim) {
                    Padding(6.dp) {
                        Text(text,style=TextStyle(color= ContrastedCol(col)))
                    }
                }
            }
        }
    }
}


fun ContrastedCol(col1:Color):Color {
    val a = 1 - (0.299 * col1.red + 0.587 * col1.green + 0.114 * col1.blue) / 255
    return if (a < 0.999) Color.Black else Color.White
}
