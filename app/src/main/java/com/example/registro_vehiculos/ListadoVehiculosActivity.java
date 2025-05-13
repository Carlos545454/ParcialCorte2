package com.example.registro_vehiculos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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
        controlador = new VehiculoController(this);

        Cursor cursor = controlador.allVehiculos2();
        VehiculoCursorAdapter adapter = new VehiculoCursorAdapter(this, cursor, false);
        listaVehiculos.setAdapter(adapter);

        listaVehiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Accedemos directamente a los TextViews del layout del ítem
                TextView placa = view.findViewById(R.id.txtPlaca);
                TextView marca = view.findViewById(R.id.txtMarca);
                TextView color = view.findViewById(R.id.txtColor);

                // Mostramos los datos seleccionados
                Toast.makeText(getApplicationContext(),
                        placa.getText().toString() + ", " +
                                marca.getText().toString() + ", " +
                                color.getText().toString(),
                        Toast.LENGTH_LONG).show();

                // Enviamos los datos a la actividad de edición
                Intent i = new Intent(getApplicationContext(), EdicionVehiculoActivity.class);
                i.putExtra("placa", placa.getText().toString());
                i.putExtra("marca", marca.getText().toString());
                i.putExtra("color", color.getText().toString());
                startActivity(i);
            }
        });
    }
}



