package and5.abrar.challange_ch6.view

import and5.abrar.challange_ch6.R
import and5.abrar.challange_ch6.datastore.UserManager
import and5.abrar.challange_ch6.viewmodel.ViewModelFilm
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_film_actvty.*

class FilmActvty : AppCompatActivity() {
    lateinit var adapterFilm: AdapterFilm
    lateinit var userManager: UserManager
    lateinit var prefs : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_actvty)
        userManager = UserManager(this)
        prefs = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
        userManager.userName.asLiveData().observe(this) {
            welcome.text = "halo, $it"
        }
        adapterFilm = AdapterFilm(){
            val pindahdata = Intent(applicationContext, DetailActvty::class.java)
            pindahdata.putExtra("detailfilm", it)
            startActivity(pindahdata)
        }
        rvFilm.layoutManager=LinearLayoutManager(this)
        rvFilm.adapter=adapterFilm
        iniViewmodel()
        avatar.setOnClickListener {
            startActivity(Intent(this, ProfileActvty::class.java))
        }
    }
    fun iniViewmodel(){
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.getLivedataFilm().observe(this, Observer {
            if (it != null){
                adapterFilm.setFilm(it)
                adapterFilm.notifyDataSetChanged()
            }
        })
        viewModel.getApiFilm()
    }
}