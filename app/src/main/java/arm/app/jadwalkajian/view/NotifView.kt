package arm.app.jadwalkajian.view

import arm.app.jadwalkajian.model.Notif

interface NotifView {
    fun Hasil(status: Boolean, msg : String)
    fun DataNotif(result: List<Notif>)
}