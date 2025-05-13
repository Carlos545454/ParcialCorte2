package com.example.registro_vehiculos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Vehiculo vehiculo;
    VehiculoController vc;
    EditText txtPlaca, txtMarca, txtColor;
    Button btnAgregar, btnBuscar, btnActualizar, btnEliminar, btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPlaca = findViewById(R.id.txtPlaca);
        txtMarca = findViewById(R.id.txtMarca);
        txtColor = findViewById(R.id.txtColor);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnListar = findViewById(R.id.btnListar);

        btnAgregar.setOnClickListener(this);
        btnBuscar.setOnClickListener(this);
        btnActualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnListar.setOnClickListener(this);

        vc = new VehiculoController(this);
    }

    @Override
    public void onClick(View view) {
        String placa = txtPlaca.getText().toString().trim();
        String marca = txtMarca.getText().toString().trim();
        String color = txtColor.getText().toString().trim();

        int id = view.getId();

        if (id == R.id.btnAgregar) {
            if (TextUtils.isEmpty(placa) || TextUtils.isEmpty(marca) || TextUtils.isEmpty(color)) {
                Toast.makeText(this, "Los datos no pueden estar vacíos", Toast.LENGTH_LONG).show();
            } else {
                vehiculo = new Vehiculo(placa, marca, color);
                if (vc.buscarVehiculo(placa)) {
                    Toast.makeText(this, "La placa ya existe", Toast.LENGTH_LONG).show();
                } else {
                    vc.agregarVehiculo(vehiculo);
                    Toast.makeText(this, "Vehículo agregado", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                }
            }

        } else if (id == R.id.btnBuscar) {
            if (TextUtils.isEmpty(placa)) {
                Toast.makeText(this, "Debes ingresar la placa", Toast.LENGTH_SHORT).show();
            } else {
                Vehiculo vehiculo = vc.obtenerVehiculo(placa);
                if (vehiculo != null) {
                    // Recuperar la placa y el color del vehículo
                    String mensaje = "Vehículo: Placa: " + vehiculo.getPlaca() +
                            ", Marca: " + vehiculo.getMarca() +
                            ", Color: " + vehiculo.getColor();

                    // Mostrar el mensaje completo en el Toast
                    Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Vehículo no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if (id == R.id.btnActualizar) {
            if (TextUtils.isEmpty(placa)) {
                Toast.makeText(this, "Debes ingresar la placa", Toast.LENGTH_SHORT).show();
            } else {
                if (vc.buscarVehiculo(placa)) {
                    vehiculo = new Vehiculo(placa, marca, color);
                    vc.actualizarVehiculo(vehiculo);
                    Toast.makeText(this, "Vehículo actualizado", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                } else {
                    Toast.makeText(this, "Vehículo no encontrado para actualizar", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (id == R.id.btnEliminar) {
            if (TextUtils.isEmpty(placa)) {
                Toast.makeText(this, "Debes ingresar la placa", Toast.LENGTH_SHORT).show();
            } else {
                if (vc.buscarVehiculo(placa)) {
                    vc.eliminarVehiculo(placa);
                    Toast.makeText(this, "Vehículo eliminado", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                } else {
                    Toast.makeText(this, "Vehículo no encontrado", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (id == R.id.btnListar) {
            Cursor c = vc.allVehiculos();
            String cadena = "";
            while (c.moveToNext()) {
                cadena += c.getString(0) + ", "; // Suponiendo que 0 es la columna de placa
            }
            Intent intent = new Intent(this, ListadoVehiculosActivity.class);
            startActivity(intent);
        }
    }

    private void limpiarCampos() {
        txtPlaca.setText("");
        txtMarca.setText("");
        txtColor.setText("");
        txtPlaca.requestFocus();
    }
}



