package and5.abrar.challange_ch6.view

import and5.abrar.challange_ch6.R
import and5.abrar.challange_ch6.model.GetDataFilmItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_film.view.*

class AdapterFilm(private var onClik :(GetDataFilmItem)->Unit):RecyclerView.Adapter<AdapterFilm.ViewHolder>() {
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)
    private var dataFilm : List<GetDataFilmItem>? = null
    fun setFilm(film: List<GetDataFilmItem>){
        this.dataFilm = film
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film,parent, false)
        return ViewHolder(viewitem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.cardFilm.setOnClickListener {
            onClik(dataFilm!![position])
        }
        holder.itemView.namaFilm.text = dataFilm!![position].name
        Glide.with(holder.itemView.context)
            .load(dataFilm!![position].image)
            .into(holder.itemView.gambarFilm)
        holder.itemView.namaFilm.text = "waktu : " + dataFilm!![position].name
        holder.itemView.Director.text = "populasi : " + dataFilm!![position].director
    }

    override fun getItemCount(): Int {
        if (dataFilm == null){
            return  0
        }else{
            return  dataFilm!!.size
        }
    }
}