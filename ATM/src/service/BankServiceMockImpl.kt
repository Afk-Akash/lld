package service

import cashProcessor.FiveHundredProcessor
import entity.Card
import java.time.LocalDate

class BankServiceMockImpl(
    private val fiveHundredProcessor: FiveHundredProcessor
) : BankService {

    private val validCards = listOf(
        Card(
            cardNumber = "123456789",
            expiryDate = LocalDate.now().plusYears(1)
        ),
        Card(
            cardNumber = "987654321",
            expiryDate = LocalDate.now().plusYears(1)
        ),
        Card(
            cardNumber = "12345678",
            expiryDate = LocalDate.now().plusYears(1)
        ),
        Card(
            cardNumber = "87654321",
            expiryDate = LocalDate.now().plusYears(1)
        )
    )
    private val balanceMap = mutableMapOf("123456789" to 10000, "12345678" to 67890, "987654321" to 0, "87654321" to 500)

    override fun authenticateCard(cardNo: String, pin: String): Boolean {
        validCards.forEach { card ->
            if(card.cardNumber == cardNo) return true
        }
        throw IllegalAccessException("card is invalid")
    }

    override fun processAmount(cardNo: String, amount: Int) {
        val availableBal = balanceMap[cardNo] ?: 0
        if(availableBal < amount){
            throw Exception("Your balance is too low for this")
        }
        fiveHundredProcessor.processAmount(amount)
        balanceMap[cardNo] = availableBal.minus(amount)
    }

    override fun checkBalance(cardNo: String): Int {
       val bal = balanceMap[cardNo] ?: 0
        println("balance : $bal")
        return bal
    }
}