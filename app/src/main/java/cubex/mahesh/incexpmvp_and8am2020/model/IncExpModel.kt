package cubex.mahesh.incexpmvp_and8am2020.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import cubex.mahesh.incexpmvp_and8am2020.presenter.IncExpPresenter
import cubex.mahesh.incexpmvp_and8am2020.view.IncExpView
import cubex.mahesh.incexpmvp_and8am2020.view.MainActivity

class IncExpModel(var activity: MainActivity):IncExpPresenter{

    var dBase:SQLiteDatabase? = null
    val _ID = "_id"
    val _DATE = "_date"
    val _TYPE = "_type"
    val _AMOUNT = "_amount"
    val _DESC = "_desc"

    init {
            dBase = activity.openOrCreateDatabase(
   "and8am2020", Context.MODE_PRIVATE,null)
        dBase?.execSQL("create table if not exists incexp($_ID integer primary key autoincrement,$_DATE varchar(15), $_TYPE varchar(15), $_AMOUNT number,$_DESC varchar(100) )")
    }

    override fun insertData(dto: IncExpDTO) {
        var cvalues = ContentValues()
        cvalues.put(_DATE, dto._date)
        cvalues.put(_AMOUNT, dto._amount)
        cvalues.put(_TYPE, dto._type)
        cvalues.put(_DESC, dto._desc)

       var status = dBase?.insert("incexp", null, cvalues)
        activity.insertView(status!!)
    }

    override fun readData() {
            var c:Cursor = dBase!!.query(
                "incexp",null,null,null,
                null,null,null)
        activity.readView(c)
    }

}