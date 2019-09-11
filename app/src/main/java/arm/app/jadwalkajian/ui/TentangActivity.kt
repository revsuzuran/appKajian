package arm.app.jadwalkajian.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import arm.app.jadwalkajian.R
import arm.app.jadwalkajian.utils.SharedPref
import kotlinx.android.synthetic.main.activity_tentang.*

class TentangActivity : AppCompatActivity() {

    private lateinit var sharedPreference: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tentang)

        sharedPreference = SharedPref(this)

        if(sharedPreference.getValueString("status") == "admin"){
            loginAdmin.visibility = View.GONE
            logoutAdmin.visibility = View.VISIBLE
        }else{
            loginAdmin.visibility = View.VISIBLE
            logoutAdmin.visibility = View.GONE
        }

        loginAdmin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        logoutAdmin.setOnClickListener {
            sharedPreference.save("status","user")
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }
}
