package com.agescarsan.medina

class User {
    constructor(Id: Int, name: String, LastName: String, phone: String, genre: String,  age: Int, email: String){
        this.Id = Id
        this.name = name
        this.LastName = LastName
        this.phone = phone
        this.genre = genre
        this.age = age
        this.email = email
    }
    constructor(){
    }
    var Id:Int = 0;
    var name:String = ""
    var LastName:String = ""
    var phone:String = ""
    var genre:String = ""
    var age:Int = 0
    var email:String=""

}
