package com.example.services

import com.example.model.Person

/**
 * PersonService manages state of people in a mutable array
 * for demo purposes.  In a full system this would call a DAO
 * layer.
 */
object PersonService {
  import PersonData.testPeople

  def getPersons: List[Person] = {
    testPeople.toList
  }

  def getPersonById(personId: Long): Option[Person] = {
    testPeople find (_.id == Some(personId))
  }

  def addPerson(person: Person): Person = {
    val maxId = testPeople.map(_.id).flatten.max + 1
    val newPerson = person.copy(id = Some(maxId))
    testPeople += newPerson
    newPerson
  }

  def updatePerson(person: Person): Boolean = {
    testPeople.indexWhere(_.id == person.id) match {
      case -1 => false
      case i => testPeople.update(i, person); true
    }
  }

  def deletePerson(id: Long): Unit = {
    getPersonById(id) match {
      case Some(person) => testPeople -= person
      case None =>
    }
  }

}
