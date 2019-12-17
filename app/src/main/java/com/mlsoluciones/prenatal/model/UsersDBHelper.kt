package com.mlsoluciones.prenatal.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class UsersDBHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TUSUARIO)
        //db.execSQL(SQL_CREATE_TEXAMEN)
        //db.execSQL(SQL_CREATE_TIMAGEN)
        //insertTexamen(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_TUSUARIO)
        //db.execSQL(SQL_DELETE_TEXAMEN)
        //db.execSQL(SQL_DELETE_TIMAGEN)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser(user: UserModel): Long {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Cree un nuevo mapa de valores, donde los nombres de columna son las claves
        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_NOMBRE, user.nombre)
        values.put(DBContract.UserEntry.COLUMN_EMAIL, user.email)
        values.put(DBContract.UserEntry.COLUMN_TELEFONO, user.telefono)
        values.put(DBContract.UserEntry.COLUMN_CONTRASENA, user.contrasena)
        values.put(DBContract.UserEntry.COLUMN_EDAD_GESTACIONAL, user.edadGestacional)
        values.put(DBContract.UserEntry.COLUMN_SEMANA_GESTACION_ECOGRAFIA, user.semanaGestacionEcografia)
        values.put(DBContract.UserEntry.COLUMN_FECHA_PROBABLE_PARTO, user.fechaProbableParto)
        values.put(DBContract.UserEntry.COLUMN_MADRE_RH_NEGATIVO, user.madreRhNegativo)
        values.put(DBContract.UserEntry.COLUMN_PADRE_RH_POSITIVO, user.padreRhPositivo)
        values.put(DBContract.UserEntry.COLUMN_HIJO_ANT_RH_POSITIVO, user.hijoAnteriorRhPositivo)

        // Inserte la nueva fila, devolviendo el valor de la clave principal de la nueva fila
        return db.insert(DBContract.UserEntry.TABLE_NAME, null, values)
    }

    fun readAllUsers(): ArrayList<UserModel> {
        val users = ArrayList<UserModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM " + DBContract.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_TUSUARIO)
            return ArrayList()
        }

        var userid: Int
        var nombre: String
        var email: String
        var telefono: String
        var contrasena: String
        var edadGestacional: Float
        var semanaGestacionEcografia: Float
        var fechaProbableParto: String
        var madreRhNegativo: Int
        var padreRhPositivo: Int
        var hijoAnteriorRhPositivo: Int
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userid = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_ID_USER))
                nombre = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NOMBRE))
                email = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_EMAIL))
                telefono = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TELEFONO))
                contrasena = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_CONTRASENA))
                edadGestacional = cursor.getFloat(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_EDAD_GESTACIONAL))
                semanaGestacionEcografia = cursor.getFloat(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_SEMANA_GESTACION_ECOGRAFIA))
                fechaProbableParto = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_FECHA_PROBABLE_PARTO))
                madreRhNegativo = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MADRE_RH_NEGATIVO))
                padreRhPositivo = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_PADRE_RH_POSITIVO))
                hijoAnteriorRhPositivo = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_HIJO_ANT_RH_POSITIVO))

                users.add(UserModel(userid, nombre, email, telefono, contrasena, edadGestacional, semanaGestacionEcografia, fechaProbableParto,
                                    madreRhNegativo, padreRhPositivo, hijoAnteriorRhPositivo))
                cursor.moveToNext()
            }
        }
        return users
    }

    fun loginUser(email: String, pass: String): Int {
        val users = ArrayList<UserModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM " + DBContract.UserEntry.TABLE_NAME + " WHERE " +
                    DBContract.UserEntry.COLUMN_EMAIL + "='" + email + "' AND " +
                    DBContract.UserEntry.COLUMN_CONTRASENA + "='" + pass + "'", null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_TUSUARIO)
            return 0
        }

        var userid = 0

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userid = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_ID_USER))

                cursor.moveToNext()
            }
        }
        return userid
    }

    companion object {
        // Si cambia el esquema de la base de datos, debe incrementar la versión de la base de datos.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "PrenatalApp.db"

        private val SQL_CREATE_TUSUARIO =
            "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                    DBContract.UserEntry.COLUMN_ID_USER + " INTEGER PRIMARY KEY autoincrement," +
                    DBContract.UserEntry.COLUMN_NOMBRE + " TEXT," +
                    DBContract.UserEntry.COLUMN_EMAIL + " TEXT," +
                    DBContract.UserEntry.COLUMN_TELEFONO + " TEXT," +
                    DBContract.UserEntry.COLUMN_CONTRASENA + " TEXT," +
                    DBContract.UserEntry.COLUMN_EDAD_GESTACIONAL + " REAL," +
                    DBContract.UserEntry.COLUMN_SEMANA_GESTACION_ECOGRAFIA + " REAL," +
                    DBContract.UserEntry.COLUMN_FECHA_PROBABLE_PARTO + " DATE," +
                    DBContract.UserEntry.COLUMN_MADRE_RH_NEGATIVO + " INTEGER," +
                    DBContract.UserEntry.COLUMN_PADRE_RH_POSITIVO + " INTEGER," +
                    DBContract.UserEntry.COLUMN_HIJO_ANT_RH_POSITIVO + " INTEGER)"

        private val SQL_DELETE_TUSUARIO = "DROP TABLE IF EXISTS " + DBContract.UserEntry.TABLE_NAME

        private val SQL_CREATE_TEXAMEN =
            "CREATE TABLE " + DBContract.ExamenEntry.TABLE_NAME + " (" +
                    DBContract.ExamenEntry.COLUMN_ID_EXAM + " INTEGER PRIMARY KEY autoincrement," +
                    DBContract.ExamenEntry.COLUMN_NOMBRE_EXAMEN + " TEXT," +
                    DBContract.ExamenEntry.COLUMN_NUM_TRIMESTRE + " INTEGER," +
                    DBContract.ExamenEntry.COLUMN_TIPO_EXAMEN + " INTEGER," +
                    DBContract.ExamenEntry.COLUMN_EXAMEN + " INTEGER)"

        private val SQL_DELETE_TEXAMEN = "DROP TABLE IF EXISTS " + DBContract.ExamenEntry.TABLE_NAME

    }

}