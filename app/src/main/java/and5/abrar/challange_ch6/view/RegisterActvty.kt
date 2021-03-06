package and5.abrar.challange_ch6.view

import and5.abrar.challange_ch6.R
import and5.abrar.challange_ch6.datastore.UserManager
import and5.abrar.challange_ch6.viewmodel.ViewModelUser
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_register_actvty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RegisterActvty : AppCompatActivity() {
    lateinit var viewModelUserApi : ViewModelUser
    lateinit var  usermanager : UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_actvty)
        usermanager = UserManager(this)
        btnregis.setOnClickListener {
            val nama = et_nama.text.toString()
            val pass = et_pass.text.toString()
            val user = et_username.text.toString()
            val alamat = et_address.text.toString()
            val umur = et_umur.text.toString()
            val image =  "http://loremflickr.com/640/480"
            GlobalScope.launch {
                tambahUser(alamat,image,umur.toInt(),user,pass,nama)
            }
            startActivity(Intent(this, LoginActvty::class.java))

        }
    }
    private fun tambahUser(
        alamat: String,
        image: String,
        umur: Int,
        username: String,
        password: String,
        name: String
    ) {
        viewModelUserApi = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModelUserApi.addNewUserApi(alamat,image,umur,password,name,username)
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(this@RegisterActvty, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
        }
    }
}