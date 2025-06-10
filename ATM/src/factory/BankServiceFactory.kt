package factory

import cashProcessor.FiveHundredProcessor
import cashProcessor.HundredProcessor
import cashProcessor.TwoHundredProcessor
import service.BankService
import service.BankServiceMockImpl
import service.BankServiceRealImpl

class BankServiceFactory {
    fun create(useMock: Boolean): BankService{
        return if(useMock){
            BankServiceMockImpl(FiveHundredProcessor(TwoHundredProcessor(HundredProcessor())))
        }else {
            BankServiceRealImpl()
        }
    }
}