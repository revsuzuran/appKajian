package arm.app.jadwalkajian.view

import arm.app.jadwalkajian.model.Post

interface PostView {
    fun Hasil(status: Boolean, msg : String)
    fun DataPost(result: List<Post>)
}