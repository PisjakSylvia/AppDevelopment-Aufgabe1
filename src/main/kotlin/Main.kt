fun main(args: Array<String>) {
    val bmw = Brand("BME E90 318d", "Austria","0664 0000000","bmw@email.at")
    val suzuki = Brand("Suzuki Swift","Austria", "0664 1111111", "suzuki@gmail.com")
    val nissan = Brand("Nissan Silvia","America","123456789","nissan@gmail.com")

    val workshop = Workshop("CoolCar","Austria",9020,"Klagenfurt","Coolestrasse","0677 000000")
    val workshop2 = Workshop("CoolerCar","Austria",9020,"Klagenfurt","Coolestrasse","0677 000000")

    val vehicle1 = Vehicle(1, "Vehicle1", bmw, arrayListOf(workshop), 1900, 2800, 100.0, 250.0)
    val vehicle2 = Vehicle(2, "Vehicle2", suzuki, arrayListOf(workshop2), 1600, 2500, 60.0, 200.0)
    val vehicle3 = Vehicle(3, "Vehicle3", nissan, arrayListOf(workshop, workshop2), 2200, 3100, 90.0, 455.0)

    println("Vehicle 1 Information:")
    vehicle1.printInfo()
    println("\nVehicle 2 Information:")
    vehicle2.printInfo()
    println("\nVehicle 3 Information:")
    vehicle3.printInfo()

    println("\nDriving vehicle 1 for 5 kilometers:")
    vehicle1.drive(5)
    println("\nDriving vehicle 2 for 3 kilometers:")
    vehicle2.drive(3)
    println("\nDriving vehicle 3 for 4 kilometers:")
    vehicle3.drive(4)

}