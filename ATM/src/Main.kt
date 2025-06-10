import factory.BankServiceFactory
import service.AtmService

fun main() {
    val bankServiceFactory = BankServiceFactory()
    val bankService = bankServiceFactory.create(useMock =  true)

    val atmService = AtmService(bankService)

    atmService.init()
}