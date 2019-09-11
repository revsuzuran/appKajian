package arm.app.jadwalkajian.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import arm.app.jadwalkajian.R
import arm.app.jadwalkajian.init.InitRetrofit
import arm.app.jadwalkajian.model.Post
import arm.app.jadwalkajian.response.ResponseInsert
import arm.app.jadwalkajian.utils.SharedPref
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Response

class PostAdapter(context: Context?, data: List<Post>?) :
    RecyclerView.Adapter<PostAdapter.MyHolder>() {

    var mContext : Context? = context
    var post : List<Post>? = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.MyHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_post, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return post?.size!!
    }

    override fun onBindViewHolder(holder: PostAdapter.MyHolder, position: Int) {

        val sharedPreference = SharedPref(mContext!!)
        if(sharedPreference.getValueString("status") == "admin"){
            holder.nlayout?.setOnClickListener {
                val builder = AlertDialog.Builder(mContext!!)
                builder.setTitle("Hapus Post")
                builder.setMessage("Anda Yakin Ingin Menghapus Post Ini ?")
                builder.setPositiveButton("Ya"){ dialog, _ ->
                    HapusData(post?.get(position)?.id!!,position)
                    dialog.dismiss()
                }
                builder.setNegativeButton("Tidak"){dialog, which ->
                    dialog.dismiss()
                }
                builder.setCancelable(true)
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
        post?.get(position)?.gambar
        val url = "http://kajian.arm.click/resources/back/img/"
        holder.nText?.text = post?.get(position)?.text
        val req = RequestOptions()
            .placeholder(R.drawable.ic_photo_black_24dp)
            .error(R.drawable.ic_photo_black_24dp)
        Glide.with(mContext!!).load(url+post?.get(position)?.gambar).apply(req).into(holder.nGambar!!)


    }

    class MyHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var nGambar : ImageView? = itemView?.findViewById(R.id.imgPost)
        var nText : TextView? = itemView?.findViewById(R.id.captionPost)
        var nlayout : CardView? = itemView?.findViewById(R.id.item_layout_post)
    }

    private fun HapusData(id:Long, position: Int) {

        val getInit = InitRetrofit().getInitInstance()
        val request = getInit.request_deletePost(id)
        request.enqueue(object : retrofit2.Callback<ResponseInsert> {
            override fun onFailure(call: Call<ResponseInsert>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<ResponseInsert>?, response: Response<ResponseInsert>?) {
                if (response != null){
                    notifyItemRemoved(position)
                    notifyDataSetChanged()
                    Toast.makeText(mContext, "Data Berhasil di hapus!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(mContext, "Gagal Menghapus Data!", Toast.LENGTH_SHORT).show()
                }
            }

        })

    }


}