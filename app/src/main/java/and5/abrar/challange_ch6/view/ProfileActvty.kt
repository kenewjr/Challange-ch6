package and5.abrar.challange_ch6.view

import and5.abrar.challange_ch6.R
import and5.abrar.challange_ch6.datastore.UserManager
import and5.abrar.challange_ch6.model.GetDataUserItem
import and5.abrar.challange_ch6.viewmodel.ViewModelProfile
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_profile_actvty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileActvty : AppCompatActivity() {
    lateinit var listuser : List<GetDataUserItem>
    lateinit var usermanager : UserManager
    private lateinit var sharedPreference : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_actvty)
        usermanager = UserManager(this)
        sharedPreference = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
//        getDataProfile()
        btnLogout.setOnClickListener {
            GlobalScope.launch {
                usermanager.hapusData()
            }
            startActivity(Intent(this, LoginActvty::class.java))
        }
        }
    }
//    fun getDataProfile(){
//        val id = sharedPreference.getString("id","")
//        val viewModel = ViewModelProvider(this).get(ViewModelProfile::class.java)
//        viewModel.DetailUserAPI(id!!.toInt())
//        viewModel.getLiveProfile().observe(this, Observer {
//            if (it != null){
//                listuser = it
//                initData(listuser)
//                Toast.makeText(this, it.toString(),Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(this, "failed" ,Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//    fun initData(userdatalist : List<GetDataUserItem>){
//
//        for (i in userdatalist.indices){
//            upUser.setText(userdatalist[i].username)
//            upNama.setText(userdatalist[i].name)
//            upAdress.setText(userdatalist[i].address)
//            upLahir.setText(userdatalist[i].umur)
//        }
//    }
//    fun logout(){
//        AlertDialog.Builder(this)
//            .setTitle("Keluar Aplikasi")
//            .setMessage("Yakin keluar dari aplikasi?")
//            .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
//                sharedPreference = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
//                val SF = sharedPreference.edit()
//                SF.clear()
//                SF.apply()
//                startActivity(Intent(this, LoginActvty::class.java))
//                finish()
//            }
//            .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
//                dialogInterface.dismiss()
//            }
//            .show()
//    }
//}