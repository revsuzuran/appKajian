package arm.app.jadwalkajian.presenter

import arm.app.jadwalkajian.init.InitRetrofit
import arm.app.jadwalkajian.model.Post
import arm.app.jadwalkajian.response.ResponseData
import arm.app.jadwalkajian.response.ResponseInsert
import arm.app.jadwalkajian.view.PostView
import retrofit2.Call
import retrofit2.Response

class PresenterPost(var view: PostView?){

    fun Tampil(){
        val getInit = InitRetrofit().getInitInstance()
        val request = getInit.request_getpost()
        request.enqueue(object : retrofit2.Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<ResponseData>?, response: Response<ResponseData>?) {
                val result = response?.body()?.dataPost

                if (result != null) {
                    view?.DataPost(result)
                }else{

                }
            }

        })

    }

    fun InsertPost(gambar: String, text: String, tanggal: String){
        val getInit = InitRetrofit().getInitInstance()
        val request = getInit.request_insertpost(gambar,text,tanggal)
        request.enqueue(object : retrofit2.Callback<ResponseInsert> {
            override fun onFailure(call: Call<ResponseInsert>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<ResponseInsert>?, response: Response<ResponseInsert>?) {
                if (response != null){
                    val msg = response.body()?.msg
                    val status = response.body()?.status

                    if (msg != null && status !=null) {
                        view?.Hasil(status,msg)
                    }else{
                        view?.Hasil(false,"gagal")
                    }
                }
            }

        })

    }

}