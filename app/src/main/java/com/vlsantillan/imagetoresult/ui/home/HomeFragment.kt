package com.vlsantillan.imagetoresult.ui.home

import android.Manifest
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vlsantillan.imagetoresult.R
import com.vlsantillan.imagetoresult.databinding.FragmentHomeBinding
import com.vlsantillan.imagetoresult.ui.camera.CameraViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val cameraViewModel: CameraViewModel by activityViewModels()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Array<String>>

    private val bitmap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            var allAreGranted = true
            for (b in result.values) {
                allAreGranted = allAreGranted && b
            }

            if (allAreGranted) {
                findNavController().navigate(R.id.camera_fragment)
                Toast.makeText(view.context, "Open camera", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonHomeAddResult.setOnClickListener {
            val appPerms = arrayOf(
                Manifest.permission.CAMERA
            )
            activityResultLauncher.launch(appPerms)
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            lifecycleScope.launch {
                cameraViewModel.currentSource.collect {
                    val source = bitmap ?: it
                    if (source != null) {
                        binding.imageViewHome.setImageBitmap(source)
                    } else {
                        binding.groupHomeEquation.isVisible = false
                        binding.textViewHomeInstruction.isVisible = true
                    }
                }
            }
            lifecycleScope.launch {
                cameraViewModel.calculationResult.collect {
                    if (it?.result != null) {
                        binding.groupHomeEquation.isVisible = true
                        binding.textViewHomeInstruction.isVisible = false
                        binding.textViewHomeInput.text = "Input: ${it.input1} ${it.operation} ${it.input2}"
                        binding.textViewHomeResult.text = "Result: ${it.result}"
                    } else {
                        binding.groupHomeEquation.isVisible = false
                        binding.textViewHomeInstruction.isVisible = true
                    }
                }
            }
        }
    }
}