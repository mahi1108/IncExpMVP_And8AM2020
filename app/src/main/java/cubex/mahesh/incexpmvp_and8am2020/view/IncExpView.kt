package cubex.mahesh.incexpmvp_and8am2020.view

import android.database.Cursor

interface IncExpView {
        fun insertView(status:Long)
        fun readView(c:Cursor)
}