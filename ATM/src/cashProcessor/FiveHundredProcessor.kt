package cashProcessor

class FiveHundredProcessor(
    private val twoHundredProcessor: TwoHundredProcessor
): CashProcess {

    override fun processAmount(amount: Int): Boolean {
        val noteCount = amount/500
        val remain = amount % 500

        if(remain == 0){
            println("dispensing $noteCount note of 500 Rs.")
            return true
        }else{
            val response = twoHundredProcessor.processAmount(remain)
            if(response){
                println("dispensing $noteCount note of 500 Rs.")
            }
            return response
        }

    }
}