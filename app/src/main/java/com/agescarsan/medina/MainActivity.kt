package com.agescarsan.medina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var Id:EditText
    lateinit var name:EditText
    lateinit var LastName:EditText
    lateinit var phone:EditText
    lateinit var genre:EditText
    lateinit var age:EditText
    lateinit var email:EditText
    lateinit var listD:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Id=findViewById(R.id.txtId)
        name=findViewById(R.id.txtname)
        LastName=findViewById(R.id.txtLastName)
        age=findViewById(R.id.txtage)
        phone=findViewById(R.id.txtphone)
        genre=findViewById(R.id.txtgenre)
        email=findViewById(R.id.txtemail)
        Id=findViewById(R.id.txtId)
        listD=findViewById(R.id.txtData)
    }

    fun SaveData(view: View) {
        var db = DB(this)
        var users = User()
        if(name.text.toString().isNotEmpty() && LastName.text.toString().isNotEmpty() && phone.text.toString().isNotEmpty() && Id.text.toString().isNotEmpty() && age.text.toString().isNotEmpty() && email.text.toString().isNotEmpty()){
            users.Id = Id.text.toString().toInt()
            users.name = name.text.toString()
            users.LastName = LastName.text.toString()
            users.age = age.text.toString().toInt()
            users.phone = phone.text.toString()
            users.genre = genre.text.toString()
            users.email = email.text.toString().toString()
            var message = db.Guardar(users)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun showData(view: View){
        listD.text=""
        var db = DB(this)
        var data = db.listar()
        if(data.isNotEmpty()){
            for (i in 0..data.size-1){
                listD.append("Id "+data.get(i).Id+" name "+data.get(i).name+" LastName "+data.get(i).LastName+" phone "+data.get(i).phone+" age "+data.get(i).age+" email "+data.get(i).email+" genre "+data.get(i).genre)
            }
        }
    }

    fun EraseData(view: View){
        var db = DB(this)
        if (Id.text.isNotEmpty()){
            db.Eliminar(Id.text.toString().toInt())
        }
    }

    fun updateData(view:View) {
        listD.text=""
        var db = DB(this)
        var users = User()
        if(Id.text.toString().isNotEmpty() && name.text.toString().isNotEmpty() && phone.text.toString().isNotEmpty() && email.text.toString().isNotEmpty() && age.text.toString().isNotEmpty() && genre.text.toString().isNotEmpty() && LastName.text.toString().isNotEmpty()) {
            users.Id = Id.text.toString().toInt()
            users.name = name.text.toString()
            users.LastName = LastName.text.toString()
            users.age = age.text.toString().toInt()
            users.phone = phone.text.toString()
            users.genre = genre.text.toString()
            users.email = email.text.toString()
            var message = db.Actualizar( Id.text.toString().toInt(), users.name, users.LastName, users.phone, users.genre, users.age, users.email)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}