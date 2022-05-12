package and5.abrar.challange_ch6.view

import and5.abrar.challange_ch6.R
import and5.abrar.challange_ch6.datastore.UserManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.asLiveData

class SplashScreen : AppCompatActivity() {
    lateinit var userManager: UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        userManager = UserManager(this)
        Handler(Looper.getMainLooper()).postDelayed({
            userManager.ceklogin.asLiveData().observe(this){
                if(it == true){
                    startActivity(Intent(this, FilmActvty::class.java))
                }else{
                    startActivity(Intent(this, LoginActvty::class.java))
                }
            }
            },3000)


    }
}