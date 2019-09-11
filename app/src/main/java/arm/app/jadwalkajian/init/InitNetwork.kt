package arm.app.jadwalkajian.init

import arm.app.jadwalkajian.model.Admin
import arm.app.jadwalkajian.model.Jadwal
import arm.app.jadwalkajian.model.Notif
import arm.app.jadwalkajian.model.Post
import arm.app.jadwalkajian.response.ResponseData
import arm.app.jadwalkajian.response.ResponseInsert
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class InitRetrofit {
    fun getInitRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("http://kajian.arm.click/")
            .addConverterFactory(
            GsonConverterFactory.create())
            .build()

    }

    fun getInitInstance(): ApiService {
        return getInitRetrofit().create(ApiService::class.java)
    }

}

interface ApiService {

    @GET("get_jadwal")
    fun request_getdata(): Call<ResponseData>

    @GET("get_jadwal/{harix}")
    fun request_getSingleData(@Path("harix") hari:String): Call<ResponseData>

    @POST("insert_jadwal")
    fun request_insert(@Body jadwal: Jadwal?): Call<ResponseInsert>

    @PUT("update_jadwal")
    fun request_update(@Body jadwal: Jadwal): Call<ResponseInsert>

    @DELETE("rm_jadwal/{id}")
    fun request_delete(@Path("id") id: Long): Call<ResponseInsert>


    @POST("auth")
    fun request_auth(@Body admin: Admin): Call<ResponseInsert>

    @FormUrlEncoded
    @POST("insert_post")
    fun request_insertpost(
        @Field("gambar") gambar:String,
        @Field("text") text:String,
        @Field("tanggal") tanggal:String): Call<ResponseInsert>

    @GET("get_post")
    fun request_getpost(): Call<ResponseData>

    @GET("get_notif")
    fun request_getnotif(): Call<ResponseData>

    @PUT("update_notif")
    fun request_upNotif(@Body notif: Notif?): Call<ResponseInsert>

    @DELETE("rm_post/{id}")
    fun request_deletePost(@Path("id") id: Long): Call<ResponseInsert>

}