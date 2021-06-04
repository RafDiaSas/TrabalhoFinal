package pt.ipg.trabalhofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Pessoas(var id: Long = -1, var nome: String, var numeroCC: String, var telefone: String, var estado: Int, var dataD: Int, var dataR: Int) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply{
            put(TabelaPessoas.CAMPO_NOME, nome)
            put(TabelaPessoas.CAMPO_NUMEROCC, numeroCC)
            put(TabelaPessoas.CAMPO_TELEFONE, telefone)
            put(TabelaPessoas.CAMPO_ESTADO, estado)
            put(TabelaPessoas.CAMPO_DATADIAGNOSTICO, dataD)
            put(TabelaPessoas.CAMPO_DATARECUPERACAO, dataR)
        }
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Pessoas {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNome = cursor.getColumnIndex(TabelaPessoas.CAMPO_NOME)
            val colNumeroCC = cursor.getColumnIndex(TabelaPessoas.CAMPO_NUMEROCC)
            val colTelefone = cursor.getColumnIndex(TabelaPessoas.CAMPO_TELEFONE)
            val colEstado = cursor.getColumnIndex(TabelaPessoas.CAMPO_ESTADO)
            val colDataD = cursor.getColumnIndex(TabelaPessoas.CAMPO_DATADIAGNOSTICO)
            val colDataR = cursor.getColumnIndex(TabelaPessoas.CAMPO_DATARECUPERACAO)

            val id = cursor.getLong(colId)
            val nome = cursor.getString(colNome)
            val numeroCC = cursor.getString(colNumeroCC)
            val telefone = cursor.getString(colTelefone)
            val estado = cursor.getInt(colEstado)
            val dataD = cursor.getInt(colDataD)
            val dataR = cursor.getInt(colDataR)

            return Pessoas(id, nome, numeroCC, telefone, estado, dataD, dataR)
        }
    }
}