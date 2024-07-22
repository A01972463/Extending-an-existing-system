package org.example

open class ShipmentSubject {
    private val subscribers = mutableListOf<ShipmentObserver>()

    fun subscribe(observer: ShipmentObserver) {
        subscribers.add(observer)
    }

    fun unsubscribe(observer: ShipmentObserver) {
        subscribers.remove(observer)
    }

    fun notifyObservers() {
        for (observer in subscribers) {
            observer.onNotify()
        }
    }
}
