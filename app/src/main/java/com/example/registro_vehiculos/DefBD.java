package com.example.registro_vehiculos;

public class DefBD {

    // Datos generales de la base de datos
    public static final String nameDb = "VehiculosBD";
    public static final int VERSION_BD = 1;

    // Tabla Vehículo
    public static final String tabla_vehiculo = "vehiculo";
    public static final String col_placa = "placa";
    public static final String col_marca = "marca";
    public static final String col_color = "color";

    public static final String crear_tabla_vehiculo =
            "CREATE TABLE IF NOT EXISTS " + tabla_vehiculo + " (" +
                    col_placa + " TEXT PRIMARY KEY, " +
                    col_marca + " TEXT, " +
                    col_color + " TEXT);";
}
