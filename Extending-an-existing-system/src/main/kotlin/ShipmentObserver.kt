package org.example

open class ShipmentObserver {
    val shipment = ShipmentSubject()

    fun trackShipment() {
        shipment.subscribe(this)
    }

    open fun onNotify() {
        // Logic to handle notification
    }
}
