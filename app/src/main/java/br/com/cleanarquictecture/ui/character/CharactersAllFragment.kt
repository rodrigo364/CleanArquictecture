package br.com.cleanarquictecture.ui.character

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.cleanarquictecture.R
import br.com.cleanarquictecture.ui.UiState
import br.com.core.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharactersAll()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect{
                    when(it) {
                        is UiState.Loading -> {
                            Log.d("SUC"," it.data?.info?.next!!")
                        }
                        is UiState.Success -> {
                            Log.d("SUC", it.data?.info?.next!!)
                        }
                        is  UiState.Error -> {
                            Log.d("TESTE", it.message!!)
                        }
                    }
                }
            }
        }




    }

}