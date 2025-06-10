package cashProcessor

class TwoHundredProcessor(
    private val hundredProcessor: HundredProcessor
): CashProcess {

    override fun processAmount(amount: Int): Boolean {
        val noteCount = amount/200
        val remain = amount % 200

        if(remain == 0){
            println("dispensing $noteCount note of 200 Rs.")
            return true
        }else{
            val response = hundredProcessor.processAmount(remain)
            if(response){
                println("dispensing $noteCount note of 200 Rs.")
            }
            return response
        }

    }

}
