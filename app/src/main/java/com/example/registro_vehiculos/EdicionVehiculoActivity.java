package com.example.registro_vehiculos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EdicionVehiculoActivity extends AppCompatActivity {

    EditText edtPlaca, edtMarca, edtColor;
    Button btnActualizar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_vehiculo); // Asegúrate de tener este layout creado

        // Recuperar los datos enviados desde la actividad anterior
        Intent i = getIntent();
        String placa = i.getStringExtra("placa");
        String marca = i.getStringExtra("marca");
        String color = i.getStringExtra("color");

        // Referencias a los componentes de la vista
        edtPlaca = findViewById(R.id.edtPlaca);
        edtMarca = findViewById(R.id.edtMarca);
        edtColor = findViewById(R.id.edtColor);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        // Asignar datos a los campos
        edtPlaca.setText(placa);
        edtPlaca.setEnabled(false); // No se debe editar la placa
        edtMarca.setText(marca);
        edtColor.setText(color);

        // Botón eliminar
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EdicionVehiculoActivity.this);
                builder.setTitle("Confirmar eliminación")
                        .setMessage("¿Está seguro de eliminar el vehículo?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                VehiculoController vc = new VehiculoController(getApplication());
                                vc.eliminarVehiculo(edtPlaca.getText().toString());
                                startActivity(new Intent(getApplicationContext(), ListadoVehiculosActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        // Botón actualizar
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vehiculo v = new Vehiculo(
                        edtPlaca.getText().toString(),
                        edtMarca.getText().toString(),
                        edtColor.getText().toString()
                );
                VehiculoController vc = new VehiculoController(getApplication());
                vc.actualizarVehiculo(v);
                startActivity(new Intent(getApplicationContext(), ListadoVehiculosActivity.class));
                finish();
            }
        });
    }
}

