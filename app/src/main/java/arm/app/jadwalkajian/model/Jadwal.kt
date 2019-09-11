package arm.app.jadwalkajian.model

import com.google.gson.annotations.SerializedName

class Jadwal {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("pemateri")
    var pemateri: String? = null
    @SerializedName("tanggal")
    var tanggal: String? = null
    @SerializedName("hari")
    var hari: String? = null
    @SerializedName("jam_mulai")
    var jamMulai: String? = null
    @SerializedName("jam_selesai")
    var jamSelesai: String? = null
    @SerializedName("tempat")
    var tempat: String? = null
    @SerializedName("alamat")
    var alamat: String? = null
    @SerializedName("long")
    var long: String? = null
    @SerializedName("lang")
    var lang: String? = null



}