package com.example.model

import spray.json.DefaultJsonProtocol

case class Person(id: Option[Long], firstName: String, lastName: String, age: Int)

/**
 * Implements spray-json support so Person case class can be marshalled
 * to/from json when accepting and completing requests.  By having this
 * marshaller in scope an HttpService can automatically handle things
 * like List[Person] or Option[Person]
 */
object PersonJsonProtocol extends DefaultJsonProtocol {
  implicit val personFormat = jsonFormat4(Person)
}
