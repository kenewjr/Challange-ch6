package and5.abrar.challange_ch6.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Favorite(
    @PrimaryKey(autoGenerate = true)var id :Int?,
    @ColumnInfo(name = "email") var email:String,
    @ColumnInfo(name = "idMovie") var idMovie:String,
)
