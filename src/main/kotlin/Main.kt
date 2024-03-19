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

    suspend fun race(vehicle: Vehicle, distance: Int) {
        var traveledDistance = 0
        val random = Random()
        val randomBoolean = random.nextBoolean()
        while (traveledDistance < distance) {
            val randomCallNumber = (3..5).random()
            repeat(randomCallNumber) {
                if (randomBoolean) {
                    vehicle.accelerate()
                } else {
                    vehicle.brake()
                }
                delay(100) //(in milliseconds)
            }
            traveledDistance++
        }
    }

    val raceDistance = 5 // 5 kilometers race distance

    println("Starting the race!")
    runBlocking {
        launch { race(MikesCar, raceDistance) }
        launch { race(MyCar, raceDistance) }
    }
    println("Race finished!")
}