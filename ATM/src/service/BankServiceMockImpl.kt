package service

import cashProcessor.FiveHundredProcessor

class BankServiceMockImpl(
    private val fiveHundredProcessor: FiveHundredProcessor
) : BankService {

    private val validCards = listOf("123456789", "98765431", "12345678", "87654321")

    override fun authenticateCard(cardNo: String, pin: String): Boolean {
        if (cardNo in validCards) return true
        throw IllegalAccessException("card is invalid")
    }

    override fun processAmount(amount: Int) {
        fiveHundredProcessor.processAmount(amount)
    }

    override fun checkBalance(cardNo: String): Int {
        return 100
    }
}