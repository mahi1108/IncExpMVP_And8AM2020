package cubex.mahesh.incexpmvp_and8am2020.presenter

import cubex.mahesh.incexpmvp_and8am2020.model.IncExpDTO

interface IncExpPresenter {
    fun insertData(dto:IncExpDTO)
    fun readData()
}