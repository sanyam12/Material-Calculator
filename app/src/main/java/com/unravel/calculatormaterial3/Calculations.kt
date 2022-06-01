package com.unravel.calculatormaterial3

import android.util.Log
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.function.Function
import net.objecthunter.exp4j.function.Functions
import net.objecthunter.exp4j.operator.Operator
import kotlin.math.*


class Calculations {


//    fun expressionRemoval():Int
//    {
//
//    }

    fun evaluate(s: String): Double {
        val factorial: Operator = object : Operator("!", 1, true, PRECEDENCE_POWER + 1) {
            override fun apply(vararg args: Double): Double {
                val arg = args[0].toInt()
                require(arg.toDouble() == args[0]) { "Operand for factorial has to be an integer" }
                require(arg >= 0) { "The operand of the factorial can not be less than zero" }
                var result = 1.0
                for (i in 1..arg) {
                    result *= i.toDouble()
                }
                return result
            }
        }

        val cubert: Function = object : Function("cubert", 1) {
            override fun apply(vararg args: Double): Double {
                val n: Double = 1/3.toDouble()
                return args[0].pow(n)
            }
        }
        val ncr: Operator = object : Operator("!+!", 2, true, PRECEDENCE_ADDITION+1) {
            override fun apply(vararg args: Double): Double {
                require(args[0] == args[0].toInt().toDouble()){"Should be an int"}
                require(args[1] == args[1].toInt().toDouble()){"Should be an int"}

                val n:Int = args[0].toInt()
                val r:Int = args[1].toInt()
                var nf:Int=1
                var rf:Int = 1
                var nrf:Int = 1
                for (i in 1..n)
                {
                    nf *= i
                }

                for (i in 1..r)
                {
                    rf *= i
                }
                for (i in 1..(n-r))
                {
                    nrf *= i
                }
                return (nf/(rf*nrf)).toDouble()
            }
        }
        val asin: Function = object : Function("asin", 1) {
            override fun apply(vararg args: Double): Double {
                return asin(args[0])
            }
        }
        val acos: Function = object : Function("acos", 1) {
            override fun apply(vararg args: Double): Double {
                return acos((args[0]))
            }
        }
        val atan: Function = object : Function("atan", 1) {
            override fun apply(vararg args: Double): Double {
                return atan((args[0]))
            }
        }
        val logn: Function = object : Function("logn", 2) {
            override fun apply(vararg args: Double): Double {
                require(args[0]==args[0].toInt().toDouble()){"has to be int"}
                require(args[1]==args[1].toInt().toDouble()){"has to be int"}

                return log(args[0], args[1])
            }
        }
        val ln  : Function = object : Function("ln", 1){
            override fun apply(vararg args: Double): Double {
                return ln(args[0])
            }
        }
        val log : Function = object : Function("log", 1){
            override fun apply(vararg args: Double): Double {
                return log(args[0], 10.0)
            }
        }
        val sin : Function = object : Function("sin", 1) {
            override fun apply(vararg args: Double): Double {
                return sin(args[0])
            }
        }
        val cos : Function = object : Function("cos", 1) {
            override fun apply(vararg args: Double): Double {
                return cos(args[0])
            }
        }
        val tan : Function = object : Function("tan", 1) {
            override fun apply(vararg args: Double): Double {
                return tan(args[0])
            }
        }
        val xroot:Function = object : Function("xroot", 2) {
            override fun apply(vararg args: Double): Double {
                return args[0].pow((1/args[1]))
            }
        }
        val npr : Operator = object : Operator("&!", 2, true, PRECEDENCE_ADDITION+1){
            override fun apply(vararg args: Double): Double {
                require(args[0] == args[0].toInt().toDouble()){"Should be an int"}
                require(args[1] == args[1].toInt().toDouble()){"Should be an int"}
                val n = args[0].toInt()
                val r:Int = args[1].toInt()
                var nf:Int=1

                for (i in 1..n)
                {
                    nf *= i
                }

                var nrf = 1;
                for(i in 1..(n-r))
                {
                    nrf *=i
                }
                return (nf/nrf).toDouble()
            }
        }

        val exp: Expression =
            ExpressionBuilder(s)
                .operator(mutableListOf(factorial, ncr, npr))
                .functions(mutableListOf(cubert,asin,acos,atan, logn, ln, log, sin, cos, tan, xroot))
                .build()

        try {
            return exp.evaluate()
        } catch (e: Exception) {
            Log.d("error in cal", e.toString())
            return -1.0
        }
    }
}

fun removal(dataBase: DataBase):Int {
    var ans:Int = -1
    when(dataBase.list[dataBase.list.size-1])
    {
        dataBase.SQROOT-> {return DataLength().SQROOT}
        dataBase.PI-> {return DataLength().PI}
        dataBase.EXPONENT-> {return DataLength().EXPONENT}
        dataBase.FACTORIAL-> {return DataLength().FACTORIAL}
        dataBase.BRACKET_LEFT-> {return DataLength().BRACKET_LEFT}
        dataBase.BRACKET_RIGHT-> {return DataLength().BRACKET_RIGHT}
        dataBase.PERCENTAGE ->{return DataLength().PERCENTAGE}
        dataBase.DIVIDE-> {return DataLength().DIVIDE}
        dataBase.MULTIPLY-> {return DataLength().MULTIPLY}
        dataBase.MINUS-> {return DataLength().MINUS}
        dataBase.ADD-> {return DataLength().ADD}
        dataBase.CUBE_ROOT-> {return DataLength().CUBE_ROOT}
        dataBase.INVERSE-> {return DataLength().INVERSE}
        dataBase.NCR-> {return DataLength().NCR}
        dataBase.COMMA-> {return DataLength().COMMA}
        dataBase.NPR-> {return DataLength().NPR}
        dataBase.SQUARE-> {return DataLength().SQUARE}
        dataBase.SINE-> {return DataLength().SINE}
        dataBase.COSINE-> {return DataLength().COSINE}
        dataBase.TAN-> {return DataLength().TAN}
        dataBase.CUBE-> {return DataLength().ADD}
        dataBase.ASIN-> {return DataLength().ASIN}
        dataBase.ACOS-> {return DataLength().ACOS}
        dataBase.ATAN-> {return DataLength().ATAN}
        dataBase.XROOT-> {return DataLength().XROOT}
        dataBase.LN-> {return DataLength().LN}
        dataBase.LOG-> {return DataLength().LOG}
        dataBase.LOGN-> {return DataLength().LOGN}
        dataBase.TENX-> {return DataLength().TENX}
        dataBase.E-> {return DataLength().E}
        dataBase.EX-> {return DataLength().EX}
        else->{
            return -1
        }


    }
}

fun expressionRemoval(dataBase: DataBase):Int {
    var ans:Int = -1
    when(dataBase.list[dataBase.list.size-1])
    {
        dataBase.SQROOT-> {return 4}
        dataBase.PI-> {return 2}
        dataBase.EXPONENT-> {return 1}
        dataBase.FACTORIAL-> {return 1}
        dataBase.BRACKET_LEFT-> {return 1}
        dataBase.BRACKET_RIGHT-> {return 1}
        dataBase.PERCENTAGE ->{return DataLength().PERCENTAGE}
        dataBase.DIVIDE-> {return DataLength().DIVIDE}
        dataBase.MULTIPLY-> {return DataLength().MULTIPLY}
        dataBase.MINUS-> {return DataLength().MINUS}
        dataBase.ADD-> {return DataLength().ADD}
        dataBase.CUBE_ROOT-> {return DataLength().CUBE_ROOT}
        dataBase.INVERSE-> {return DataLength().INVERSE}
        dataBase.NCR-> {return 3}
        dataBase.COMMA-> {return DataLength().COMMA}
        dataBase.NPR-> {return 3}
        dataBase.SQUARE-> {return DataLength().SQUARE}
        dataBase.SINE-> {return DataLength().SINE}
        dataBase.COSINE-> {return DataLength().COSINE}
        dataBase.TAN-> {return DataLength().TAN}
        dataBase.CUBE-> {return DataLength().ADD}
        dataBase.ASIN-> {return DataLength().ASIN}
        dataBase.ACOS-> {return DataLength().ACOS}
        dataBase.ATAN-> {return DataLength().ATAN}
        dataBase.XROOT-> {return DataLength().XROOT}
        dataBase.LN-> {return DataLength().LN}
        dataBase.LOG-> {return DataLength().LOG}
        dataBase.LOGN-> {return DataLength().LOGN}
        dataBase.TENX-> {return DataLength().TENX}
        dataBase.E-> {return DataLength().E}
        dataBase.EX-> {return DataLength().EX}
        else->{
            return -1
        }


    }
}



