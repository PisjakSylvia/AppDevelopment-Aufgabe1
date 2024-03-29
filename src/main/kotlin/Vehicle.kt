import kotlinx.coroutines.Job
import java.util.*
import kotlin.collections.ArrayList


class Vehicle {
    private var id = 0
    private var name =  ""
    private var brand: Brand? = null
    private var workshops: ArrayList<Workshop>? = ArrayList()
    private var weight = 0
    private var maxPermissibleWeight = 0
    private var speed: Double = 0.0
    private var maxSpeed: Double = 0.0
    var distanceTraveled: Double = 0.0
        private set
    var raceDuration: Long = 0 // Zeit, die das Fahrzeug für das Rennen benötigt
        private set
    fun updateDistanceTraveled(distance: Job) : Double{
        distanceTraveled += distance.toString().toDouble()
        return distanceTraveled
    }
    fun updateRaceDuration(timeElapsed: Long) {
        raceDuration = timeElapsed // Methode zum Aktualisieren der Rennzeit
    }
    constructor(
        id: Int,
        name: String,
        brand: Brand?,
        workshops: ArrayList<Workshop>?,
        weight: Int,
        maxPermissibleWeight: Int,
        speed: Double,
        maxSpeed: Double
    ) {
        this.id = id
        this.name = name
        this.brand = brand
        this.workshops = workshops
        this.weight = weight
        this.maxPermissibleWeight = maxPermissibleWeight
        this.speed = speed
        this.maxSpeed = maxSpeed
    }

    fun accelerate(): Double{
        val randomSpeed = (10..50).random()
        speed = (speed + randomSpeed).coerceAtMost(maxSpeed)
        return speed.toDouble()
    }

    fun brake(): Double{
       val randomReducedSpeed = (10..50).random()
       speed = (speed-randomReducedSpeed).coerceAtLeast(0.0)
       return speed.toDouble()
    }

    fun drive(kilometers: Int) {
        val random = Random()
        repeat(kilometers) { _ ->
            val randomBoolean = random.nextBoolean()
            val randomCallNumber = (3..5).random()
            repeat(randomCallNumber) {
                if (randomBoolean) {
                    accelerate()
                } else {
                    brake()
                }
            }
            println("Traveled 1 kilometer. Current speed: $speed")
        }
    }

    fun printInfo(){
        println("Vehicle(id=$id, name='$name', brand=$brand, workshops=$workshops, weight=$weight kg, maxPermissibleWeight=$maxPermissibleWeight kg, speed=$speed km/h, maxSpeed=$maxSpeed km/h)")
    }

    fun getWorkshop(postcode: Int): Workshop?{
        return workshops?.find { it.postcode == postcode }
    }
    fun getName():String{
        return this.name
    }
}