package br.com.cleanarquictecture.ui.character

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cleanarquictecture.R
import br.com.cleanarquictecture.databinding.FragmentCharactersAllBinding
import br.com.cleanarquictecture.ui.UiState
import br.com.core.Resource
import br.com.domain.model.Character
import br.com.domain.model.CharacterAll
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersAllFragment : Fragment() {

    private val viewModel: CharactersViewModel by viewModels()
    private var adapterCharactersAll : AdapterCharactersAll = AdapterCharactersAll()
    private lateinit var binding: FragmentCharactersAllBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersAllBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setupObserver()
        getCharactersAll()
    }

    private fun setUpUI() {
        val recyclerview = binding.recyclerviewCharactersAll
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.addItemDecoration(
            DividerItemDecoration(
                recyclerview.context,
                (recyclerview.layoutManager as LinearLayoutManager).orientation
            )
        )

        recyclerview.adapter = adapterCharactersAll
    }

    private fun setUpUILoading() {
        binding.progressBar.visibility = View.VISIBLE

    }

    private fun setUpUISuccess(characterAll: CharacterAll) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerviewCharactersAll.visibility = View.VISIBLE
        adapterCharactersAll.addCharacter(characterAll.results)
        adapterCharactersAll.notifyDataSetChanged()
    }

    private fun setUpUIError(message: String?) {
        binding.progressBar.visibility = View.GONE
        binding.txtError.visibility = View.VISIBLE
        binding.txtErrorMessage.visibility = View.VISIBLE
        binding.txtErrorMessage.text = message
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect{ state ->
                    when(state) {
                        is UiState.Loading -> setUpUILoading()
                        is UiState.Success -> setUpUISuccess(state.data!!)
                        is UiState.Error -> setUpUIError(state.message)
                    }
                }
            }
        }
    }

    private fun getCharactersAll() {
        viewModel.getCharactersAll()
    }

}