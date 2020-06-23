package com.teo.formulario

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var options_spiner = arrayOf("Medellin","Bogota","Manizales","Cali","Barranquilla")


        //val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options_spiner )


        var adapter =ArrayAdapter.createFromResource(this,R.array.ciudades,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter= adapter

        var ciudad:String=""
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                ciudad = "${parent.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }


        val c = Calendar.getInstance()
        val ano = c.get(Calendar.YEAR)
        val mes =c.get(Calendar.MONTH)
        val dia = c.get(Calendar.DAY_OF_MONTH)

        tvFecha.setOnClickListener{
            val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener {view:DatePicker , mYear:Int, mMonth:Int, mDay: Int ->
                tvFecha.setText(""+mDay+"/"+(mMonth+1)+"/"+mYear)
            },ano,mes,dia)
            dpd.show()
        }
        btRegistrar.setOnClickListener {
            val nombre :String = et_Nombre.text.toString()
            val correo: String = et_Correo.text.toString()
            val telefono:String=et_Telefono.text.toString()
            val contra:String=et_Contrasena.text.toString()
            val rcontra:String=et_Rcontrasena.text.toString()
            var genero:String= rb_Maculino.text.toString()
            if(rb_Femenino.isChecked){
                genero=rb_Femenino.text.toString()
            }

            var hobbies:String=""

            if(cb_viajar.isChecked())
                hobbies=hobbies+ cb_viajar.text.toString()+ " "

            if(cb_leer.isChecked())
                hobbies=hobbies+ cb_leer.text.toString()+ " "

            if(cb_bailar.isChecked())
                hobbies=hobbies+ cb_bailar.text.toString()+ " "

            if(cb_cantar.isChecked())
                hobbies=hobbies+ cb_cantar.text.toString() + " "

            val fecha :String = tvFecha.text.toString()

            // checkiar
            if(nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || contra.isEmpty() || rcontra.isEmpty() || fecha.isEmpty()){
                Toast.makeText(this,"Por favor llenar todos los campos",Toast.LENGTH_SHORT).show()
                if (nombre.isEmpty()) {
                    et_Nombre.setError("Este campo no puede estar vacio")
                }

                if (correo.isEmpty()) {
                    et_Correo.setError("Este campo no puede estar vacio")
                }

                if (telefono.isEmpty()) {
                    et_Telefono.setError("Este campo no puede estar vacio")
                }

                if (contra.isEmpty()) {
                    et_Contrasena.setError("Este campo no puede estar vacio")
                }

                if (rcontra.isEmpty()) {
                    et_Rcontrasena.setError("Este campo no puede estar vacio")
                }
            }
            else
            {
                if(contra != rcontra)
                    Toast.makeText(this,"Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                else{
                    tvResultado.text="Nombre: " + nombre +
                            "\nCorreo: " + correo+
                            "\nTelefono: " + telefono+
                            "\nGenero: " + genero+
                            "\nHobbies: " + hobbies+
                            "\nFecha de nacimiento: " + fecha+
                            "\nCiudad de Nacimiento: " + ciudad}
            }




        }
    }
}

