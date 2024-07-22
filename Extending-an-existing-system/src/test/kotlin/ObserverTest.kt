import org.example.ShipmentObserver
import org.example.ShipmentSubject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ShipmentObserverTest {

    private lateinit var shipment: ShipmentSubject
    private lateinit var observer1: ShipmentObserver
    private lateinit var observer2: ShipmentObserver

    @BeforeEach
    fun setUp() {
        shipment = ShipmentSubject()
        observer1 = TestShipmentObserver()
        observer2 = TestShipmentObserver()
    }

    @Test
    fun `test subscribe and notifyObservers`() {
        shipment.subscribe(observer1)
        shipment.subscribe(observer2)

        shipment.notifyObservers()

        assertEquals(1, (observer1 as TestShipmentObserver).notificationCount)
        assertEquals(1, (observer2 as TestShipmentObserver).notificationCount)
    }

    @Test
    fun `test unsubscribe`() {
        shipment.subscribe(observer1)
        shipment.subscribe(observer2)
        shipment.unsubscribe(observer1)

        shipment.notifyObservers()

        assertEquals(0, (observer1 as TestShipmentObserver).notificationCount)
        assertEquals(1, (observer2 as TestShipmentObserver).notificationCount)
    }
}

class TestShipmentObserver : ShipmentObserver() {
    var notificationCount = 0

    override fun onNotify() {
        notificationCount++
    }
}
