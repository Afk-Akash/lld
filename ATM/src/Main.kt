import cashProcessor.FiveHundredProcessor
import cashProcessor.HundredProcessor
import cashProcessor.TwoHundredProcessor
import factory.BankServiceFactory
import service.AtmService
import service.BankService

fun main() {
    val bankServiceFactory = BankServiceFactory()
    val bankService = bankServiceFactory.bankServiceFactoryBuilder(useMock =  true)

    val atmService = AtmService(bankService)

    atmService.init()
}