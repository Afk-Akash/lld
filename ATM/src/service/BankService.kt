package service

interface BankService {
    fun authenticateCard(cardNo: String, pin: String): Boolean

    fun processAmount(amount: Int)

    fun checkBalance(cardNo: String): Int
}
