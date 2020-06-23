package com.example.p1_formulario

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var fecha: String
    private var cal= Calendar.getInstance()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                val format = "MM/dd/yyy"
                val simpleDateFormat = SimpleDateFormat(format, Locale.US)
                fecha = simpleDateFormat.format(cal.time).toString()
                tv_fecha_nacimiento.text = fecha
            }
        ib_calendario.setOnClickListener(){
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        bt_guardar.setOnClickListener(){
            Log.d("ButtonClicked","si mi PERRO")
            val nombre = et_nombre.text.toString()
            val telefono = et_telefono.text.toString()
            val correo = et_correo.text.toString()
            val contrasena = et_contrasena.text.toString()
            val conf_contrasena = et_rep_contrasena.text.toString()
            val genero = if(rb_masculino.isChecked) "Masculino" else "Femenino"
            var pasatiempos = ""
            var fecha_nac = tv_fecha_nacimiento.text.toString()
            var ciudadnacimiento = sp_ciudad_nacimiento.selectedItem.toString()
            if(ch_Cine.isChecked) pasatiempos = "$pasatiempos  Cine"
            if(ch_ps4.isChecked) pasatiempos = "$pasatiempos  PS4"
            if(ch_series.isChecked) pasatiempos = "$pasatiempos  TV"
            if(ch_facebook.isChecked) pasatiempos = "$pasatiempos  Facebook"


            if((contrasena == conf_contrasena) &&  nombre.isNotEmpty() && telefono.isNotEmpty()
                && correo.isNotEmpty() && contrasena.isNotEmpty() && conf_contrasena.isNotEmpty() &&
                fecha_nac.isNotEmpty() && ciudadnacimiento.isNotEmpty() ){
                tv_resultado.text = "Nombre: $nombre \nTeléfono: $telefono \nCorreo: $correo " +
                        "\nCiudad de Nacimiento: $ciudadnacimiento \nGénero: $genero" +
                        "\nPasatiempos: $pasatiempos \nFecha Nacimiento: $fecha"
            }
            else if(contrasena != conf_contrasena){
                tv_resultado.text = getString(R.string.error_contrasenas)
            }
            else{
                tv_resultado.text = "Llene todos los campos"
            }
        }
    }
}