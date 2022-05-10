package and5.abrar.challange_ch6.api

import and5.abrar.challange_ch6.model.GetDataFilmItem
import and5.abrar.challange_ch6.model.GetDataUserItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("film")
    fun getAllfilm(): Call<List<GetDataFilmItem>>


    @GET("user")
    fun allUser(): Call<List<GetDataUserItem>>
    @POST("user")
    fun updateUser(
        @Field("id")id : String,
        @Field("name")name : String,
        @Field("username")username : String,
        @Field("address")adress : String,
        @Field("umur")umur : String
    ): Call<GetDataUserItem>
    @POST("user")
    fun detailUser(@Field("id") id : Int) : Call<List<GetDataUserItem>>
}