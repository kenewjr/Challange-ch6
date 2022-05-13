package and5.abrar.challange_ch6.api

import and5.abrar.challange_ch6.model.GetDataFilmItem
import and5.abrar.challange_ch6.model.GetDataUserItem
import and5.abrar.challange_ch6.model.PostNewUser
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("film")
    fun getAllfilm(): Call<List<GetDataFilmItem>>


    @GET("user")
    fun allUser(): Call<List<GetDataUserItem>>
    @PUT("user/{id}")
    @FormUrlEncoded
    fun updateUser(
        @Path("id")id : String,
        @Field("name")name : String,
        @Field("pass")pass :String,
        @Field("username")username : String,
        @Field("address")adress : String,
        @Field("umur")umur : String,
        @Field("image")image : String
    ): Call<PostNewUser>
    @POST("user")
    fun detailUser(@Field("id") id : Int) : Call<List<GetDataUserItem>>
    @POST("user")
    fun postDataUser(@Body reqUser: PostNewUser) : Call<GetDataUserItem>
}