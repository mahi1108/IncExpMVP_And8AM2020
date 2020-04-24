package cubex.mahesh.incexpmvp_and8am2020.view

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import cubex.mahesh.incexpmvp_and8am2020.R
import cubex.mahesh.incexpmvp_and8am2020.model.IncExpDTO
import cubex.mahesh.incexpmvp_and8am2020.model.IncExpModel
import cubex.mahesh.incexpmvp_and8am2020.presenter.IncExpPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),IncExpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun insert(v:View){
        var dto = IncExpDTO(
            date.text.toString(),
            amount.text.toString().toInt(),
            type.selectedItem.toString(),
            description.text.toString())
        var iep: IncExpPresenter = IncExpModel(this@MainActivity)
        iep.insertData(dto)
    }

    fun read(v:View){
        var iep: IncExpPresenter = IncExpModel(this@MainActivity)
        iep.readData()
    }

    override fun insertView(status: Long) {
        if(status != -1.toLong()){
            Toast.makeText(this@MainActivity,
                "Record Inserted successfully",
                Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this@MainActivity,
                "Record Insertion Failed...",
                Toast.LENGTH_LONG).show()
        }
    }

    override fun readView(c: Cursor) {
        var cadapter = SimpleCursorAdapter(
            this@MainActivity,
            R.layout.indi_row,
            c,
            arrayOf("_date","_amount","_type","_desc"),
            intArrayOf(R.id.date,R.id.amount,R.id.type,R.id.desc),
            0)
        lview.adapter = cadapter

        var _isum = 0
        var _esum = 0
        while (c.moveToNext()){
            if(c.getString(2).equals("Income")){
                _isum = _isum + c.getInt(c.getColumnIndex("_amount"))
            }else{
                _esum = _esum+ c.getInt(c.getColumnIndex("_amount"))
            }
        }
        isum.text = "Income Sum \n $_isum"
        esum.text = "Expense Sum \n $_esum"
    }
}
