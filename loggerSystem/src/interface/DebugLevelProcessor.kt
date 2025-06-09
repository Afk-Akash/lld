package `interface`

class DebugLevelProcessor(
    private val errorLevelProcessor: ErrorLevelProcessor
): LogProcessor {
    override fun log(logLevel: Int, message: String) {
        if(logLevel == 2){
            println("Debug: $message")
        }else{
            errorLevelProcessor.log(logLevel, message)
        }
    }
}