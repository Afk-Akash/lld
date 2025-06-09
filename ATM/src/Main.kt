import processor.FiveHundredProcessor
import processor.HundredProcessor
import processor.TwoHundredProcessor

fun main() {
    while (true) {
        println("please enter the amount you want to take out")

        val input = readln().toInt()

        val atm = FiveHundredProcessor(TwoHundredProcessor(HundredProcessor()))

        atm.processAmount(input)
    }
}