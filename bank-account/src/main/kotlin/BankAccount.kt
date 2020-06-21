import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class BankAccount {
    private val lock = ReentrantLock()

    private var isOpened: Boolean = true

    var balance: Long = 0
        get() {
            check(isOpened)
            return field
        }
        private set


    fun adjustBalance(amount: Long){
        lock.withLock {
            check(isOpened)
            balance += amount
        }
    }

    fun close() {
        lock.withLock {
            isOpened = false
        }
    }
}
