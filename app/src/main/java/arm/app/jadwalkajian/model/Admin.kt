package arm.app.jadwalkajian.model

import com.google.gson.annotations.SerializedName

class Admin {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("username")
    var username: String? = null
    @SerializedName("password")
    var password: String? = null

}
