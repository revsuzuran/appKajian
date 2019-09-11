package arm.app.jadwalkajian.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import arm.app.jadwalkajian.R
import arm.app.jadwalkajian.adapter.JadwalAdapter
import arm.app.jadwalkajian.model.Jadwal
import arm.app.jadwalkajian.presenter.PresenterJadwal
import arm.app.jadwalkajian.ui.MainActivity.Companion.status
import arm.app.jadwalkajian.view.JadwalView
import com.rilixtech.materialfancybutton.MaterialFancyButton
import kotlinx.android.synthetic.main.fragment_jadwal.*
import kotlinx.android.synthetic.main.item_hari.*


class JadwalFragment : Fragment(), JadwalView {

    private val TAG:String = "AQIL PRAKOSO"
    private var presenter: PresenterJadwal? = null
    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var btn:MaterialFancyButton? = null

    companion object {
        fun newInstance(): JadwalFragment {
            val fragment = JadwalFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()

        btn?.setOnClickListener{
            context?.startActivity(Intent(context, TambahKajian::class.java))
        }
        semua.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            presenter?.Tampil()
        }
        ahadPahing.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            presenter?.TampilSingle("Ahad Pahing")
        }
        ahadPon.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            presenter?.TampilSingle("Ahad Pon")
        }
        ahadWage.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            presenter?.TampilSingle("Ahad Wage")
        }
        selasaKliwon.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            presenter?.TampilSingle("Selasa Kliwon")
        }
        kamisPahing.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            presenter?.TampilSingle("Kamis Pahing")
        }
        khusus.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            presenter?.TampilSingle("Khusus")
        }

    }

    private fun initView(){

        recyclerView = view?.findViewById(R.id.rv_jadwal)
        progressBar = view?.findViewById(R.id.pgbar_fragment_jadwal)
        btn = view?.findViewById(R.id.btn_add_jadwal)
        presenter = PresenterJadwal(this,context)
        presenter?.Tampil()
        progressBar?.visibility = View.VISIBLE
        Log.d(TAG, "tes")

        if(status == "admin") {
            btn?.visibility = View.VISIBLE
        }else{
            btn?.visibility = View.GONE
        }

    }

    override fun Hasil(status: Boolean, msg: String) {
    }

    override fun DataJadwal(result: List<Jadwal>) {

        Log.d(TAG, "$result")
        if(result.isEmpty()){
            tidakada?.visibility = View.VISIBLE
        }else{
            tidakada?.visibility = View.GONE
        }
        val adapter = JadwalAdapter(context, result)
        val linear = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = linear
        progressBar?.visibility = View.GONE
        adapter.notifyDataSetChanged()

    }

    override fun onResume() {
        super.onResume()
        presenter?.Tampil()
    }


}
