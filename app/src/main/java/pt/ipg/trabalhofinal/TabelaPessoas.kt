package pt.ipg.trabalhofinal

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaPessoas (db: SQLiteDatabase) : BaseColumns {
    private val db : SQLiteDatabase = db
    fun cria() = db?.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME TEXT NOT NULL, $CAMPO_NUMEROCC TEXT NOT NULL, $CAMPO_TELEFONE TEXT NOT NULL, $CAMPO_INFETADO INTEGER NOT NULL, $CAMPO_DATADIAGNOSTICO INTEGER NOT NULL, $CAMPO_DATARECUPERACAO INTEGER, $CAMPO_ID_HOSPITAL INTEGER NOT NULL, FOREIGN KEY ($CAMPO_ID_HOSPITAL) REFERENCES ${TabelaHospital.NOME_TABELA})")

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
        const val NOME_TABELA = "Pessoas"
        const val CAMPO_NOME = "Nome"
        const val CAMPO_NUMEROCC = "Numero_CC"
        const val CAMPO_TELEFONE = "Telefone"
        const val CAMPO_INFETADO = "Infetado"
        const val CAMPO_DATADIAGNOSTICO = "Data_Teste_Positivo"
        const val CAMPO_DATARECUPERACAO = "Data_Recuperacao"
        const val CAMPO_ID_HOSPITAL = "Campo_ID_Hospital"

        val TODOS_CAMPOS =arrayOf(BaseColumns._ID, CAMPO_NOME, CAMPO_NUMEROCC, CAMPO_TELEFONE, CAMPO_INFETADO, CAMPO_DATADIAGNOSTICO, CAMPO_DATARECUPERACAO, CAMPO_ID_HOSPITAL)

    }

}