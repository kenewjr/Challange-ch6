package and5.abrar.challange_ch6.view

import and5.abrar.challange_ch6.R
import and5.abrar.challange_ch6.datastore.UserManager
import and5.abrar.challange_ch6.viewmodel.ViewModelUser
import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile_actvty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ProfileActvty : AppCompatActivity() {
    lateinit var viewModelUserApi : ViewModelUser
    lateinit var usermanager : UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_actvty)
        usermanager = UserManager(this)
        getDataProfile()
        profileImage.setOnClickListener {
        if(askForPermissions()){
            setImage()
        }
        }
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
//       profileImage.setImageBitmap(convertStringToBitmap(it))
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
        val image =  usermanager.Image.toString()

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

    private fun setImage() {
        val inten = Intent(Intent.ACTION_PICK)
        inten.type = "image/*"

        startActivityForResult(inten, 100)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode === RESULT_OK) {
            profileImage.setImageURI(data?.data)
            GlobalScope.launch {
                usermanager.setImage(data?.data.toString())
            }
        }
    }
    fun isPermissionsAllowed(): Boolean {
        return if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            false
        } else true
    }

    fun askForPermissions(): Boolean {
        if (!isPermissionsAllowed()) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
                showPermissionDeniedDialog()
            } else {
                ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),2000)
            }
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<String>,grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            2000 -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission is granted, you can perform your operation here
                } else {
                    // permission is denied, you can ask for permission again, if you want
                    //  askForPermissions()
                }
                return
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission Denied")
            .setMessage("Permission is denied, Please allow permissions from App Settings.")
            .setPositiveButton("App Settings",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    // send to app settings if permission is denied permanently
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package", "and5.abrar.challange_ch6", null)
                    intent.data = uri
                    startActivity(intent)
                })
            .setNegativeButton("Cancel",null)
            .show()
    }

}