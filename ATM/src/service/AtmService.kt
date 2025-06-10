package service

class AtmService(
    private val bankService: BankService
) {

    fun init() {
        while (true) {
            println("Please enter your card")
            val cardNo = readln()
            println("please enter your pin")
            val pin = readln()

            bankService.authenticateCard(cardNo, pin)

            println("1. withdraw amount")
            println("2. check balance")
            println("3. exit")

            val input = readln()
            when (input) {
                "1" -> handleWithdrawAmount(cardNo)
                "2" -> checkBalance(cardNo)
                "3" -> {
                    println("exiting ...")
                    return
                }
                else -> {
                    println("wrong input")
                    break
                }
            }


        }
    }


    private fun handleWithdrawAmount(cardNo: String) {
        println("Please enter the amount")
        val amount = readln().toInt()
        bankService.processAmount(cardNo, amount)
    }

    private fun  checkBalance(cardNo: String){
        bankService.checkBalance(cardNo)
    }
}