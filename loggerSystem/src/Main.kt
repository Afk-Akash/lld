import `interface`.DebugLevelProcessor
import `interface`.ErrorLevelProcessor
import `interface`.InfoLevelProcessor

fun main() {

    val logger = InfoLevelProcessor(DebugLevelProcessor(ErrorLevelProcessor()))

    logger.log(1, "This is the message from info")
    logger.log(2, "This is the message from debug")
    logger.log(3, "This is the message from error")
}