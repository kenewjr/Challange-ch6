package and5.abrar.challange_ch6.viewmodel


import and5.abrar.challange_ch6.api.ApiClient
import and5.abrar.challange_ch6.model.GetDataUserItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser: ViewModel() {
    lateinit var liveDataLogin : MutableLiveData<List<GetDataUserItem>>
    lateinit var liveDataUpdate : MutableLiveData<GetDataUserItem>

    init {
        liveDataLogin = MutableLiveData()
        liveDataUpdate = MutableLiveData()

    }

    fun getLiveLogin(): MutableLiveData<List<GetDataUserItem>>{
        return liveDataLogin
    }
    fun getLiveUpdate() : MutableLiveData<GetDataUserItem>{
        return liveDataUpdate
    }


    fun loginUserAPI(){
        ApiClient.instance.allUser()
            .enqueue(object : Callback<List<GetDataUserItem>>{
                override fun onResponse(call: Call<List<GetDataUserItem>>, response: Response<List<GetDataUserItem>>) {
                    if (response.isSuccessful){
                        liveDataLogin.postValue(response.body())
                    }else{
                        liveDataLogin.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetDataUserItem>>, t: Throwable) {
                    liveDataLogin.postValue(null)
                }

            })
    }

    fun updateUserAPI(id :Int, name : String, username: String, address: String, umur : String){
        ApiClient.instance.updateUser(id.toString(), name, username, address, umur)
            .enqueue(object : Callback<GetDataUserItem>{
                override fun onResponse(call: Call<GetDataUserItem>, response: Response<GetDataUserItem>) {
                    if (response.isSuccessful){
                        liveDataUpdate.postValue(response.body())
                    }else{
                        liveDataUpdate.postValue(null)
                    }
                }

                override fun onFailure(call: Call<GetDataUserItem>, t: Throwable) {
                    liveDataUpdate.postValue(null)
                }

            })
    }
}