package and5.abrar.challange_ch6.view

import and5.abrar.challange_ch6.R
import and5.abrar.challange_ch6.datastore.UserManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register_actvty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActvty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var  usermanager : UserManager
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_actvty)
        usermanager = UserManager(this)
        btnregis.setOnClickListener {
            val nama = et_nama.text.toString()
            val pass = et_pass.text.toString()
            GlobalScope.launch {
                usermanager.saveData(nama, pass)
            }
            startActivity(Intent(this, LoginActvty::class.java))

        }
    }
}