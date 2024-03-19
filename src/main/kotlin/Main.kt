import kotlinx.coroutines.*
import java.util.*


fun main(args: Array<String>) {
    val bmw = Brand("BME E90 318d", "Austria","0664 0000000","bmw@email.at")
    val suzuki = Brand("Suzuki Swift","Austria", "0664 1111111", "suzuki@gmail.com")
    val nissan = Brand("Nissan Silvia","America","123456789","nissan@gmail.com")

    val workshop = Workshop("CoolCar","Austria",9020,"Klagenfurt","Coolestrasse","0677 000000")
    val workshop2 = Workshop("CoolerCar","Austria",9020,"Klagenfurt","Coolestrasse","0677 000000")

    val MikesCar = Vehicle(1, "MikesCar", bmw, arrayListOf(workshop), 1900, 2800, 100.0, 250.0)
    val MyCar = Vehicle(2, "MyCar", suzuki, arrayListOf(workshop2), 1600, 2500, 60.0, 200.0)
    val OtherVehicle = Vehicle(3, "OtherVehicle", nissan, arrayListOf(workshop, workshop2), 2200, 3100, 90.0, 455.0)

    println("MikesCar Information:")
    MikesCar.printInfo()
    println("\nMyCar Information:")
    MyCar.printInfo()
    println("\nOtherVehicle Information:")
    OtherVehicle.printInfo()

    println("\nDriving MikesCar for 5 kilometers:")
    MikesCar.drive(5)
    println("\nDriving MyCar for 3 kilometers:")
    MyCar.drive(5)
    println("\nDriving OtherVehicle for 4 kilometers:")
    OtherVehicle.drive(4)

    val workshopFor9020 = MikesCar.getWorkshop(9020)
    if (workshopFor9020 != null) {
        println("Postcode: ${workshopFor9020.postcode}")
    } else {
        println("Workshop not found for postcode 9020.")
    }

    /**
     * Suspended function to simulate a race for a given vehicle and distance.
     *
     * @param vehicle The vehicle participating in the race.
     * @param distance The distance of the race in kilometers.
     */
    suspend fun race(vehicle: Vehicle, distance: Int): Long {
        var traveledDistance = 0 // Variable, um die zurückgelegte Strecke zu verfolgen
        var timeElapsedMillis: Long = 0 // Variable, um die vergangene Zeit zu verfolgen
        val startTime = System.currentTimeMillis() // Startzeit des Rennens

        val random = java.util.Random() // Random-Objekt für zufällige Aktionen
        val randomBoolean = random.nextBoolean() // Zufälliger Boolean-Wert für abwechselnde Beschleunigung und Bremsen

        while (traveledDistance < distance) { // Rennen fortsetzen, bis das Fahrzeug die angegebene Strecke zurückgelegt hat
            val randomCallNumber = (3..5).random() // Zufällige Anzahl von Aktionen (Beschleunigung oder Bremsen) festlegen
            repeat(randomCallNumber) { // Wiederholen der zufälligen Anzahl von Aktionen
                if (randomBoolean) { // Wenn der zufällige Boolean-Wert true ist, beschleunigen Sie das Fahrzeug
                    vehicle.accelerate()
                } else { // Andernfalls bremsen Sie das Fahrzeug
                    vehicle.brake()
                }
                // Verwendung von withContext, um delay innerhalb eines Coroutine-Scope aufzurufen
                withContext(Dispatchers.Default) {
                    delay(100)
                }            }
            traveledDistance++ // Nach jedem Kilometer die zurückgelegte Strecke inkrementieren
            timeElapsedMillis = System.currentTimeMillis() - startTime // Aktualisieren der vergangenen Zeit
        }
        vehicle.updateRaceDuration(timeElapsedMillis) // Aktualisierung der Rennzeit des Fahrzeugs

        return timeElapsedMillis // Rückgabe der vergangenen Zeit, die das Fahrzeug benötigt hat, um die Strecke zurückzulegen
    }



    println("Starting the race!") // Print message indicating the start of the race

    // Starten der Rennen für Fahrzeug 1 und Fahrzeug 2 in separaten Coroutines
    val startTime = System.currentTimeMillis() // Startzeit des Rennens
    val raceDistance = 5 // Strecke des Rennens in Kilometern

    val start1 = GlobalScope.launch { race(MikesCar, raceDistance)
    println("Mike:" + race(MikesCar, raceDistance))
    }
    val start2 = GlobalScope.launch {
        race(MyCar, raceDistance)
        println("Me:" + race(MyCar, raceDistance))
    }
    val start3 = GlobalScope.launch { race(OtherVehicle, raceDistance)
        println("Other:" + race(OtherVehicle, raceDistance))
    }

    // Warten, bis beide Renn-Coroutines abgeschlossen sind
    runBlocking {
        start1.join()
        start2.join()
        start3.join()
    }

    val endTime = System.currentTimeMillis() // Endzeit des Rennens
    val raceTimeMillis = endTime - startTime // Gesamtzeit des Rennens in Millisekunden

    // Berechnen der durchschnittlichen Geschwindigkeit für jedes Fahrzeug (Geschwindigkeit = Strecke / Zeit)
    val averageSpeedMikesCar = raceDistance.toDouble() / (MikesCar.raceDuration.toDouble() / 1000) // Umrechnung der Zeit in Sekunden
    val averageSpeedMyCar = raceDistance.toDouble() / (MyCar.raceDuration.toDouble() / 1000) // Umrechnung der Zeit in Sekunden
    val averageSpeedOtherVehicle = raceDistance.toDouble() / (OtherVehicle.raceDuration.toDouble() / 1000) // Umrechnung der Zeit in Sekunden

    // Bestimmen des Gewinners basierend auf der durchschnittlichen Geschwindigkeit
    val winner: Vehicle? = when {
        averageSpeedMikesCar > averageSpeedMyCar && averageSpeedMikesCar > averageSpeedOtherVehicle -> MikesCar
        averageSpeedMyCar > averageSpeedMikesCar && averageSpeedMyCar > averageSpeedOtherVehicle -> MyCar
        averageSpeedOtherVehicle > averageSpeedMikesCar && averageSpeedOtherVehicle > averageSpeedMyCar -> OtherVehicle
        else -> null // Unentschieden, kein klarer Gewinner
    }

    // Ausgabe des Gewinners bzw. Unentschieden
    if (winner != null) {
        println("The winner is: ${winner.getName()}. Average speed: ${winner.raceDuration.toDouble() / 1000} m/s")
    } else {
        println("It's a tie. Both vehicles have the same average speed.")
    }

    println("Race finished!") // Print message indicating the end of the race
}