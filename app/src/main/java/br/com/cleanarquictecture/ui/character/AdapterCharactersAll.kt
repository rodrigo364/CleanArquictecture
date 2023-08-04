package br.com.cleanarquictecture.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cleanarquictecture.databinding.ItemCharacterBinding
import br.com.domain.model.Character

class AdapterCharactersAll(private val characterAll: ArrayList<Character> = arrayListOf())
    : RecyclerView.Adapter<AdapterCharactersAll.ViewHolderCharactersAll>() {
    class ViewHolderCharactersAll( private val binding: ItemCharacterBinding)
        : RecyclerView.ViewHolder(binding.root)  {

        fun bind(character: Character) {

            binding.txtName.text = character.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharactersAll {
       return ViewHolderCharactersAll(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )
    }

    override fun getItemCount(): Int = characterAll.size

    override fun onBindViewHolder(holder: ViewHolderCharactersAll, position: Int) {
       holder.bind(characterAll[position])
    }

    fun addCharacter(charactersAll: List<Character>){
        this.characterAll.addAll(charactersAll)
    }
}