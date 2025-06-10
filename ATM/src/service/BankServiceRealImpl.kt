package service

class BankServiceRealImpl(
) : BankService {

    override fun authenticateCard(cardNo: String, pin: String): Boolean {
        TODO()
    }

    override fun processAmount(cardNo: String, amount: Int) {
        TODO()
    }

    override fun checkBalance(cardNo: String): Int {
        TODO()
    }
}