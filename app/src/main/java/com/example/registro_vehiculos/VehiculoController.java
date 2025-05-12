package com.example.registro_vehiculos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class VehiculoController {
    private BaseDatos bd;
    private Context c;

    public VehiculoController(Context c) {
        this.bd = new BaseDatos(c, 1);
        this.c = c;
    }

    public void agregarVehiculo(Vehiculo v) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(DefBD.col_placa, v.getPlaca());
            valores.put(DefBD.col_marca, v.getMarca());
            valores.put(DefBD.col_color, v.getColor());

            if (!buscarVehiculo(v)) {
                SQLiteDatabase sql = bd.getWritableDatabase();
                long id = sql.insert(DefBD.tabla_vehiculo, null, valores);
                Toast.makeText(c, "Vehículo registrado", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(c, "La placa ya existe", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Toast.makeText(c, "Error agregando vehículo " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean buscarVehiculo(Vehiculo v) {
        String[] args = new String[]{v.getPlaca()};
        SQLiteDatabase sql = bd.getReadableDatabase();
        Cursor cursor = sql.query(DefBD.tabla_vehiculo, null, "placa=?", args, null, null, null);
        if (cursor.getCount() > 0) {
            bd.close();
            return true;
        } else {
            bd.close();
            return false;
        }
    }

    public boolean buscarVehiculo(String placa) {
        String[] args = new String[]{placa};
        SQLiteDatabase sql = bd.getReadableDatabase();
        Cursor cursor = sql.query(DefBD.tabla_vehiculo, null, "placa=?", args, null, null, null);
        if (cursor.getCount() > 0) {
            bd.close();
            return true;
        } else {
            bd.close();
            return false;
        }
    }

    public Cursor allVehiculos() {
        try {
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor c = sql.query(DefBD.tabla_vehiculo, null, null, null, null, null, null);
            return c;
        } catch (Exception ex) {
            Toast.makeText(c, "Error consulta vehículos " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public Cursor allVehiculos2() {
        try {
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor cur = sql.rawQuery("SELECT placa AS _id, marca, color FROM vehiculo ORDER BY " + DefBD.col_placa, null);
            return cur;
        } catch (Exception ex) {
            Toast.makeText(c, "Error consulta vehículos " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void eliminarVehiculo(String placa) {
        try {
            String[] args = {placa};
            if (!buscarVehiculo(placa)) {
                Toast.makeText(c, "Placa no existe", Toast.LENGTH_LONG).show();
            } else {
                SQLiteDatabase sql = bd.getWritableDatabase();
                sql.delete(DefBD.tabla_vehiculo, "placa=?", args);
                Toast.makeText(c, "Vehículo eliminado", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Toast.makeText(c, "Error eliminando vehículo " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void actualizarVehiculo(Vehiculo v) {
        try {
            SQLiteDatabase sql = bd.getWritableDatabase();
            String[] args = {v.getPlaca()};
            ContentValues valores = new ContentValues();
            valores.put(DefBD.col_marca, v.getMarca());
            valores.put(DefBD.col_color, v.getColor());
            sql.update(DefBD.tabla_vehiculo, valores, "placa=?", args);
            Toast.makeText(c, "Vehículo actualizado", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(c, "Error actualizando vehículo " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}



