package arm.app.jadwalkajian.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import arm.app.jadwalkajian.R
import arm.app.jadwalkajian.model.Jadwal
import arm.app.jadwalkajian.ui.DetailJadwalActivity
import arm.app.jadwalkajian.utils.BulanHelper

class JadwalAdapter(context: Context?, data: List<Jadwal>?) :
    RecyclerView.Adapter<JadwalAdapter.MyHolder>() {

    var context : Context? = context
    var jadwal : List<Jadwal>? = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalAdapter.MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_jadwal, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return jadwal?.size!!
    }

    override fun onBindViewHolder(holder: JadwalAdapter.MyHolder, position: Int) {

//        if(kondisi == 0) {
//            holder.btnPilih?.visibility = View.GONE
//            holder.btnLihat?.visibility = View.GONE
//            holder.btnLihat2?.visibility = View.VISIBLE
//        }else {
//            holder.btnPilih?.visibility = View.VISIBLE
//            holder.btnLihat?.visibility = View.VISIBLE
//            holder.btnLihat2?.visibility = View.GONE
//        }

        val textnya = jadwal?.get(position)?.tanggal?.replace("-","/")
        val datanya = textnya?.split("/")?.toMutableList()
        val bulanHelper = BulanHelper()
        val bulan = bulanHelper.convertToBulan(datanya?.get(1).toString())
        holder.jam?.text = "${jadwal?.get(position)?.jamMulai} - ${jadwal?.get(position)?.jamSelesai}"
        holder.nPemateri?.text = jadwal?.get(position)?.pemateri
        holder.nTempat?.text = jadwal?.get(position)?.tempat
        holder.nAlamat?.text = jadwal?.get(position)?.alamat
        holder.nHari?.text = jadwal?.get(position)?.hari
        holder.nTanggal?.text = datanya?.get(0).toString()
        holder.nBulantahun?.text = "$bulan ${datanya?.get(2).toString()}"
        holder.itemLayout?.setOnClickListener {

            val intent = Intent(context, DetailJadwalActivity::class.java)
            intent.putExtra("id", jadwal?.get(position)?.id?.toLong())
            intent.putExtra("pemateri", jadwal?.get(position)?.pemateri)
            intent.putExtra("hari", jadwal?.get(position)?.hari)
            intent.putExtra("tanggal", jadwal?.get(position)?.tanggal)
            intent.putExtra("tempat", jadwal?.get(position)?.tempat)
            intent.putExtra("alamat", jadwal?.get(position)?.alamat)
            intent.putExtra("jam_mulai", jadwal?.get(position)?.jamMulai)
            intent.putExtra("jam_selesai", jadwal?.get(position)?.jamSelesai)
            intent.putExtra("lang", jadwal?.get(position)?.lang?.toDouble())
            intent.putExtra("long", jadwal?.get(position)?.long?.toDouble())

            intent.putExtra("mTanggal", datanya?.get(0).toString())
            intent.putExtra("mBulanTahun", "$bulan ${datanya?.get(2).toString()}")
            context?.startActivity(intent)

        }


    }

    class MyHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var nPemateri : TextView? = itemView?.findViewById(R.id.pemateri)
        var nTempat : TextView? = itemView?.findViewById(R.id.tempat)
        var nAlamat : TextView? = itemView?.findViewById(R.id.alamat)
        var nHari : TextView? = itemView?.findViewById(R.id.hari)
        var nTanggal : TextView? = itemView?.findViewById(R.id.tanggal)
        var nBulantahun : TextView? = itemView?.findViewById(R.id.bulanTahun)
        var itemLayout : CardView? = itemView?.findViewById(R.id.item_layout)
        var jam: TextView? = itemView?.findViewById(R.id.jam)

    }


}