package arm.app.jadwalkajian.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import arm.app.jadwalkajian.R
import arm.app.jadwalkajian.model.Notif
import arm.app.jadwalkajian.presenter.PresenterNotif
import arm.app.jadwalkajian.utils.SharedPref
import arm.app.jadwalkajian.view.NotifView
import com.rilixtech.materialfancybutton.MaterialFancyButton
import kotlinx.android.synthetic.main.fragment_notif.*

class NotifFragment : Fragment(), NotifView {

    private var presenter: PresenterNotif? = null
    private var progressBar: ProgressBar? = null
    private lateinit var sharedPreference: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notif, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPreference = SharedPref(context!!)

        if(sharedPreference.getValueString("status") == "admin") {
            btn_edit_notif.visibility = View.VISIBLE

        }

        progressBar = view?.findViewById(R.id.pgbar_fragment_notif)
        presenter = PresenterNotif(this)
        presenter?.Tampil()
        progressBar?.visibility = View.VISIBLE
        Log.d("aqil", "tes")
    }


    override fun Hasil(status: Boolean, msg: String) {
        progressBar?.visibility = View.GONE
        if(msg == "sukses"){
            Toast.makeText(context, "Berhasil Menyimpan Data!", Toast.LENGTH_SHORT).show()
        }  else{
            Toast.makeText(context, "Gagal Menyimpan Data!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun DataNotif(result: List<Notif>) {

        progressBar?.visibility = View.GONE
        if(result.isEmpty()){
            Toast.makeText(context, "Gagal Mengambil Data!", Toast.LENGTH_SHORT).show()
        }else{
            pesan.text = result[0].pesan
            btn_edit_notif.setOnClickListener{
                showDia(result[0].pesan)
            }
        }
    }

    private fun showDia(pesan: String?) {

        val inflater = layoutInflater
        val views = inflater.inflate(R.layout.dialog_notif, null)
        val builder = AlertDialog.Builder(context!!)
            .setView(views)
        val dialog = builder.show()

        dialog.setView(views)
        dialog.setCancelable(true)

        val mPesan = views.findViewById<EditText>(R.id.pesanEdit)
        val btnkirim = views.findViewById<MaterialFancyButton>(R.id.btn_kirim)

        mPesan.setText(pesan)

        btnkirim.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            val notif = Notif()
            notif.id = 1
            notif.pesan = mPesan.text.toString()
            notif.tanggal = "tes"
            presenter?.UpdateNotif(notif)
            dialog.dismiss()

        }


    }

}
