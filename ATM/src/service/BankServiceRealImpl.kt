package service

import cashProcessor.FiveHundredProcessor

class BankServiceRealImpl(
) : BankService {

    override fun authenticateCard(cardNo: String, pin: String): Boolean {
        TODO()
    }

    override fun processAmount(amount: Int) {
        TODO()
    }

    override fun checkBalance(cardNo: String): Int {
        TODO()
    }
}