package com.unravel.calculatormaterial3

import android.content.Context
import android.text.Html
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

@Composable
fun Science(
    visibility: Boolean,
    ButtonSize: Int,
    input: EditText,
    dataBase:DataBase
) {

    //TODO: sin, cos, tan, sin inverse, cos inverse, tan inverse, log(10), ln, log(n base)
    //TODO: x^2, x^3, cube root, xth root, E, 10^x, e^x, x^-1, nCr, pCr

    //TODO: dataBase needs setup in both simple and scientific calculator side!!!


    //TODO: backspace won't always work, it may crash the app, reason seems to be some funciton
    //TODO: take up more the just on operator in string for example cube take '^3)' but backspace
    //TODO: isn't able to figure out how much of string to remove.
    //TODO: Solution seems to be to make a function that we return how much to remove for each case


    //TODO: backspace always removes from spot at the start, could be improved but no current solution found


    //TODO: the app still won't work on android levels less than 12 due to dynamic colors, need to add alternate theme code
}

@Composable
fun RowS1(
    ButtonSize:Int,
    input: EditText,
    dataBase:DataBase,
    updateVisibility: ()->Unit,
    visibility: Boolean,
    backgroundColor:Int
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        ButtonCalc(
            color = colorResource(id = backgroundColor),
            icon = painterResource(id = R.drawable.cube_root),
            size =ButtonSize,
            s ="cube root",
            scale = 1.2f,
            isText = false,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"cubert(")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.CUBE_ROOT)
            dataBase.list.add(dataBase.BRACKET_LEFT)
            dataBase.expression+="cubert("
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id =backgroundColor ),
            icon = painterResource(id = R.drawable.xinverse),
            size =ButtonSize,
            s ="x inverse",
            scale = 1.4f,
            isText = false,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"^-1)")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.INVERSE)
            dataBase.list.add(dataBase.BRACKET_RIGHT)
            dataBase.expression+="^-1)"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = backgroundColor),
            icon = painterResource(id = R.drawable.pi),
            size =ButtonSize,
            s ="brackets)",
            isText = true,
            text = ")",
            textColor = Color.White,
            scale = 1f,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+")")
            dataBase.list.add(dataBase.BRACKET_RIGHT)
            input.setSelection(input.length())
            dataBase.expression+=")"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = backgroundColor),
            icon = painterResource(id = R.drawable.exclamation),
            size =ButtonSize,
            s ="nCr",
            scale = 1f,
            isText = true,
            textColor = Color.White,
            text = "nCr",
            textSize = 12,
            visibility = visibility
        ) {
            if(!input.text.toString().isEmpty())
            {
                Log.d("selection", input.selectionEnd.toString())
                input.setText(input.text.toString()+"C")
                input.setSelection(input.length())
                dataBase.list.add(dataBase.NCR)
                dataBase.expression+="!+!"
            }
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = backgroundColor),
            icon = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
            size =ButtonSize,
            s =",",
            scale = 1f,
            isText = true,
            text = ",",
            textColor = Color.White,
            visibility = visibility
        ) {
            if(!input.text.toString().isEmpty())
            {
                input.setText(input.text.toString()+",")
                input.setSelection(input.length())
                dataBase.list.add(dataBase.COMMA)
                dataBase.expression+=","
            }
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = backgroundColor),
            icon = painterResource(id = com.google.android.material.R.drawable.mtrl_ic_arrow_drop_up),
            size =ButtonSize,
            s ="drop up",
            scale = 1f,
            isText = false,
            visibility = visibility
        ) {
            updateVisibility()
        }


    }
}

@Composable
fun SciencePort(
    spacing: Int,
    ButtonSize: Int,
    dataBase: DataBase,
    input: EditText,
    result: TextView,
    context: Context,
    visibility: Boolean,
    clearButtonColor: Int,
    buttonColor: Int,
    equalColor:Int,
    errorColor:Int
) {
    SpaceH(spacing = spacing, visibility)
    RowS2(ButtonSize = ButtonSize, input, dataBase = dataBase, visibility = visibility, clearButtonColor = clearButtonColor, buttonColor = buttonColor)
    SpaceH(spacing = spacing, visibility)
    RowS3(ButtonSize = ButtonSize, input, dataBase, visibility, buttonColor = buttonColor)
    SpaceH(spacing = spacing, visibility)
    RowS4(ButtonSize = ButtonSize, input, dataBase, visibility, buttonColor = buttonColor)
    SpaceH(spacing = spacing, visibility)
    RowS5(ButtonSize = ButtonSize, input, dataBase, visibility, buttonColor = buttonColor)
    SpaceH(spacing = spacing, visibility)
    RowS6(ButtonSize = ButtonSize, input, result, dataBase, context, visibility, buttonColor = buttonColor, equalColor = equalColor, errorColor = errorColor)
    SpaceH(spacing = spacing, visibility)
}


@Composable
fun RowS2(
    ButtonSize:Int,
    input: EditText,
    dataBase: DataBase,
    visibility: Boolean,
    clearButtonColor:Int,
    buttonColor:Int
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        ButtonCalc(
            color = colorResource(id = clearButtonColor),
            icon = painterResource(id = R.drawable.root),
            size =ButtonSize,
            s ="clear",
            scale = 1f,
            text = "AC",
            textColor = Color.Black,
            isText = true,
            visibility = visibility
        ) {
            input.setText("")
            input.setSelection(input.length())
            dataBase.list.clear()
            dataBase.expression=""
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.pi),
            size =ButtonSize,
            s ="brackets(",
            isText = true,
            text = "(",
            textColor = Color.Black,
            scale = 1f,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"(")
            dataBase.list.add(dataBase.BRACKET_LEFT)
            input.setSelection(input.length())
            dataBase.expression+="("
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
            size =ButtonSize,
            s ="nPr",
            scale = 1f,
            isText = true,
            text = "nPr",

            textColor = Color.Black,
            visibility = visibility
        ) {

            if (!dataBase.list.isEmpty()) {
                input.setText(input.text.toString()+"P")
                input.setSelection(input.length())
                dataBase.list.add(dataBase.NPR)
                dataBase.expression+="&!"
            }
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.xsquare),
            size =ButtonSize,
            s ="square",
            scale = 1.5f,
            isText = false,
            tint = Color.Black,
            visibility = visibility
        ) {
            if (!dataBase.list.isEmpty()) {
                input.setText(input.text.toString()+"^2)")
                input.setSelection(input.length())
                dataBase.list.add(dataBase.SQUARE)
                dataBase.list.add(dataBase.BRACKET_RIGHT)
                dataBase.expression+="^2)"
            }
        }
    }
}

@Composable
fun RowS3(
    ButtonSize:Int,
    input: EditText,
    dataBase: DataBase,
    visibility: Boolean,
    buttonColor:Int
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.root),
            size =ButtonSize,
            s ="sin",
            scale = 1f,
            text = "sin",
            textColor = Color.Black,
            isText = true,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"sin(")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.SINE)
            dataBase.list.add(dataBase.BRACKET_LEFT)
            dataBase.expression+="sin("
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.pi),
            size =ButtonSize,
            s ="cos",
            isText = true,
            text = "cos",
            textSize = 27,
            textColor = Color.Black,
            scale = 1f,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"cos(")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.COSINE)
            dataBase.list.add(dataBase.BRACKET_LEFT)
            dataBase.expression+="cos("
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
            size =ButtonSize,
            s ="tan",
            scale = 1f,
            isText = true,
            text = "tan",
            textColor = Color.Black,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"tan(")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.TAN)
            dataBase.list.add(dataBase.BRACKET_LEFT)
            dataBase.expression+="tan("
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.xcube),
            size =ButtonSize,
            s ="cube",
            scale = 1.2f,
            isText = false,
            tint = Color.Black,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"^3)")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.CUBE)
            dataBase.list.add(dataBase.BRACKET_RIGHT)
            dataBase.expression+="^3)"
        }
    }
}

@Composable
fun RowS4(
    ButtonSize:Int,
    input: EditText,
    dataBase: DataBase,
    visibility: Boolean,
    buttonColor:Int
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.root),
            size =ButtonSize,
            s ="sin inverse",
            scale = 1f,
            text = "asin",
            textSize = 21,
            textColor = Color.Black,
            isText = true,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"asin(")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.ASIN)
            dataBase.list.add(dataBase.BRACKET_LEFT)
            dataBase.expression+="asin("
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.pi),
            size =ButtonSize,
            s ="cos inverse",
            isText = true,
            text = "acos",
            textSize = 21,
            textColor = Color.Black,
            scale = 1f,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"acos(")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.ACOS)
            dataBase.list.add(dataBase.BRACKET_LEFT)
            dataBase.expression+="acos("
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
            size =ButtonSize,
            s ="tan inverse",
            scale = 1f,
            isText = true,
            text = "atan",
            textSize = 21,
            textColor = Color.Black,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"atan(")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.ATAN)
            dataBase.list.add(dataBase.BRACKET_LEFT)
            dataBase.expression+="atan("
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.x_root),
            size =ButtonSize,
            s ="xth root",
            scale = 2f,
            isText = false,
            textColor = Color.Black,
            tint = Color.Black,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"xroot(")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.XROOT)
            dataBase.list.add(dataBase.BRACKET_LEFT)
            dataBase.expression+="xroot("

        }
    }
}

@Composable
fun RowS5(
    ButtonSize:Int,
    input: EditText,
    dataBase: DataBase,
    visibility: Boolean,
    buttonColor:Int
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.root),
            size =ButtonSize,
            s ="ln",
            scale = 1f,
            text = "ln",
            textColor = Color.Black,
            isText = true,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"ln(")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.LN)
            dataBase.list.add(dataBase.BRACKET_LEFT)
            dataBase.expression+="ln("
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.pi),
            size =ButtonSize,
            s ="log",
            isText = true,
            text = "log",
            textColor = Color.Black,
            scale = 1f,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"log(")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.LOG)
            dataBase.list.add(dataBase.BRACKET_LEFT)
            dataBase.expression+="log("
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
            size =ButtonSize,
            s ="logn",
            scale = 1f,
            textSize = 23,
            isText = true,
            text = "logn",
            textColor = Color.Black,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"logn(")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.LOGN)
            dataBase.list.add(dataBase.BRACKET_LEFT)
            dataBase.expression+="logn("
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.ten_exponent),
            size =ButtonSize,
            s ="10x",
            scale = 1.2f,
            isText = false,
            tint = Color.Unspecified,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"10^")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.TENX)
            dataBase.expression+="10^"
        }
    }
}

@Composable
fun RowS6(
    ButtonSize:Int,
    input: EditText,
    result: TextView,
    dataBase: DataBase,
    context: Context,
    visibility: Boolean,
    buttonColor:Int,
    equalColor:Int,
    errorColor:Int
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.root),
            size =ButtonSize,
            s ="E",
            scale = 1f,
            text = "e",
            textColor = Color.Black,
            isText = true,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"e")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.E)
            dataBase.expression+="e"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.epower),
            size =ButtonSize,
            s ="ex",
            isText = false,
            textColor = Color.Black,
            scale = 1.5f,
            visibility = visibility,
            tint = Color.Black
        ) {
            input.setText(input.text.toString()+"e^")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.EX)
            dataBase.expression+="e^"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.backspace_48px),
            size =ButtonSize,
            s ="backspace",
            scale = 0.8f,
            isText = false,
            visibility = visibility,
            tint = Color.Black
        ) {
            val s: String = input.text.toString()
            if(!s.isEmpty())
            {
                input.setText(s.substring(0, s.length-1))
                dataBase.list.removeAt(dataBase.list.size-1)
                dataBase.expression.substring(0, dataBase.expression.length-1)
            }
            input.setSelection(input.length())
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = equalColor),
            icon = painterResource(id = R.drawable.equal),
            size =ButtonSize,
            s ="equal",
            scale = 0.8f,
            isText = false,
            tint = Color.Unspecified,
            visibility = visibility
        ) {
            Log.d("debug database", dataBase.list.toString())
            val ans:Double = Calculations().evaluate(dataBase.expression)
            //val ans:Double = Calculations().calculation(dataBase)
            if (ans!=-1.0) {
                result.text = ans.toString()
            } else {
                result.text = "Format Error"
                result.setTextColor(ContextCompat.getColor(context, errorColor))
            }
        }
    }
}
