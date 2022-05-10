package and5.abrar.challange_ch6.view

import and5.abrar.challange_ch6.R
import and5.abrar.challange_ch6.datastore.UserManager
import and5.abrar.challange_ch6.model.GetDataUserItem
import and5.abrar.challange_ch6.viewmodel.ViewModelUser
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_login_actvty.*

class LoginActvty : AppCompatActivity() {
    lateinit var sf : SharedPreferences
    lateinit var usermanager : UserManager
    lateinit var listuserlogin : List<GetDataUserItem>
    lateinit var namauser : String
    lateinit var passuser : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_actvty)
        usermanager = UserManager(this)

        val dataUser = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
        sf = this.getSharedPreferences("datauser",Context.MODE_PRIVATE)

        usermanager.userNama.asLiveData().observe(this) {
            namauser = it.toString()
        }
        usermanager.userPass.asLiveData().observe(this) {
            passuser = it.toString()
        }
        btnLogin.setOnClickListener {
            val inNama = nama.text.toString()
            val inPass = pass.text.toString()
            if (inNama == namauser && passuser == inPass) {
                Login()
                Toast.makeText(this, "ini benar", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "ini salah", Toast.LENGTH_SHORT).show()
            }
        }
        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActvty::class.java))
        }

    }
    fun Login(){
        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.loginUserAPI()
        viewModel.getLiveLogin().observe(this, Observer {
            if (it != null){
                listuserlogin = it
                loginAuth(listuserlogin)
                startActivity(Intent(this, FilmActvty::class.java))
            }else{
                Toast.makeText(this, "gagal Login", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun loginAuth(listlogin : List<GetDataUserItem>){
        val nama = nama.text.toString()
        val  password = pass.text.toString()
        for(i in listlogin.indices){
            if (nama == listlogin[i].username && password == listlogin[i].password) {
                sf = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
                val sfe = sf.edit()
                sfe.putString("username", listlogin[i].username).apply()
                sfe.putString("id", listlogin[i].id).apply()
                Toast.makeText(this, "Selamat Datang Di indomaret", Toast.LENGTH_SHORT).show()
            }
        }
    }
}