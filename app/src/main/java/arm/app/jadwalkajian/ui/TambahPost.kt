package arm.app.jadwalkajian.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import arm.app.jadwalkajian.R
import arm.app.jadwalkajian.model.Post
import arm.app.jadwalkajian.presenter.PresenterPost
import arm.app.jadwalkajian.view.PostView
import com.rilixtech.materialfancybutton.MaterialFancyButton
import kotlinx.android.synthetic.main.activity_tambah_post.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

class TambahPost : AppCompatActivity(), PostView {
    override fun Hasil(status: Boolean, msg: String) {
        pgbar_input_post.visibility = View.GONE
        if(msg == "sukses"){
            Toast.makeText(this, "Berhasil Menyimpan Data!", Toast.LENGTH_SHORT).show()
            finish()
        }  else{
            Toast.makeText(this, "Gagal Menyimpan Data!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun DataPost(result: List<Post>) {
    }

    val IMG_REQ = 777
    var bitmap: Bitmap? = null
    private var presenter: PresenterPost? = null
    private var post: Post? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_post)

        post = Post()

        presenter = PresenterPost(this)

        val pilihGambar = findViewById<TextView>(R.id.pilihTambahGambar)

        pilihGambar.setOnClickListener {
            selectImage()
        }

        btn_kirimtambahpost.setOnClickListener {
            pgbar_input_post.visibility = View.VISIBLE
            post?.text = postText.text.toString()
            post?.tanggal = Date().toString()
            presenter?.InsertPost(post?.gambar.toString(), postText.text.toString(), Date().toString())

        }

    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, IMG_REQ)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMG_REQ && resultCode == Activity.RESULT_OK && data != null) {
            val path = data.data
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, path)
                imgView.setImageBitmap(bitmap)
                imgView.visibility = View.VISIBLE
                post?.gambar = imageToString()
                pilihTambahGambar.visibility = View.GONE


            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    private fun imageToString(): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream)
        val imagByte = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(imagByte, Base64.NO_WRAP)
    }


}
