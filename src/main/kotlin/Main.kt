import java.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch



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
    suspend fun race(vehicle: Vehicle, distance: Int) {
        var traveledDistance = 0 // Variable to track the distance traveled by the vehicle
        val random = Random() // Random number generator for randomizing actions
        val randomBoolean = random.nextBoolean() // Random boolean value for alternating acceleration and braking
        while (traveledDistance < distance) { // Continue racing until the vehicle has traveled the specified distance
            val randomCallNumber = (3..5).random() // Random number of actions (acceleration or braking) to perform
            repeat(randomCallNumber) { // Repeat the random number of actions
                if (randomBoolean) { // If the random boolean value is true, accelerate the vehicle
                    vehicle.accelerate()
                } else { // Otherwise, brake the vehicle
                    vehicle.brake()
                }
                delay(100) // Introduce a delay of 100 milliseconds between actions
            }
            traveledDistance++ // Increment the traveled distance after each kilometer
        }
    }

    val raceDistance = 5 // Distance of the race in kilometers

    println("Starting the race!") // Print message indicating the start of the race
    runBlocking {
        launch { race(MikesCar, raceDistance) } // Launch a coroutine for Mike's car to participate in the race
        launch { race(MyCar, raceDistance) } // Launch a coroutine for My's car to participate in the race
    }
    println("Race finished!") // Print message indicating the end of the race

}