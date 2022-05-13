package and5.abrar.challange_ch6.view

import and5.abrar.challange_ch6.R
import and5.abrar.challange_ch6.datastore.UserManager
import and5.abrar.challange_ch6.viewmodel.ViewModelUser
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile_actvty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileActvty : AppCompatActivity() {
    lateinit var viewModelUserApi : ViewModelUser
    lateinit var usermanager : UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_actvty)
        usermanager = UserManager(this)
        getDataProfile()
        btnUpdate.setOnClickListener {
            updateData()
        }
        btnLogout.setOnClickListener {
            GlobalScope.launch {
                usermanager.hapusData()
            }
            startActivity(Intent(this, LoginActvty::class.java))
        }
        }
    fun getDataProfile(){
        usermanager = UserManager(this)
        usermanager.Image.asLiveData().observe(this){
            Glide.with(this.profileImage.context).load(it).into(this.profileImage)
        }
       usermanager.Address.asLiveData().observe(this){
           up_address.setText(it)
       }
        usermanager.Nama.asLiveData().observe(this){
            up_nama.setText(it)
        }
        usermanager.Umur.asLiveData().observe(this){
            up_umur.setText(it)
        }
        usermanager.userName.asLiveData().observe(this){
            up_username.setText(it)
        }
        usermanager.Pass.asLiveData().observe(this){
            up_pass.setText(it)
        }

    }
    private fun updateData() {
        usermanager = UserManager(this)

        var id = ""
        val nama = up_nama.text.toString()
        val pass = up_pass.text.toString()
        val user = up_username.text.toString()
        val alamat = up_address.text.toString()
        val umur = up_umur.text.toString()
        val image =  "http://loremflickr.com/640/480"

        usermanager.Id.asLiveData().observe(this){
            id = it.toString()
        }
        AlertDialog.Builder(this)
            .setTitle("Update data")
            .setMessage("Yakin ingin mengupdate data?")
            .setNegativeButton("TIDAK"){ dialogInterface : DialogInterface, i : Int ->
                dialogInterface.dismiss()
            }
            .setPositiveButton("YA"){ dialogInterface : DialogInterface, i : Int ->
                viewModelUserApi = ViewModelProvider(this).get(ViewModelUser::class.java)
                viewModelUserApi.updateUserAPI(id.toInt(),nama,pass,user,alamat,umur,image)
                Toast.makeText(this, "Update data berhasil", Toast.LENGTH_SHORT).show()
                GlobalScope.launch {
                    usermanager.saveData(
                       nama,
                        id,
                        pass,
                        image,
                        umur,
                        user,
                        alamat
                    )
                }
                startActivity(Intent(this,FilmActvty::class.java))
            }.show()
    }
    }