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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActvty : AppCompatActivity() {
    lateinit var usermanager : UserManager
    lateinit var listuserlogin : List<GetDataUserItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_actvty)
        usermanager = UserManager(this)
        btnLogin.setOnClickListener {
                Login()
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
        usermanager = UserManager(this)
        val nama = nama.text.toString()
        val  password = pass.text.toString()
        for(i in listlogin.indices){
            if (nama == listlogin[i].username && password == listlogin[i].password) {
               GlobalScope.launch {
                   usermanager.setBoolean(true)
                   usermanager.saveData(
                   listlogin[i].name,
                   listlogin[i].id,
                   listlogin[i].password,
                   listlogin[i].image,
                   listlogin[i].umur.toString(),
                   listlogin[i].username,
                   listlogin[i].address
                   )
               }
                Toast.makeText(this, "Selamat Datang Di indomaret", Toast.LENGTH_SHORT).show()
            }
        }
    }
}