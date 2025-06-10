package service

interface BankService {
    fun authenticateCard(cardNo: String, pin: String): Boolean

    fun processAmount(cardNo: String, amount: Int)

    fun checkBalance(cardNo: String): Int
}
