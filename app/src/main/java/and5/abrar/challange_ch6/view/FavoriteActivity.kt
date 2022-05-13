package and5.abrar.challange_ch6.view

import and5.abrar.challange_ch6.R
import and5.abrar.challange_ch6.room.FavoriteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {
    var filmDb:FavoriteDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        getFilmFav()
        filmDb = FavoriteDatabase.getinstance(this)
    }
    fun getFilmFav(){
        rv_favfilm.layoutManager = LinearLayoutManager(this)
        GlobalScope.launch {
            val listFavFilm = filmDb?.favoriteduo()?.getFavorite()
            runOnUiThread{
                if (listFavFilm?.size != null) {
                    if(listFavFilm.isEmpty()){
                        tv.text = "data kosong"
                    }else{
                        listFavFilm.let {
                            rv_favfilm.adapter = AdapterFilmFavourite(it)
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getFilmFav()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}