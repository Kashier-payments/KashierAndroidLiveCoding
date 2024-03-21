package org.example

enum class CarType {
  SEDAN,
  SUV,
  TRUCK,
  CONVERTIBLE
}

data class Car(val id: String, val brand: String, val model: String, val year: Int, val type: CarType, val rentalDays: Int)

data class Customer(val id: String, val name: String, val email: String)


fun generateRentalReceipt(customer: Customer, cars: List<Car>): String {
  val receipt = StringBuilder()
  receipt.append("Rental Receipt for ${customer.name}:\n")
  receipt.append("Customer ID: ${customer.id}\n")
  receipt.append("Customer Email: ${customer.email}\n\n")
  var totalCost = 0.0

  cars.forEachIndexed { index, car ->
    val rentalCost = when (car.type) {
      CarType.SEDAN -> 50.0 * car.rentalDays
      CarType.SUV -> 70.0 * car.rentalDays
      CarType.TRUCK -> 100.0 * car.rentalDays
      CarType.CONVERTIBLE -> 150.0 * car.rentalDays
    }
    totalCost += rentalCost

    receipt.append("Car ${index + 1}: ${car}\n")
    receipt.append("Rental Rate per Day: ${String.format("%.2f", rentalCost / car.rentalDays)}\n")
    receipt.append("Rental Days: ${car.rentalDays}\n")
    receipt.append("Rental Cost: ${String.format("%.2f", rentalCost)}\n\n")
  }
  receipt.append("Total Cost: ${String.format("%.2f", totalCost)}")

  return receipt.toString()
}

fun main() {
  val customer = Customer("1", "John Doe", "john.doe@example.com")

  val sedan = Car("1", "Toyota", "Corolla", 2020, CarType.SEDAN, 7)
  val suv = Car("2", "Honda", "CR-V", 2019, CarType.SUV, 5)
  val truck = Car("3", "Ford", "F-150", 2018, CarType.TRUCK, 3)
  val convertible = Car("4", "BMW", "Z4", 2021, CarType.CONVERTIBLE, 4)

  val rentedCars = listOf(sedan, suv, truck, convertible)

  val rentalReceipt = generateRentalReceipt(customer, rentedCars)
  println(rentalReceipt)
}
