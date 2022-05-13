package and5.abrar.challange_ch6.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDuo {
    @Insert
    fun addFavorite(favorite : Favorite) : Long

    @Query("SELECT * FROM Favorite")
    fun getFavorite(): List<Favorite>

    @Delete
    fun deletefavorite(favorite: Favorite):Int
}