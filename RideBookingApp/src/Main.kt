import kotlinx.coroutines.*
import java.util.PriorityQueue
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import kotlin.random.Random

class TopIntService(private val capacity: Int = 10) {

    private val heap = PriorityQueue<Int>() // min-heap
    private val lock = ReentrantLock()

    fun add(value: Int) {
        lock.withLock {
            if (heap.size < capacity) {
                heap.add(value)
            } else if (value > heap.peek()) {
                heap.poll()
                heap.add(value)
            }
        }
    }

    fun getTop(): List<Int> {
        lock.withLock {
            return heap.sortedDescending()
        }
    }
}

fun main() {
    runBlocking {
        val service = TopIntService()

        launch {
            repeat(1000) {
                val value = Random.nextInt(0, 1000)
                service.add(value)
                println(value)
                delay(1) // simulate stream delay
            }
        }

        launch {
            repeat(10) {
                delay(100)
                val top = service.getTop()
                println("Top values: $top")
            }
        }

    }

}