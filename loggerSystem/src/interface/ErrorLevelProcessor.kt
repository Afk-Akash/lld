package `interface`

class ErrorLevelProcessor: LogProcessor {

    override fun log(logLevel: Int, message: String) {
        if(logLevel == 3){
            println("Error: $message")
        }else{
            println("Log level out of scope")
        }
    }

}
