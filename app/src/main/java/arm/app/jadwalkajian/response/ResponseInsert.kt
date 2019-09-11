package arm.app.jadwalkajian.response

import com.google.gson.annotations.SerializedName

class ResponseInsert {

    @SerializedName("status")
    var status : Boolean? = null
    @SerializedName("msg")
    var msg : String? = null
}