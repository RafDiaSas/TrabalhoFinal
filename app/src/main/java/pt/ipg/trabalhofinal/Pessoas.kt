package pt.ipg.trabalhofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Pessoas(var id: Long = -1, var nome: String, var numeroCC: String, var telefone: String, var infetado: Int, var dataDiagnostico: Int, var dataRecuperacao: Int) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply{
            put(TabelaPessoas.CAMPO_NOME, nome)
            put(TabelaPessoas.CAMPO_NUMEROCC, numeroCC)
            put(TabelaPessoas.CAMPO_TELEFONE, telefone)
            put(TabelaPessoas.CAMPO_INFETADO, infetado)
            put(TabelaPessoas.CAMPO_DATADIAGNOSTICO, dataDiagnostico)
            put(TabelaPessoas.CAMPO_DATARECUPERACAO, dataRecuperacao)
        }
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Pessoas {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNome = cursor.getColumnIndex(TabelaPessoas.CAMPO_NOME)
            val colNumeroCC = cursor.getColumnIndex(TabelaPessoas.CAMPO_NUMEROCC)
            val colTelefone = cursor.getColumnIndex(TabelaPessoas.CAMPO_TELEFONE)
            val colInfetado = cursor.getColumnIndex(TabelaPessoas.CAMPO_INFETADO)
            val colDataDiagnostico = cursor.getColumnIndex(TabelaPessoas.CAMPO_DATADIAGNOSTICO)
            val colDataRecuperacao = cursor.getColumnIndex(TabelaPessoas.CAMPO_DATARECUPERACAO)

            val id = cursor.getLong(colId)
            val nome = cursor.getString(colNome)
            val numeroCC = cursor.getString(colNumeroCC)
            val telefone = cursor.getString(colTelefone)
            val infetado = cursor.getInt(colInfetado)
            val dataDiagnostico = cursor.getInt(colDataDiagnostico)
            val dataRecuperacao = cursor.getInt(colDataRecuperacao)

            return Pessoas(id, nome, numeroCC, telefone, infetado, dataDiagnostico, dataRecuperacao)
        }
    }
}