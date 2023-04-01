package com.agescarsan.medina

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB (contexto:Context):SQLiteOpenHelper(contexto, "User", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table Person(Id integer primary key AUTOINCREMENT, name varchar(10) not null, LastName varchar(20) not null, phone varchar(20) not null, genre varchar(10) not null, age integer not null, email varchar(20) not null)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun Guardar(Usuario: User) : String {
        val db = this.writableDatabase;
        var contenedor = ContentValues();
        contenedor.put("Id", Usuario.Id)
        contenedor.put("name", Usuario.name)
        contenedor.put("LastName", Usuario.LastName)
        contenedor.put("phone", Usuario.phone)
        contenedor.put("genre", Usuario.genre)
        contenedor.put("age", Usuario.age)
        contenedor.put("email", Usuario.email)
        try {
            var resultado = db.insert("Person", null, contenedor);

            if (resultado == (-1).toLong()){
                return "Hubo un error al Guardar";
            }
            return "Se guardo correctamente";
        } catch (ex: Exception) {
            return ex.message.toString();
        } finally {
            db.close()
        }
    }

    fun Actualizar(Id:Int, name:String, LastName:String, phone:String, genre: String,  age: Int, email: String) : String{
        val db = this.writableDatabase;
        var contenedor = ContentValues();
        contenedor.put("Id", Id)
        contenedor.put("name", name)
        contenedor.put("LastName", LastName)
        contenedor.put("phone", phone)
        contenedor.put("genre", genre)
        contenedor.put("age", age)
        contenedor.put("email", email)
        try{
            var resultado = db.update("Person", contenedor,"Id=?", arrayOf(Id.toString()));

            if (resultado > 0  ){
                return "Se actualizó"
            }
            return "Hubo un error";
        }catch (ex:Exception){
            return ex.message.toString();
        }finally {
            db.close();
        }

    }

    fun Eliminar(Id:Int): String{
        val db = this.writableDatabase;
        try{
            var resultado = db.delete("Person", "Id=?", arrayOf(Id.toString()))

            if (resultado > 0){
                return "Se eliminó correctamente"
            }
            return "Hubo un error";
        }catch (ex:Exception){
            return ex.message.toString();
        }finally {
            db.close()
        }
    }

    @SuppressLint("Range")
    fun listar():MutableList<User>{
        val lista: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val sql = "select * from Person"
        var resultado = db.rawQuery(sql, null)
        if (resultado.moveToFirst()) {
            do {
                var datosp=User();
                datosp.Id = resultado.getString(resultado.getColumnIndex("Id")).toInt()
                datosp.name = resultado.getString(resultado.getColumnIndex("name"))
                datosp.LastName = resultado.getString(resultado.getColumnIndex("LastName"))
                datosp.phone = resultado.getString(resultado.getColumnIndex("phone"))
                datosp.genre = resultado.getString(resultado.getColumnIndex("genre"))
                datosp.age = resultado.getInt(resultado.getColumnIndex("age"))
                datosp.email = resultado.getString(resultado.getColumnIndex("email"))
                lista.add(datosp)
            }while (resultado.moveToNext())
            resultado.close()
            db.close()
        }
        return lista
    }
}