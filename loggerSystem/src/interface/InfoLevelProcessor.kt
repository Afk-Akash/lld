package `interface`

class InfoLevelProcessor(
    private val debugLevelProcessor: DebugLevelProcessor
): LogProcessor {

    override fun log(logLevel: Int, message: String) {
        if(logLevel == 1){
            println("Info: $message")
        }else{
            debugLevelProcessor.log(logLevel, message)
        }
    }

}