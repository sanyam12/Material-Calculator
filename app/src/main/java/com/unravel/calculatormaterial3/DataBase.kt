package com.unravel.calculatormaterial3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

data class DataBase(val list: MutableList<Any>,
                    var expression:String = "",
                    val SQROOT: Int = 111,
                    val PI: Int = 112,
                    val EXPONENT: Int = 113,
                    val FACTORIAL: Int = 114,
                    val BRACKET_LEFT: Int = 1221,
                    val BRACKET_RIGHT: Int = 1222,
                    val PERCENTAGE: Int = 123,
                    val DIVIDE: Int = 124,
                    val MULTIPLY: Int = 134,
                    val MINUS: Int = 144,
                    val ADD: Int = 154,
                    val CUBE_ROOT: Int = 211,
                    val INVERSE: Int = 212,
                    val NCR: Int = 214,
                    val COMMA:Int = 215,
                    val NPR:Int = 223,
                    val SQUARE:Int = 224,
                    val SINE:Int = 231,
                    val COSINE:Int = 232,
                    val TAN:Int = 233,
                    val CUBE:Int = 234,
                    val ASIN:Int = 241,
                    val ACOS:Int = 242,
                    val ATAN:Int = 243,
                    val XROOT:Int = 244,
                    val LN:Int = 251,
                    val LOG:Int = 252,
                    val LOGN:Int = 253,
                    val TENX:Int = 254,
                    val E:Int = 261,
                    val EX:Int = 262

)

data class DataLength(
                      val SQROOT: Int = 4,
                      val PI: Int = 2,
                      val EXPONENT: Int = 1,
                      val FACTORIAL: Int = 1,
                      val BRACKET_LEFT: Int = 1,
                      val BRACKET_RIGHT: Int = 1,
                      val PERCENTAGE: Int = 1,
                      val DIVIDE: Int = 1,
                      val MULTIPLY: Int = 1,
                      val MINUS: Int = 1,
                      val ADD: Int = 1,
                      val CUBE_ROOT: Int = 6,
                      val INVERSE: Int = 3,
                      val NCR: Int = 1,
                      val COMMA:Int = 1,
                      val NPR:Int = 1,
                      val SQUARE:Int = 2,
                      val SINE:Int = 3,
                      val COSINE:Int = 3,
                      val TAN:Int = 3,
                      val CUBE:Int = 2,
                      val ASIN:Int = 4,
                      val ACOS:Int = 4,
                      val ATAN:Int = 4,
                      val XROOT:Int = 5,
                      val LN:Int = 2,
                      val LOG:Int = 3,
                      val LOGN:Int = 4,
                      val TENX:Int = 3,
                      val E:Int = 1,
                      val EX:Int = 2
)




