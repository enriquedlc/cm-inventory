package com.cifpcm.inventory.database;

/**
 *
 * @author tecen
 */
public class SQLBuilder {

    // Consultas SQL para la tabla de aulas
    private static final String INSERT_AULA_SQL = "INSERT INTO aula (numeracion, descripcion, ip) VALUES (?, ?, ?);";
    private static final String SELECT_AULA_BY_ID = "SELECT numeracion, descripcion, ip FROM aula WHERE idAula = ?";
    private static final String SELECT_ALL_AULAS = "SELECT * FROM aula";
    private static final String DELETE_AULA_SQL = "DELETE FROM aula WHERE idAula = ?";
    private static final String UPDATE_AULA_SQL = "UPDATE aula SET numeracion = ?, descripcion = ?, ip = ? WHERE idAula = ?";
    private static final String EXISTS_AULA_SQL = "SELECT COUNT(*) FROM aula WHERE idAula = ?";
    private static final String SELECT_MARCAJES_BY_AULA = "SELECT m.IdMarcaje, m.IdProducto, m.IdAula, m.TimeStamp, m.Tipo, p.Descripcion "
            + "FROM Marcaje m "
            + "INNER JOIN Productos p ON m.IdProducto = p.IdProducto "
            + "WHERE m.IdAula = ? AND m.TimeStamp BETWEEN ? AND ?";

    // Consultas SQL para la tabla de productos
    private static final String INSERT_PRODUCTO_SQL = "INSERT INTO productos (descripcion, EAN13, keyRFID) VALUES (?, ?, ?);";
    private static final String SELECT_PRODUCTO_BY_ID = "SELECT descripcion, EAN13, keyRFID FROM productos WHERE idProducto = ?";
    private static final String SELECT_ALL_PRODUCTOS = "SELECT * FROM productos";
    private static final String DELETE_PRODUCTO_SQL = "DELETE FROM productos WHERE idProducto = ?";
    private static final String UPDATE_PRODUCTO_SQL = "UPDATE productos SET descripcion = ?, EAN13 = ?, keyRFID = ? WHERE idProducto = ?";
    private static final String EXISTS_PRODUCTO_SQL = "SELECT COUNT(*) FROM productos WHERE idProducto = ?";

    // Consultas SQL para la tabla de marcajes
    private static final String INSERT_MARCAJE_SQL = "INSERT INTO marcaje (idProducto, idAula, timeStamp, tipo) VALUES (?, ?, ?, ?);";
    private static final String SELECT_MARCAJE_BY_ID = "SELECT idProducto, idAula, timeStamp, tipo FROM marcaje WHERE idMarcaje = ?";
    private static final String SELECT_ALL_MARCAJES = "SELECT m.IdMarcaje, m.IdProducto, m.IdAula, m.TimeStamp, m.Tipo, p.Descripcion AS DescripcionProducto\n"
            + "FROM marcaje m\n"
            + "INNER JOIN productos p ON m.IdProducto = p.IdProducto;";
    private static final String SELECT_ALL_MARCAJES_ENTRE_FECHAS ="SELECT m.idMarcaje, m.idProducto, m.idAula, m.timeStamp, m.tipo, p.descripcion AS Descripcion " +
                 "FROM marcaje m " +
                 "INNER JOIN productos p ON m.idProducto = p.idProducto " +
                 "WHERE m.timeStamp BETWEEN ? AND ?";
    private static final String SELECT_MARCAJES_BY_PRODUCTO = "SELECT m.IdMarcaje, m.IdProducto, m.IdAula, m.Tipo, m.TimeStamp, p.Descripcion "
            + "FROM marcaje m "
            + "INNER JOIN productos p ON m.IdProducto = p.IdProducto "
            + "WHERE m.IdProducto = ? AND m.TimeStamp BETWEEN ? AND ?";
    private static final String DELETE_MARCAJES_BY_PRODUCTO_SQL = "DELETE FROM Marcaje WHERE idProducto = ?";
    private static final String DELETE_MARCAJE_SQL = "DELETE FROM marcaje WHERE idMarcaje = ?";
    private static final String UPDATE_MARCAJE_SQL = "UPDATE marcaje SET idProducto = ?, idAula = ?, timeStamp = ?, tipo = ? WHERE idMarcaje = ?";
    private static final String COUNT_MARCAJES_BY_AULA_SQL = "SELECT COUNT(*) FROM marcaje WHERE idAula = ?";
    private static final String SELECT_MARCAJES_BY_PRODUCTO_Y_AULA = "SELECT m.IdMarcaje, m.IdAula, m.TimeStamp, m.Tipo, p.Descripcion "
            + "FROM Marcaje m "
            + "INNER JOIN Productos p ON m.IdProducto = p.IdProducto "
            + "WHERE m.IdProducto = ? AND m.IdAula = ?";

    public static String getINSERT_PRODUCTO_SQL() {
        return INSERT_PRODUCTO_SQL;
    }

    public static String getSELECT_PRODUCTO_BY_ID() {
        return SELECT_PRODUCTO_BY_ID;
    }

    public static String getSELECT_ALL_PRODUCTOS() {
        return SELECT_ALL_PRODUCTOS;
    }

    public static String getDELETE_PRODUCTO_SQL() {
        return DELETE_PRODUCTO_SQL;
    }

    public static String getUPDATE_PRODUCTO_SQL() {
        return UPDATE_PRODUCTO_SQL;
    }

    // Getters para las consultas SQL de marcajes
    public static String getINSERT_MARCAJE_SQL() {
        return INSERT_MARCAJE_SQL;
    }
    
    public static String getSELECT_ALL_MARCAJES_ENTRE_FECHAS() {
        return SELECT_ALL_MARCAJES_ENTRE_FECHAS;
    }

    public static String getSELECT_MARCAJE_BY_ID() {
        return SELECT_MARCAJE_BY_ID;
    }

    public static String getSELECT_MARCAJES_BY_AULA() {
        return SELECT_MARCAJES_BY_AULA;
    }

    public static String getSELECT_ALL_MARCAJES() {
        return SELECT_ALL_MARCAJES;
    }

    public static String getDELETE_MARCAJES_BY_PRODUCTO_SQL() {
        return DELETE_MARCAJES_BY_PRODUCTO_SQL;
    }

    public static String getDELETE_MARCAJE_SQL() {
        return DELETE_MARCAJE_SQL;
    }

    public static String getUPDATE_MARCAJE_SQL() {
        return UPDATE_MARCAJE_SQL;
    }

    public static String getSELECT_MARCAJES_BY_PRODUCTO() {
        return SELECT_MARCAJES_BY_PRODUCTO;
    }

    public static String getSELECT_MARCAJES_BY_PRODUCTO_Y_AULA() {
        return SELECT_MARCAJES_BY_PRODUCTO_Y_AULA;
    }

    public static String getCOUNT_MARCAJES_BY_AULA_SQL() {
        return COUNT_MARCAJES_BY_AULA_SQL;
    }

    public static String getINSERT_AULA_SQL() {
        return INSERT_AULA_SQL;
    }

    public static String getSELECT_AULA_BY_ID() {
        return SELECT_AULA_BY_ID;
    }

    public static String getSELECT_ALL_AULAS() {
        return SELECT_ALL_AULAS;
    }

    public static String getDELETE_AULA_SQL() {
        return DELETE_AULA_SQL;
    }

    public static String getUPDATE_AULA_SQL() {
        return UPDATE_AULA_SQL;
    }

    public static String getEXISTS_AULA_SQL() {
        return EXISTS_AULA_SQL;
    }

    public static String getEXISTS_PRODUCTO_SQL() {
        return EXISTS_PRODUCTO_SQL;
    }

}
