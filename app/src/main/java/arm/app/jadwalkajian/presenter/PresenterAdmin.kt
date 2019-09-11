package arm.app.jadwalkajian.presenter

import arm.app.jadwalkajian.init.InitRetrofit
import arm.app.jadwalkajian.model.Admin
import arm.app.jadwalkajian.response.ResponseInsert
import arm.app.jadwalkajian.view.AdminView
import retrofit2.Call
import retrofit2.Response

class PresenterAdmin(var view: AdminView?){

    fun Auth(admin: Admin){
        val getInit = InitRetrofit().getInitInstance()
        val request = getInit.request_auth(admin)
        request.enqueue(object : retrofit2.Callback<ResponseInsert> {
            override fun onFailure(call: Call<ResponseInsert>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<ResponseInsert>?, response: Response<ResponseInsert>?) {
                if (response != null){
                    val msg = response.body()?.msg!!
                    val status = response.body()?.status!!

                    view?.Hasil(status,msg)
                }
            }

        })

    }


}