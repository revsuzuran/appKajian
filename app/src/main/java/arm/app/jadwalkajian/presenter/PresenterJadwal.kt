package arm.app.jadwalkajian.presenter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import arm.app.jadwalkajian.init.InitRetrofit
import arm.app.jadwalkajian.model.Jadwal
import arm.app.jadwalkajian.response.ResponseData
import arm.app.jadwalkajian.response.ResponseInsert
import arm.app.jadwalkajian.view.JadwalView
import retrofit2.Call
import retrofit2.Response

class PresenterJadwal(var view: JadwalView?, var context: Context?){

    fun Tampil(){
        Log.d("aqil","testtest")
        val getInit = InitRetrofit().getInitInstance()
        val request = getInit.request_getdata()
        request.enqueue(object : retrofit2.Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<ResponseData>?, response: Response<ResponseData>?) {
                if (response != null){
                    val result = response.body()?.data

                    if (result != null) {
                        view?.DataJadwal(result)
                    }else{
                        Toast.makeText(context, "Gagal Mengambil Data!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })

    }

    fun InsertJadwal(jadwal: Jadwal?){
        Log.d("aqil","testtest")
        val getInit = InitRetrofit().getInitInstance()
        val request = getInit.request_insert(jadwal)
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

    fun TampilSingle(hari: String){
        Log.d("aqil","testtest")
        val getInit = InitRetrofit().getInitInstance()
        val request = getInit.request_getSingleData(hari)
        request.enqueue(object : retrofit2.Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<ResponseData>?, response: Response<ResponseData>?) {
                if (response != null){
                    val result = response.body()?.data

                    if (result != null) {
                        view?.DataJadwal(result)
                    }else{
                        Toast.makeText(context, "Gagal Mengambil Data!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })

    }

    fun HapusJadwal(id: Long){
        Log.d("aqil","testtest")
        val getInit = InitRetrofit().getInitInstance()
        val request = getInit.request_delete(id)
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