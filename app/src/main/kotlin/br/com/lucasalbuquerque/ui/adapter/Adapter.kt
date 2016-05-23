package br.com.lucasalbuquerque.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.com.lucasalbuquerque.domain.MarvelCharacter
import br.com.lucasalbuquerque.ui.layout.MainActivityUI
import java.util.*

class Adapter(val context: Context, val charactersList: ArrayList<MarvelCharacter> = ArrayList<MarvelCharacter>()) : RecyclerView.Adapter<Adapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder? {
        return Holder(MainActivityUI.CharacterListItem(context))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(charactersList.get(position))
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    fun push(character: MarvelCharacter) {
        charactersList.add(0, character)
        notifyItemInserted(0)
    }

    fun pop() {
        charactersList.remove(charactersList.last())
        notifyItemRemoved(charactersList.size)
    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(character: MarvelCharacter) {
            (itemView as MainActivityUI.CharacterListItem).bind(character)
        }
    }
}