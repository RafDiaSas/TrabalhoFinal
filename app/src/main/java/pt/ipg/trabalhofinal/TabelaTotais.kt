package pt.ipg.trabalhofinal

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaTotais (db: SQLiteDatabase) : BaseColumns {
    private val db : SQLiteDatabase = db
    fun cria() = db?.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_TOTAIS INTEGER NOT NULL, $CAMPO_ATIVOS INTEGER NOT NULL, $CAMPO_RECUPERADOS INTEGER NOT NULL)")

    fun insert(values: ContentValues): Long {
        return db.insert(NOME_TABELA, null, values)
    }

    fun update(values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return db.update(NOME_TABELA, values, whereClause, whereArgs)
    }

    fun delete(whereClause: String, whereArgs: Array<String>): Int {
        return db.delete(NOME_TABELA, whereClause, whereArgs)
    }

    fun query(
        columns: Array<String>,
        selection: String,
        selectionArgs: Array<String>,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor? {
        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object{
        const val NOME_TABELA = "CasosTotais"
        const val CAMPO_TOTAIS = "NTotais"
        const val CAMPO_ATIVOS = "NAtivos"
        const val CAMPO_RECUPERADOS = "NRecuperados"

        val TODOS_CAMPOS =arrayOf(BaseColumns._ID, CAMPO_TOTAIS, CAMPO_ATIVOS, CAMPO_RECUPERADOS)

    }
}