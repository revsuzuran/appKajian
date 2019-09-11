package arm.app.jadwalkajian.model

import com.google.gson.annotations.SerializedName

class Notif {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("pesan")
    var pesan: String? = null
    @SerializedName("tanggal")
    var tanggal: String? = null

}