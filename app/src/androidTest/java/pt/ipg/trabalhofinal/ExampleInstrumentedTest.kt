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


    @Test
    fun consegueInserirPessoas(){
        val db = getBDCasosOpenHelper().writableDatabase
        val tabelaPessoas = getTabelaPessoas(db)
        val pessoas = Pessoas(nome="Daniel", numeroCC = "87690271", telefone = "+355 939128700", estado = 1, dataD = 25102020, dataR = 31122020)

        pessoas.id = inserePessoas(tabelaPessoas, pessoas)
        val pessoasBD = getPessoasBD(tabelaPessoas, pessoas.id)
        assertEquals(pessoas, pessoasBD)
        db.close()
    }

    @Test
    fun consegueAlterarPessoas(){
        val db = getBDCasosOpenHelper().writableDatabase

        val tabelaPessoas = getTabelaPessoas(db)

        val pessoas = Pessoas(nome="Daniel", numeroCC = "87690271", telefone = "+355 939128700", estado = 1, dataD = 25102020, dataR = 31122020)

        pessoas.id = inserePessoas(tabelaPessoas, pessoas)
        pessoas.nome="Daniel"
        pessoas.numeroCC="00000000"
        pessoas.telefone="977456449"
        pessoas.estado=2
        pessoas.dataD=11012021
        pessoas.dataR=12012021
        val registosAlterados = tabelaPessoas.update(
            pessoas.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(pessoas.id.toString())
        )

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueApagarPessoas(){
        val db = getBDCasosOpenHelper().writableDatabase
        val tabelaPessoas = getTabelaPessoas(db)
        val pessoas = Pessoas(nome="Daniel", numeroCC = "87690271", telefone = "+355 939128700", estado = 1, dataD = 25102020, dataR = 31122020)


        pessoas.id = inserePessoas(tabelaPessoas, pessoas)

        val registosEliminados =tabelaPessoas.delete(
            "${BaseColumns._ID}=?",
            arrayOf(pessoas.id.toString())
        )

        assertEquals(1, registosEliminados)

        db.close()
    }
}