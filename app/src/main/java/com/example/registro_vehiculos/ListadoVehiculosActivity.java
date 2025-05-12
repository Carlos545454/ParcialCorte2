package com.example.registro_vehiculos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListadoVehiculosActivity extends AppCompatActivity {

    ListView listaVehiculos;
    VehiculoController controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_vehiculos);

        listaVehiculos = findViewById(R.id.listaVehiculos);
        controlador = new VehiculoController(getApplicationContext());

        // Llama al método para obtener todos los vehículos (Cursor)
        Cursor cursor = controlador.allVehiculos();

        if (cursor != null && cursor.moveToFirst()) {
            // Crea el adaptador con el Cursor
            VehiculoCursorAdapter adapter = new VehiculoCursorAdapter(this, cursor, false);
            listaVehiculos.setAdapter(adapter);

            // Configura el listener para manejar clics en los elementos de la lista
            listaVehiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                    // Obtén los datos del vehículo al hacer clic
                    cursor.moveToPosition(posicion);
                    String placa = cursor.getString(cursor.getColumnIndexOrThrow(DefBD.col_placa));
                    String marca = cursor.getString(cursor.getColumnIndexOrThrow(DefBD.col_marca));
                    String color = cursor.getString(cursor.getColumnIndexOrThrow(DefBD.col_color));

                    // Crea la intención para la actividad de edición de vehículos
                    Intent i = new Intent(getApplicationContext(), EdicionVehiculoActivity.class);
                    i.putExtra("placa", placa);
                    i.putExtra("marca", marca);
                    i.putExtra("color", color);
                    startActivity(i);
                }
            });
        } else {
            Toast.makeText(this, "No se encontraron vehículos", Toast.LENGTH_SHORT).show();
        }
    }

}


