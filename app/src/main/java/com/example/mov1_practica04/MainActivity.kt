package com.example.mov1_practica04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var objMotocicleta: Motocicleta

    private lateinit var titulo : TextView
    private lateinit var nombre : EditText
    private lateinit var marca : EditText
    private lateinit var anio : EditText
    private lateinit var precio : EditText
    private lateinit var categoria : EditText
    private lateinit var estado : TextView

    private lateinit var limpiar : Button
    private lateinit var agregar : Button
    private lateinit var buscar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        objMotocicleta = Motocicleta()

        titulo = findViewById(R.id.tvTitulo)

        nombre = findViewById(R.id.edtNombre)
        marca = findViewById(R.id.edtMarca)
        anio = findViewById(R.id.edtAnio)
        precio = findViewById(R.id.edtPrecio)
        categoria = findViewById(R.id.edtCategoria)

        estado = findViewById(R.id.tvAdvertencia)

        limpiar = findViewById(R.id.btnLimpiar)
        agregar = findViewById(R.id.btnAgregar)
        buscar = findViewById(R.id.btnBuscar)

        var motocicletas = Array<Motocicleta?>(10){ null }

        limpiar.setOnClickListener{
            estado.apply{
                text = ""
            }
            nombre.setText("")
            marca.setText("")
            anio.setText("")
            precio.setText("")
            categoria.setText("")
        }

        agregar.setOnClickListener{
            var name = nombre.text.toString()
            var mark = marca.text.toString()
            var year = anio.text.toString().toIntOrNull()
            var price = precio.text.toString().toFloatOrNull()
            var catego = categoria.text.toString().firstOrNull()

            if(name.isNotEmpty() && mark.isNotEmpty() && year != null && price != null && catego != null){
                var posicion = motocicletas.indexOfFirst { it == null }

                if(posicion != -1){
                    motocicletas[posicion] = Motocicleta(name, mark, year, price, catego)
                    estado.text = "Moto agregada en $posicion"
                }
                else{
                    estado.text = "No hay espacio disponible"
                }
            }
            else{
                estado.text = "Completa todos los campos"
            }
        }

        buscar.setOnClickListener{
            var nombreBuscar = nombre.text.toString()
            if(nombreBuscar.isNotEmpty()){
                var moto  = motocicletas.find { it?.nombre.equals(nombreBuscar,ignoreCase = true) }
                if(moto != null){
                    estado.text = "Motocicleta encontrada"
                    nombre.setText(moto.nombre)
                    marca.setText(moto.marca)
                    anio.setText(moto.anio.toString())
                    precio.setText(moto.precio.toString())
                    categoria.setText(moto.categoria.toString())
                }
                else{
                    estado.text = "No se encontro niguna motocicleta con ese nombre"
                }
            }
            else{
                estado.text = "No se ha colocado ningun nombre para buscar"
            }
        }
    }
}