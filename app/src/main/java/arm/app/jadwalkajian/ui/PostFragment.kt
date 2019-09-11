package arm.app.jadwalkajian.ui


import android.content.ContentValues.TAG
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
import arm.app.jadwalkajian.adapter.PostAdapter
import arm.app.jadwalkajian.model.Post
import arm.app.jadwalkajian.presenter.PresenterPost
import arm.app.jadwalkajian.view.PostView
import kotlinx.android.synthetic.main.fragment_post.*


class PostFragment : Fragment(), PostView {


    private var presenter: PresenterPost? = null
    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        btn_add_post.setOnClickListener{
            startActivity(Intent(context, TambahPost::class.java))
        }

    }

    private fun initView(){

        recyclerView = view?.findViewById(R.id.rv_post)
        progressBar = view?.findViewById(R.id.pgbar_fragment_post)
        presenter = PresenterPost(this)
        presenter?.Tampil()
        progressBar?.visibility = View.VISIBLE

        if(MainActivity.status == "admin") {
            btn_add_post.visibility = View.VISIBLE
        }else{
//            btn_add_post.visibility = View.GONE
        }

    }

    override fun Hasil(status: Boolean, msg: String) {
    }

    override fun DataPost(result: List<Post>) {

        Log.d(TAG, "$result")
        if(result.isEmpty()){
            tidakadapost?.visibility = View.VISIBLE
        }else{
            tidakadapost?.visibility = View.GONE
        }
        val adapter = PostAdapter(context, result)
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
