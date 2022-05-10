package and5.abrar.challange_ch6.viewmodel

import and5.abrar.challange_ch6.api.ApiClient
import and5.abrar.challange_ch6.model.GetDataFilmItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm:ViewModel() {
    lateinit var liveDataFilm: MutableLiveData<List<GetDataFilmItem>>

    init {
        liveDataFilm = MutableLiveData()
    }

    fun getLivedataFilm (): MutableLiveData<List<GetDataFilmItem>> {
        return  liveDataFilm
    }

    fun getApiFilm(){
        ApiClient.instance.getAllfilm()
            .enqueue(object : Callback<List<GetDataFilmItem>> {
                override fun onResponse(
                    call: Call<List<GetDataFilmItem>>,
                    response: Response<List<GetDataFilmItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataFilm.postValue(response.body())
                    }else{
                        liveDataFilm.postValue(null)
                    }
                }
                override fun onFailure(call: Call<List<GetDataFilmItem>>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }

            })
    }
}