package br.com.cleanarquictecture.ui.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.cleanarquictecture.R
import br.com.core.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersAllFragment : Fragment() {

    private val viewModel: CharactersViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharactersAll()
        viewModel.characterData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                Log.d("RESOURCE","LOADING..")
                }

                is Resource.Success -> {
                    Log.d("RESOURCE","SUCCESS.."+ result.data!!.info.count)

                }

                is Resource.Failure -> {
                    Log.d("RESOURCE","FAILURE.."+ result.message)

                }
            }

        }
    }

}