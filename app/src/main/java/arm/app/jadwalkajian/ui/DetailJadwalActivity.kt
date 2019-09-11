package arm.app.jadwalkajian.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import arm.app.jadwalkajian.R
import arm.app.jadwalkajian.model.Jadwal
import arm.app.jadwalkajian.presenter.PresenterJadwal
import arm.app.jadwalkajian.utils.SharedPref
import arm.app.jadwalkajian.view.JadwalView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail_jadwal.*


class DetailJadwalActivity : AppCompatActivity(), OnMapReadyCallback, JadwalView {


    private var mMap: GoogleMap? = null
    private var lat_req: Double? = null
    private var lng_req: Double? = null
    private var tempat: String? = null
    private lateinit var sharedPreference: SharedPref
    private var presenter: PresenterJadwal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(arm.app.jadwalkajian.R.layout.activity_detail_jadwal)

        lat_req = 0.0
        lng_req = 0.0

        sharedPreference = SharedPref(this)
        presenter = PresenterJadwal(this, this)

        val extras = intent.extras
        if (extras != null){
            dPemateri.text = extras.getString("pemateri")
            dHari.text = extras.getString("hari")
            dTanggal.text = extras.getString("mTanggal")
            dBulanTahun.text = extras.getString("mBulanTahun")
            dJamMulai.text = extras.getString("jam_mulai")
            dJamSelesai.text = extras.getString("jam_selesai")
            dTempat.text = extras.getString("tempat")
            dAlamat.text = extras.getString("alamat")
            lat_req = extras.getDouble("lang")
            lng_req = extras.getDouble("long")

            tempat = extras.getString("tempat")

            if(sharedPreference.getValueString("status") == "admin") {
                btnHapus.visibility = View.VISIBLE
                btnHapus.setOnClickListener {
                    pgbar_det_kajian.visibility = View.VISIBLE
                    presenter?.HapusJadwal(extras.getLong("id"))
                    finish()
                }
            }else{
                btnHapus.visibility = View.GONE
            }




        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

    override fun Hasil(status: Boolean, msg: String) {

        pgbar_det_kajian.visibility = View.GONE
        if(msg == "sukses"){
            Toast.makeText(this, "Berhasil Menghapus Data!", Toast.LENGTH_SHORT).show()
            finish()
        }  else{
            Toast.makeText(this, "Gagal Menghapus Data!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun DataJadwal(result: List<Jadwal>) {
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val masjid = LatLng(lat_req!!, lng_req!!)
        mMap?.addMarker(
            MarkerOptions().position(masjid).icon(
                BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_ORANGE
                )
            ).title("Lokasi Kajian").snippet(tempat)
        )

        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(masjid, 15f))
        mMap?.animateCamera(CameraUpdateFactory.zoomTo(10f), 1500, null)
        mMap?.uiSettings?.isZoomControlsEnabled = true
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(masjid))
        //        mMap.setMyLocationEnabled(true);
    }
}
