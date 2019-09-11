package arm.app.jadwalkajian.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import arm.app.jadwalkajian.R
import arm.app.jadwalkajian.model.Admin
import arm.app.jadwalkajian.presenter.PresenterAdmin
import arm.app.jadwalkajian.utils.SharedPref
import arm.app.jadwalkajian.view.AdminView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AdminView {


    private var presenter: PresenterAdmin? = null
    private lateinit var sharedPreferences: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = SharedPref(this)

        val admin = Admin()
        presenter = PresenterAdmin(this)

        btn_login.setOnClickListener {
            admin.username = userLogin.text.toString()
            admin.password = passLogin.text.toString()
            presenter?.Auth(admin)
            pgbarlogin.visibility = View.VISIBLE
        }


    }

    override fun Hasil(status: Boolean, msg: String) {

        pgbarlogin.visibility = View.GONE

        if(msg == "success"){
            sharedPreferences.save("status", "admin")
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("status", "admin")
            startActivity(intent)
            finish()
            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Login Gagal!", Toast.LENGTH_SHORT).show()
        }
    }
}
