package pt.ipg.trabalhofinal

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaObitos (db: SQLiteDatabase) : BaseColumns {
    private val db : SQLiteDatabase = db
    fun cria() = db?.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_DATAOBITO INTEGER NOT NULL,$CAMPO_ID_PESSOA, $CAMPO_ID_HOSPITAL INTEGER NOT NULL,FOREIGN KEY ($CAMPO_ID_PESSOA) REFERENCES ${TabelaPessoas.NOME_TABELA}, FOREIGN KEY ($CAMPO_ID_HOSPITAL) REFERENCES ${TabelaHospital.NOME_TABELA})")

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
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor? {
        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object{
        const val NOME_TABELA = "Obitos"
        const val CAMPO_DATAOBITO = "Data_Obito"
        const val CAMPO_ID_PESSOA = "Campo_ID_Pessoa"
        const val CAMPO_ID_HOSPITAL = "Campo_ID_Hospital"

        val TODOS_CAMPOS =arrayOf(BaseColumns._ID, CAMPO_DATAOBITO, CAMPO_ID_PESSOA, CAMPO_ID_HOSPITAL)

    }

}