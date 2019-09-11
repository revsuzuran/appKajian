package arm.app.jadwalkajian.response

import arm.app.jadwalkajian.model.Jadwal
import arm.app.jadwalkajian.model.Notif
import arm.app.jadwalkajian.model.Post
import com.google.gson.annotations.SerializedName

class ResponseData {

    @SerializedName("datajadwal")
    var data: List<Jadwal>? = null

    @SerializedName("datapost")
    var dataPost: List<Post>? = null

    @SerializedName("datanotif")
    var dataNotif: List<Notif>? = null
}