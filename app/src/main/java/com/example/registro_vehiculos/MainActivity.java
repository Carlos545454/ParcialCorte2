package com.example.registro_vehiculos;

import android.content.Intent;
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
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            } else {
                vehiculo = new Vehiculo(placa, marca, color);
                if (vc.buscarVehiculo(placa)) {
                    Toast.makeText(this, "La placa ya existe", Toast.LENGTH_SHORT).show();
                } else {
                    vc.agregarVehiculo(vehiculo);
                    limpiarCampos();
                }
            }
        } else if (id == R.id.btnBuscar) {
            if (TextUtils.isEmpty(placa)) {
                Toast.makeText(this, "Debes ingresar la placa", Toast.LENGTH_SHORT).show();
            } else {
                if (vc.buscarVehiculo(placa)) {
                    Toast.makeText(this, "Vehículo encontrado", Toast.LENGTH_SHORT).show();
                    // No tienes método que obtenga los datos del vehículo
                    // Podrías implementar uno que devuelva un Vehiculo completo
                } else {
                    Toast.makeText(this, "Vehículo no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (id == R.id.btnActualizar) {
            if (TextUtils.isEmpty(placa)) {
                Toast.makeText(this, "Debes ingresar la placa", Toast.LENGTH_SHORT).show();
            } else {
                if (vc.buscarVehiculo(placa)) {
                    vehiculo = new Vehiculo(placa, marca, color);
                    vc.actualizarVehiculo(vehiculo);
                    limpiarCampos();
                } else {
                    Toast.makeText(this, "No se encontró el vehículo para actualizar", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (id == R.id.btnEliminar) {
            if (TextUtils.isEmpty(placa)) {
                Toast.makeText(this, "Debes ingresar la placa", Toast.LENGTH_SHORT).show();
            } else {
                if (vc.buscarVehiculo(placa)) {
                    vc.eliminarVehiculo(placa);
                    limpiarCampos();
                } else {
                    Toast.makeText(this, "Vehículo no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (id == R.id.btnListar) {
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



