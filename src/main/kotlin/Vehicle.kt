class Vehicle {
    private var id = 0
    private var name =  ""
    private var brand: Brand? = null
    private var workshops: ArrayList<Workshop>? = ArrayList()
    private var weight = 0
    private var maxPermissibleWeight = 0
    private var speed = 0
    private var maxSpeed = 0

    constructor(
        id: Int,
        name: String,
        brand: Brand?,
        workshops: ArrayList<Workshop>?,
        weight: Int,
        maxPermissibleWeight: Int,
        speed: Int,
        maxSpeed: Int
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
       speed = (speed-randomReducedSpeed).coerceAtLeast(0)
       return speed.toDouble()
    }

    fun drive(kilometers: Int){
        repeat(kilometers){
            val randomCallNumber = (3..5).random()
            repeat(randomCallNumber){
                accelerate()
                brake()
            }
        }
    }

    fun printInfo(){
        println("Vehicle(id=$id, name='$name', brand=$brand, workshops=$workshops, weight=$weight, maxPermissibleWeight=$maxPermissibleWeight, speed=$speed, maxSpeed=$maxSpeed)")
    }

    fun getWorkshop(postcode: Int): Workshop?{
        return workshops?.find { it.postcode == postcode }
    }
}