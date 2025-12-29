package com.example.shop.UI.Fragment.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.UI.Adapter.ProductAdapter
import com.example.shop.UI.Fragment.product.ListViewModel.ListViewModel
import com.example.shop.UI.Fragment.product.models.UIState
import com.example.shop.databinding.FragmentListBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentProductList : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListViewModel by viewModel()
    private val adapter by lazy {
        ProductAdapter { product ->
            product.id?.let {
                val action =
                    FragmentProductListDirections.actionFragmentProductListToFragmentProductDetail(
                        it
                    )
                findNavController().navigate(action)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        observeState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler(){
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeState(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is UIState.loading -> {
                            binding.progressBar.isVisible = true
                            binding.recyclerview.isVisible = false
                        }

                        is UIState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerview.isVisible = true
                            adapter.submitList(state.data)
                        }

                        is UIState.Error -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerview.isVisible = false
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}
