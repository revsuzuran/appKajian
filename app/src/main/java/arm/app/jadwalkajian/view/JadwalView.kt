package arm.app.jadwalkajian.view

import arm.app.jadwalkajian.model.Jadwal

interface JadwalView {
    fun Hasil(status: Boolean, msg : String)
    fun DataJadwal(result: List<Jadwal>)
    }