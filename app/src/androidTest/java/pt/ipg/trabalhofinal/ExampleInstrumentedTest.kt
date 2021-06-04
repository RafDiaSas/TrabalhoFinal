package pt.ipg.trabalhofinal

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class TesteBaseDados {
    private fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext
    private fun getBDCasosOpenHelper() = BDCasosOpenHelper(getAppContext())
    private fun getTabelaPessoas(db: SQLiteDatabase) = TabelaPessoas(db)
    private fun getTabelAtivos(db: SQLiteDatabase) = TabelaAtivos(db)
    private fun getTabelaRecuperados(db: SQLiteDatabase) = TabelaRecuperados(db)
    private fun getTabelaTotais(db: SQLiteDatabase) = TabelaTotais(db)
    private fun inserePessoas(tabelaPessoas: TabelaPessoas, pessoas: Pessoas): Long {
        val id = tabelaPessoas.insert(pessoas.toContentValues())
        assertNotEquals(-1, id)
        return id
    }
    private fun insereAtivos(tabelaAtivos: TabelaAtivos, ativos: Ativos): Long {
        val id = tabelaAtivos.insert(ativos.toContentValues())
        assertNotEquals(-1, id)
        return id
    }
    private fun insereRecuperados(tabelaRecuperados: TabelaRecuperados, recuperados: Recuperados): Long {
        val id = tabelaRecuperados.insert(recuperados.toContentValues())
        assertNotEquals(-1, id)
        return id
    }
    private fun insereTotais(tabelaTotais: TabelaTotais, totais: Totais): Long {
        val id = tabelaTotais.insert(totais.toContentValues())
        assertNotEquals(-1, id)
        return id
    }

    private fun getPessoasBD(
        tabelaPessoas: TabelaPessoas, id: Long    ): Pessoas {
        val cursor = tabelaPessoas.query(
            TabelaPessoas.TODOS_CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        assertNotNull(cursor)
        assert(cursor!!.moveToNext())
        return Pessoas.fromCursor(cursor)
    }

    private fun getAtivosBD( tabelaAtivos: TabelaAtivos, id: Long    ): Ativos {
        val cursor = tabelaAtivos.query(
            TabelaAtivos.TODOS_CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        assertNotNull(cursor)
        assert(cursor!!.moveToNext())
        return Ativos.fromCursor(cursor)
    }

    private fun getRecuperadosBD(
        tabelaRecuperados: TabelaRecuperados, id: Long    ): Recuperados {
        val cursor = tabelaRecuperados.query(
            TabelaRecuperados.TODOS_CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        assertNotNull(cursor)
        assert(cursor!!.moveToNext())
        return Recuperados.fromCursor(cursor)
    }

    private fun getTotaisBD( tabelaTotais: TabelaTotais, id: Long    ): Totais {
        val cursor = tabelaTotais.query(
            TabelaTotais.TODOS_CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        assertNotNull(cursor)
        assert(cursor!!.moveToNext())
        return Totais.fromCursor(cursor)
    }


    @Before
    fun apagaBD() {
        getAppContext().deleteDatabase(BDCasosOpenHelper.NOME_BASE_DADOS)
    }

    @Test
    fun consegueAbrirBD(){

        val db = getBDCasosOpenHelper().readableDatabase
        assert(db.isOpen)

        db.close()
    }

}