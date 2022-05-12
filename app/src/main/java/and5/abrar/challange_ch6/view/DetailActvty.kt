package and5.abrar.challange_ch6.view

import and5.abrar.challange_ch6.R
import and5.abrar.challange_ch6.model.GetDataFilmItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_actvty.*

class DetailActvty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_actvty)
        val detailfilm = intent.getParcelableExtra("detailfilm") as GetDataFilmItem?

        tvJudul.text = detailfilm?.name
        tvsutradara.text = detailfilm?.director
        tvtglFilm.text = detailfilm?.date
        tvdesc.text = detailfilm?.description

        Glide.with(this).load(detailfilm?.image).into(imgdetail)
        btn_fav.setOnClickListener {

        }
    }
}