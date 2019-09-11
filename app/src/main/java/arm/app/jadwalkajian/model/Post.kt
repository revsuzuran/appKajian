package arm.app.jadwalkajian.model

import com.google.gson.annotations.SerializedName

class Post {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("gambar")
    var gambar: String? = null
    @SerializedName("text")
    var text: String? = null
    @SerializedName("tanggal")
    var tanggal: String? = null

}
