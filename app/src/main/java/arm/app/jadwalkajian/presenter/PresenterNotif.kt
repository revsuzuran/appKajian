package arm.app.jadwalkajian.presenter

import arm.app.jadwalkajian.init.InitRetrofit
import arm.app.jadwalkajian.model.Notif
import arm.app.jadwalkajian.response.ResponseData
import arm.app.jadwalkajian.response.ResponseInsert
import arm.app.jadwalkajian.view.NotifView
import retrofit2.Call
import retrofit2.Response

class PresenterNotif(var view: NotifView?){

    fun Tampil(){
        val getInit = InitRetrofit().getInitInstance()
        val request = getInit.request_getnotif()
        request.enqueue(object : retrofit2.Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<ResponseData>?, response: Response<ResponseData>?) {
                val result = response?.body()?.dataNotif

                if (result != null) {
                    view?.DataNotif(result)
                }else{

                }
            }

        })

    }

    fun UpdateNotif(notif: Notif){
        val getInit = InitRetrofit().getInitInstance()
        val request = getInit.request_upNotif(notif)
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