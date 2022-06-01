package com.unravel.calculatormaterial3

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.view.size
import androidx.fragment.app.Fragment
import com.unravel.calculatormaterial3.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val input: EditText = binding.input
        input.showSoftInputOnFocus = false

        var cardColor = R.color.cardColor
        //var statusBarColor = ContextCompat.getColor(requireContext(), R.color.statusBarColor)
        var backgroundColor = R.color.backgroundColor
        var clearButtonColor = R.color.clearButtonColor
        var buttonColor = R.color.buttonColor
        var equalColor = R.color.equalColor
        var errorColor = R.color.errorColor
        if(android.os.Build.VERSION.SDK_INT>=31)
        {
            cardColor= com.google.android.material.R.color.material_dynamic_primary30
            //statusBarColor = ContextCompat.getColor(requireContext(), com.google.android.material.R.color.material_dynamic_primary30)
            backgroundColor = com.google.android.material.R.color.m3_ref_palette_dynamic_secondary20
            clearButtonColor = com.google.android.material.R.color.m3_ref_palette_dynamic_tertiary90
            buttonColor = com.google.android.material.R.color.m3_ref_palette_dynamic_secondary80
            equalColor = com.google.android.material.composethemeadapter.R.color.m3_ref_palette_dynamic_primary90
            errorColor = com.google.android.material.composethemeadapter.R.color.m3_ref_palette_error80
        }

        binding.card.setCardBackgroundColor(ContextCompat.getColor(requireContext(), cardColor))
        binding.textView.setBackgroundColor(ContextCompat.getColor(requireContext(), cardColor))
        binding.baseConstraint.setBackgroundColor(ContextCompat.getColor(requireContext(), backgroundColor))


        val dataBase = DataBase(mutableListOf())

        
        binding.buttonTable.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                MaterialTheme{
                    //space between rows
                    val spacing = 10
                    val configuration = LocalConfiguration.current
                    val screenHeight = configuration.screenHeightDp
                    val screenWidth = configuration.screenWidthDp
                    Log.d("screenH", screenHeight.toString())
                    val ButtonSize = (((screenHeight*2f).toInt())-(spacing*7))/18
                    var visibility by remember{
                        mutableStateOf(true)
                    }
                    Column {

                        Column(
                            modifier = Modifier
                                .height(if (visibility) (screenHeight * 0.7f).dp else 0.dp)
                                .verticalScroll(rememberScrollState())
                                .horizontalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.Top
                        ) {
                            //row 1
                            Row1(ButtonSize = ButtonSize-30,
                                input,
                                dataBase,
                                updateVisibility = {
                                    visibility = !visibility
                                    Log.d("degub", visibility.toString())
                                },
                                visibility,
                                backgroundColor
                            )

                            //rest rows
                            DisplayPort(
                                spacing = spacing,
                                ButtonSize = ButtonSize,
                                dataBase = dataBase,
                                input = input,
                                result = binding.result,
                                context = context,
                                visibility,
                                clearButtonColor,
                                buttonColor,
                                equalColor,
                                errorColor = errorColor
                            )
                        }

                        Column(
                            modifier = Modifier
                                .height(if (!visibility) (screenHeight * 0.7f).dp else 0.dp)
                                .verticalScroll(rememberScrollState())
                                .horizontalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.Top
                        ){
                            RowS1(
                                ButtonSize = ButtonSize-30,
                                input = input,
                                updateVisibility = { visibility = !visibility },
                                visibility = visibility,
                                dataBase = dataBase,
                                backgroundColor = backgroundColor
                            )

                            SciencePort(
                                spacing = spacing,
                                ButtonSize = ButtonSize,
                                dataBase = dataBase,
                                input = input,
                                result = binding.result,
                                context = context,
                                visibility = visibility,
                                clearButtonColor = clearButtonColor,
                                buttonColor = buttonColor,
                                equalColor = equalColor,
                                errorColor = errorColor
                            )
                        }

                    }

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


@Composable
fun DisplayPort(
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
    Row2(ButtonSize = ButtonSize, input, dataBase = dataBase, visibility = visibility, clearButtonColor=clearButtonColor, buttonColor = buttonColor)
    SpaceH(spacing = spacing, visibility)
    Row3(ButtonSize = ButtonSize, input, dataBase, visibility,buttonColor = buttonColor)
    SpaceH(spacing = spacing, visibility)
    Row4(ButtonSize = ButtonSize, input, dataBase, visibility, buttonColor = buttonColor)
    SpaceH(spacing = spacing, visibility)
    Row5(ButtonSize = ButtonSize, input, dataBase, visibility, buttonColor = buttonColor)
    SpaceH(spacing = spacing, visibility)
    Row6(ButtonSize = ButtonSize, input, result, dataBase, context, visibility, buttonColor = buttonColor,equalColor=equalColor, errorColor = errorColor)
    SpaceH(spacing = spacing, visibility)
}

@Composable
fun Row1(
    ButtonSize:Int,
    input: EditText,
    dataBase: DataBase,
    updateVisibility: ()->Unit,
    visibility: Boolean,
    backgroundColor:Int
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ButtonCalc(
            color = colorResource(id = backgroundColor),
            icon = painterResource(id = R.drawable.root),
            size =ButtonSize,
            s ="sqRoot",
            scale = 1f,
            isText = false,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"sqrt(")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.SQROOT)
            dataBase.list.add(dataBase.BRACKET_LEFT)
            dataBase.expression+="sqrt("

        }

        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = backgroundColor),
            icon = painterResource(id = R.drawable.pi),
            size =ButtonSize,
            s ="pi",
            scale = 1f,
            isText = false,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"pi")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.PI)
            dataBase.expression+="pi"
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
            icon = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
            size =ButtonSize,
            s ="exponent",
            scale = 1f,
            isText = false,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"^")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.EXPONENT)
            dataBase.expression+="^"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = backgroundColor),
            icon = painterResource(id = R.drawable.exclamation),
            size =ButtonSize,
            s ="factorial",
            scale = 1f,
            isText = false,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"!")
            input.setSelection(input.length())
            dataBase.list.add(dataBase.FACTORIAL)
            dataBase.expression+="!"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = backgroundColor),
            icon = painterResource(id = R.drawable.ic_baseline_arrow_drop_down_24),
            size =ButtonSize,
            s ="drop down",
            scale = 1f,
            isText = false,
            visibility = visibility
        ) {
            updateVisibility()
        }


    }
}

@Composable
fun Row2(
    ButtonSize:Int,
    input: EditText,
    isBracketLeft: Boolean = false,
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
            dataBase.expression+="("
            input.setSelection(input.length())
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
            size =ButtonSize,
            s ="percentage",
            scale = 1f,
            isText = true,
            text = "%",
            textColor = Color.Black,
            visibility = visibility
        ) {

            if (!dataBase.list.isEmpty()) {
                input.setText(input.text.toString()+"%")
                input.setSelection(input.length())
                dataBase.list.add(dataBase.PERCENTAGE)
                dataBase.expression+="%"
            }
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.divide),
            size =ButtonSize,
            s ="divide",
            scale = 0.8f,
            isText = false,
            tint = Color.Unspecified,
            visibility = visibility
        ) {
            if (!dataBase.list.isEmpty()) {
                val check = dataBase.list[dataBase.list.size-1]
                if(check==dataBase.ADD||check == dataBase.MINUS||check==dataBase.MULTIPLY)
                {
                    input.setText(input.text.toString().substring(0, input.text.length-1)+"/")
                    dataBase.list.removeAt(dataBase.list.size-1)
                    dataBase.list.add(dataBase.DIVIDE)
                    input.setSelection(input.length())
                    dataBase.expression.substring(0, dataBase.expression.length-1)+"/"
                }else if(check != dataBase.DIVIDE)
                {
                    input.setText(input.text.toString()+"/")
                    input.setSelection(input.length())
                    dataBase.list.add(dataBase.DIVIDE)
                    dataBase.expression+="/"
                }
            }
//            input.setText(input.text.toString()+"/")
//            input.setSelection(input.length())
//            dataBase.list.add(dataBase.DIVIDE)
        }
    }
}

@Composable
fun Row3(
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
            s ="seven",
            scale = 1f,
            text = "7",
            textColor = Color.Black,
            isText = true,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"7")
            input.setSelection(input.length())
            dataBase.list.add("7")
            dataBase.expression+="7"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.pi),
            size =ButtonSize,
            s ="eight",
            isText = true,
            text = "8",
            textColor = Color.Black,
            scale = 1f,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"8")
            input.setSelection(input.length())
            dataBase.list.add("8")
            dataBase.expression+="8"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
            size =ButtonSize,
            s ="nine",
            scale = 1f,
            isText = true,
            text = "9",
            textColor = Color.Black,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"9")
            input.setSelection(input.length())
            dataBase.list.add("9")
            dataBase.expression+="9"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.cancel),
            size =ButtonSize,
            s ="multiply",
            scale = 0.3f,
            isText = false,
            tint = Color.Unspecified,
            visibility = visibility
        ) {
            if (!dataBase.list.isEmpty()) {
                val check = dataBase.list[dataBase.list.size-1]
                if(check==dataBase.ADD||check == dataBase.MINUS||check==dataBase.DIVIDE)
                {
                    input.setText(input.text.toString().substring(0, input.text.length-1)+"*")
                    dataBase.list.removeAt(dataBase.list.size-1)
                    dataBase.list.add(dataBase.MULTIPLY)
                    input.setSelection(input.length())
                    dataBase.expression.substring(0, dataBase.expression.length-1)+"*"
                }else if(check != dataBase.MULTIPLY)
                {
                    input.setText(input.text.toString()+"*")
                    input.setSelection(input.length())
                    dataBase.list.add(dataBase.MULTIPLY)
                }
            }
        }
    }
}

@Composable
fun Row4(
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
            s ="four",
            scale = 1f,
            text = "4",
            textColor = Color.Black,
            isText = true,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"4")
            input.setSelection(input.length())
            dataBase.list.add("4")
            dataBase.expression+="4"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.pi),
            size =ButtonSize,
            s ="five",
            isText = true,
            text = "5",
            textColor = Color.Black,
            scale = 1f,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"5")
            input.setSelection(input.length())
            dataBase.list.add("5")
            dataBase.expression+="5"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
            size =ButtonSize,
            s ="six",
            scale = 1f,
            isText = true,
            text = "6",
            textColor = Color.Black,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"6")
            input.setSelection(input.length())
            dataBase.list.add("6")
            dataBase.expression+="6"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.divide),
            size =ButtonSize,
            s ="minus",
            scale = 0.8f,
            isText = true,
            text = "-",
            textColor = Color.Black,
            tint = Color.Unspecified,
            visibility = visibility
        ) {
            if (!dataBase.list.isEmpty()) {
                val check = dataBase.list[dataBase.list.size-1]
                if(check==dataBase.ADD||check == dataBase.MULTIPLY||check==dataBase.DIVIDE)
                {
                    input.setText(input.text.toString().substring(0, input.text.length-1)+"-")
                    dataBase.list.removeAt(dataBase.list.size-1)
                    dataBase.list.add(dataBase.MINUS)
                    input.setSelection(input.length())
                    dataBase.expression.substring(0, dataBase.expression.length-1)+"-"
                }else if(check != dataBase.MINUS)
                {
                    input.setText(input.text.toString()+"-")
                    input.setSelection(input.length())
                    dataBase.list.add(dataBase.MINUS)
                    dataBase.expression+="-"
                }
            }
//            input.setText(input.text.toString()+"-")
//            input.setSelection(input.length())
//            dataBase.list.add(dataBase.MINUS)
        }
    }
}

@Composable
fun Row5(
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
            s ="one",
            scale = 1f,
            text = "1",
            textColor = Color.Black,
            isText = true,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"1")
            input.setSelection(input.length())
            dataBase.list.add("1")
            dataBase.expression+="1"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.pi),
            size =ButtonSize,
            s ="two",
            isText = true,
            text = "2",
            textColor = Color.Black,
            scale = 1f,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"2")
            input.setSelection(input.length())
            dataBase.list.add("2")
            dataBase.expression+="2"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
            size =ButtonSize,
            s ="three",
            scale = 1f,
            isText = true,
            text = "3",
            textColor = Color.Black,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"3")
            input.setSelection(input.length())
            dataBase.list.add("3")
            dataBase.expression+="3"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.ic_baseline_add_24),
            size =ButtonSize,
            s ="add",
            scale = 0.8f,
            isText = false,
            tint = Color.Unspecified,
            visibility = visibility
        ) {
            if (!dataBase.list.isEmpty()) {
                val check = dataBase.list[dataBase.list.size-1]
                if(check==dataBase.MULTIPLY||check == dataBase.MINUS||check==dataBase.DIVIDE)
                {
                    input.setText(input.text.toString().substring(0, input.text.length-1)+"+")
                    dataBase.list.removeAt(dataBase.list.size-1)
                    dataBase.list.add(dataBase.ADD)
                    input.setSelection(input.length())
                    dataBase.expression.substring(0, dataBase.expression.length-1)+"+"
                }else if(check != dataBase.ADD)
                {
                    input.setText(input.text.toString()+"+")
                    input.setSelection(input.length())
                    dataBase.list.add(dataBase.ADD)
                    dataBase.expression+="+"
                }
            }
//            input.setText(input.text.toString()+"+")
//            input.setSelection(input.length())
//            dataBase.list.add(dataBase.ADD)
        }
    }
}

@Composable
fun Row6(
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
            s ="zero",
            scale = 1f,
            text = "0",
            textColor = Color.Black,
            isText = true,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+"0")
            input.setSelection(input.length())
            dataBase.list.add("0")
            dataBase.expression+="0"
        }
        Spacer(modifier = Modifier.width(10.dp).height(ButtonSize.dp))
        ButtonCalc(
            color = colorResource(id = buttonColor),
            icon = painterResource(id = R.drawable.pi),
            size =ButtonSize,
            s ="dot",
            isText = true,
            text = ".",
            textColor = Color.Black,
            scale = 1f,
            visibility = visibility
        ) {
            input.setText(input.text.toString()+".")
            input.setSelection(input.length())
            dataBase.list.add(".")
            dataBase.expression+="."
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
                input.setText(s.substring(0, s.length-removal(dataBase)))
                dataBase.list.removeAt(dataBase.list.size-1)
                dataBase.expression.substring(0,s.length-1)
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

@Composable
fun SpaceH(spacing:Int, visibility: Boolean) {
    Spacer(modifier = Modifier
        .alpha(if (visibility) 1f else 0f)
        .fillMaxWidth()
        .height(spacing.dp))

}


@Composable
fun ButtonCalc(
    color: Color,
    icon: Painter,
    size: Int,
    s:String,
    scale:Float,
    text:String? = null,
    isText: Boolean,
    textColor: Color? = null,
    tint: Color = Color.White,
    visibility: Boolean,
    textSize: Int = 30,
    onClick: ()->Unit
) {
    Button(onClick = { onClick() },
        modifier= Modifier
            .size(size.dp),
        shape = CircleShape,
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = color)
    ) {
        if(isText&&text!=null&&textColor!=null)
        {
            Text(
                text = text,
                color = textColor,
                fontSize = textSize.sp
            )
        }else
        {
            Icon(
                icon,
                modifier = Modifier
                    .scale(scale)
                    .background(color),
                contentDescription = s,
                tint = tint
            )
        }

    }

}

@Preview
@Composable
fun prew() {
//    ButtonCalc(
//        color = colorResource(id = backgroundColor),
//        icon = painterResource(id = R.drawable.exclamation),
//        size = 80,
//        s = "exclamation",
//        scale = 5f,
//        isText = false,
//        visibility = true
//    ) {
//
//    }
}

//fun getScreenWidth(): Int {
//    return Resources.getSystem().displayMetrics.widthPixels
//}
//
//fun getScreenHeight(): Int{
//    return Resources.getSystem().displayMetrics.heightPixels
//}