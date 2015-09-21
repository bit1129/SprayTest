package com.example.services

import com.example.model.Person
import scala.collection.mutable.ArrayBuffer

/**
 * Person data stored in a mutable array for demonstration purposes.
 * This would normally be replaced by a DAO layer that makes calls to
 * a database or external service that persists person data.
 */
object PersonData {
  val testPeople = ArrayBuffer(
    Person(Some(1), "Bill", "Smith", 5),
    Person(Some(2), "John", "Doe", 10),
    Person(Some(3), "Steven", "Gangstead", 30),
    Person(Some(4), "Andrew", "Rubalcaba", 90))
}
