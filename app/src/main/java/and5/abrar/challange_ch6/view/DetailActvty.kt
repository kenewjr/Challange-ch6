package and5.abrar.challange_ch6.view

import and5.abrar.challange_ch6.R
import and5.abrar.challange_ch6.model.GetDataFilmItem
import and5.abrar.challange_ch6.room.Favorite
import and5.abrar.challange_ch6.room.FavoriteDatabase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_actvty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class DetailActvty : AppCompatActivity() {
    var filmDb: FavoriteDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_actvty)
        filmDb = FavoriteDatabase.getinstance(this)
        val detailfilm = intent.getParcelableExtra("detailfilm") as GetDataFilmItem?

        tvJudul.text = detailfilm?.name
        tvsutradara.text = detailfilm?.director
        tvtglFilm.text = detailfilm?.date
        tvdesc.text = detailfilm?.description

        Glide.with(this).load(detailfilm?.image).into(imgdetail)
        btn_fav.setOnClickListener {
        addfavfilm()
        }

    }
    fun addfavfilm(){

        val detailFilm = intent.getParcelableExtra<GetDataFilmItem>("detailfilm")

        GlobalScope.async {
            val director = detailFilm!!.director
            val releasedate = detailFilm!!.date
            val synopsis = detailFilm!!.description
            val title = detailFilm!!.name
            val image = detailFilm!!.image

            filmDb?.favoriteduo()?.addFavorite(Favorite(null, director, image, releasedate, synopsis, title) )

        }
        startActivity(Intent(this, FavoriteActivity::class.java))
    }
}