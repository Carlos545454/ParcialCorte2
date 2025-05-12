package com.example.registro_vehiculos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class VehiculoCursorAdapter extends CursorAdapter {

    public VehiculoCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, false);  // Cambié a false para evitar problemas con autoRequery
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Verifica que se infle correctamente la vista
        return LayoutInflater.from(context).inflate(R.layout.fila_vehiculo, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Verifica que los indices de las columnas sean correctos
        TextView placa = view.findViewById(R.id.txtPlaca);
        TextView marca = view.findViewById(R.id.txtMarca);
        TextView color = view.findViewById(R.id.txtColor);

        // Cambié el manejo de las columnas, añadiendo validación
        String placaStr = cursor.getString(cursor.getColumnIndexOrThrow(DefBD.col_placa));
        String marcaStr = cursor.getString(cursor.getColumnIndexOrThrow(DefBD.col_marca));
        String colorStr = cursor.getString(cursor.getColumnIndexOrThrow(DefBD.col_color));

        // Asegúrate de que los valores no sean nulos
        if (placaStr != null) {
            placa.setText(placaStr);
        }
        if (marcaStr != null) {
            marca.setText(marcaStr);
        }
        if (colorStr != null) {
            color.setText(colorStr);
        }
    }
}



