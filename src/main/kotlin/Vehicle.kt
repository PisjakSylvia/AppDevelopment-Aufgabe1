class Vehicle {
    private var id = 0
    private var name =  ""
    private var brand: Brand? = null
    private var workshops: ArrayList<Workshop> = ArrayList()
    private var weight = 0
    private var maxPermissibleWeight = 0
    private var speed = 0
    private var maxSpeed = 0

    fun accelerate(): Double{
        val randomSpeed = (10..50).random()
        speed = (speed + randomSpeed).coerceAtMost(maxSpeed)
        return speed.toDouble()
    }
}