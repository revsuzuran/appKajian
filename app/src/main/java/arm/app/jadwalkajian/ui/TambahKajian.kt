package arm.app.jadwalkajian.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import arm.app.jadwalkajian.R
import arm.app.jadwalkajian.model.Jadwal
import arm.app.jadwalkajian.presenter.PresenterJadwal
import arm.app.jadwalkajian.view.JadwalView
import com.schibstedspain.leku.LATITUDE
import com.schibstedspain.leku.LONGITUDE
import com.schibstedspain.leku.LocationPicker
import com.schibstedspain.leku.LocationPickerActivity
import com.schibstedspain.leku.tracker.LocationPickerTracker
import com.schibstedspain.leku.tracker.TrackEvents
import kotlinx.android.synthetic.main.activity_tambah_kajian.*


private const val MAP_BUTTON_REQUEST_CODE = 1

class TambahKajian : AppCompatActivity(), JadwalView {


    private var presenter: PresenterJadwal? = null
    private var jadwal: Jadwal? = null
    private lateinit var adapterHari: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_kajian)

        initializeLocationPickerTracker()
        jadwal = Jadwal()
        presenter = PresenterJadwal(this, this)
        val harinya = resources.getStringArray(R.array.hari)

        adapterHari = ArrayAdapter(this, android.R.layout.simple_list_item_1, harinya)
        adapterHari.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spin_hari.setAdapter(adapterHari)

        spin_hari.setOnItemSelectedListener { _, _, _, item ->
            jadwal?.hari = item.toString()
        }                                                     

        btnAddMaps.setOnClickListener(View.OnClickListener {
            if (!checkGPSEnabled()) {
                return@OnClickListener
            }


            val locationPickerIntent = LocationPickerActivity.Builder()
//                .withLocation(41.4036299, 2.1743558)
                //.withGeolocApiKey("<PUT API KEY HERE>")
                //.withSearchZone("es_ES")
                //.withSearchZone(SearchZoneRect(LatLng(26.525467, -18.910366), LatLng(43.906271, 5.394197)))
                .withDefaultLocaleSearchZone()
                //.shouldReturnOkOnBackPressed()
                //.withStreetHidden()
                //.withCityHidden()
                //.withZipCodeHidden()
                //.withSatelliteViewHidden()
                //.withGooglePlacesEnabled()
                .withGoogleTimeZoneEnabled()
                //.withVoiceSearchHidden()
                .withUnnamedRoadHidden()
                .build(applicationContext)

            //this is optional if you want to return RESULT_OK if you don't set the latitude/longitude and click back button
            locationPickerIntent.putExtra("test", "this is a test")

            startActivityForResult(locationPickerIntent, MAP_BUTTON_REQUEST_CODE)

        })
        btnEditMaps.setOnClickListener(View.OnClickListener {
            if (!checkGPSEnabled()) {
                return@OnClickListener
            }


            val locationPickerIntent = LocationPickerActivity.Builder()
//                .withLocation(41.4036299, 2.1743558)
                //.withGeolocApiKey("<PUT API KEY HERE>")
                //.withSearchZone("es_ES")
                //.withSearchZone(SearchZoneRect(LatLng(26.525467, -18.910366), LatLng(43.906271, 5.394197)))
                .withDefaultLocaleSearchZone()
                //.shouldReturnOkOnBackPressed()
                //.withStreetHidden()
                //.withCityHidden()
                //.withZipCodeHidden()
                //.withSatelliteViewHidden()
                //.withGooglePlacesEnabled()
                .withGoogleTimeZoneEnabled()
                //.withVoiceSearchHidden()
                .withUnnamedRoadHidden()
                .build(applicationContext)

            //this is optional if you want to return RESULT_OK if you don't set the latitude/longitude and click back button
            locationPickerIntent.putExtra("test", "this is a test")

            startActivityForResult(locationPickerIntent, MAP_BUTTON_REQUEST_CODE)

        })

        btnSimpan.setOnClickListener {

            pgbar_input_kajian.visibility = View.VISIBLE

            jadwal?.alamat = input_alamat.text.toString()
            jadwal?.jamMulai = input_waktu_mulai.text.toString()
            jadwal?.jamSelesai = input_waktu_selesai.text.toString()
            jadwal?.pemateri = input_pemateri.text.toString()
            jadwal?.tanggal = input_tanggal.text.toString()
            jadwal?.tempat = input_lokasi.text.toString()

            Log.d("aqil", "${jadwal?.hari}")
            presenter?.InsertJadwal(jadwal)

        }

    }

    override fun Hasil(status: Boolean, msg: String) {

        pgbar_input_kajian.visibility = View.GONE
        if(msg == "sukses"){
            Toast.makeText(this, "Berhasil Menyimpan Data!", Toast.LENGTH_SHORT).show()
            finish()
        }  else{
           Toast.makeText(this, "Gagal Menyimpan Data!", Toast.LENGTH_SHORT).show()
        }


    }

    override fun DataJadwal(result: List<Jadwal>) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {

            if (requestCode == 1) {
                val latitude = data.getDoubleExtra(LATITUDE, 0.0)
                val longitude = data.getDoubleExtra(LONGITUDE, 0.0)
//                Toast.makeText(this, latitude.toString()+longitude.toString(), Toast.LENGTH_SHORT).show()
                jadwal?.lang = latitude.toString()
                jadwal?.long = longitude.toString()
                btnAddMaps.visibility = View.GONE
                btnEditMaps.visibility = View.VISIBLE
                alamatPeta.visibility = View.VISIBLE
            }
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            Log.d("RESULT****", "CANCELLED")
        }
    }

    private fun initializeLocationPickerTracker() {
        LocationPicker.setTracker(MyPickerTracker(this))
    }

    private class MyPickerTracker(private val context: Context) : LocationPickerTracker {
        override fun onEventTracked(event: TrackEvents) {
//            Toast.makeText(context, "Event: " + event.eventName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun checkGPSEnabled(): Boolean {
        if (!isLocationEnabled())
            showAlert()
        return isLocationEnabled()
    }

    private fun showAlert() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Enable Location")
            .setMessage("Locations Settings is set to 'Off'.\nEnable Location to use this app")
            .setPositiveButton("Location Settings") { _, _ ->
                val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(myIntent)
            }
            .setNegativeButton("Cancel") { _, _ -> }
        dialog.show()
    }

}
