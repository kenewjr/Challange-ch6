package and5.abrar.challange_ch6.viewmodel

import and5.abrar.challange_ch6.api.ApiClient
import and5.abrar.challange_ch6.model.GetDataUserItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelProfile:ViewModel() {
    lateinit var liveDataDetailUser: MutableLiveData<List<GetDataUserItem>>

    init {
        liveDataDetailUser = MutableLiveData()
    }

    fun getLiveProfile(): MutableLiveData<List<GetDataUserItem>> {
        return liveDataDetailUser
    }

    fun DetailUserAPI(id : Int){
        ApiClient.instance.detailUser(id)
            .enqueue(object : Callback<List<GetDataUserItem>> {
                override fun onResponse(call: Call<List<GetDataUserItem>>, response: Response<List<GetDataUserItem>>) {
                    if (response.isSuccessful){
                        liveDataDetailUser.postValue(response.body())
                    }else{
                        liveDataDetailUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetDataUserItem>>, t: Throwable) {
                    liveDataDetailUser.postValue(null)
                }

            })
    }

}