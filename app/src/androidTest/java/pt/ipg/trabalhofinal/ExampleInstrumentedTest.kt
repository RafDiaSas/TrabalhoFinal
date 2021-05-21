package pt.ipg.trabalhofinal

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class TesteBaseDados {
    private fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext
    private fun getBDCasosOpenHelper() = BDCasosOpenHelper(getAppContext())


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