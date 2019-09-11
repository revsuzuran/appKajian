package arm.app.jadwalkajian.ui

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import arm.app.jadwalkajian.R
import arm.app.jadwalkajian.utils.SharedPref
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var content: FrameLayout? = null
    private lateinit var sharedPreference: SharedPref

    companion object {
        var status: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)

        sharedPreference = SharedPref(this)
        status = intent.getStringExtra("status")
        if(sharedPreference.getValueString("status") == "admin"){
            status = "admin"
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = JadwalFragment.newInstance()
        addFragment(fragment)
        aboutMain.setOnClickListener {
            startActivity(Intent(this, TentangActivity::class.java))
            finish()
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_satu -> {

                val fragment = JadwalFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dua -> {
                val fragment = PostFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tiga -> {
                val fragment = NotifFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }


}
