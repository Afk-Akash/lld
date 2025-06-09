package processor

class HundredProcessor: CashProcess {

    override fun processAmount(amount: Int): Boolean {
        val noteCount = amount/100
        val remain = amount % 100

        if(remain == 0){
            println("dispensing $noteCount note of 100 Rs.")
            return true
        }else{
            println("couldn't form the amount")
            return false
        }
    }
}
