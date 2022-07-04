package com.vlsantillan.imagetoresult.ui.home

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vlsantillan.imagetoresult.BuildConfig
import com.vlsantillan.imagetoresult.R
import com.vlsantillan.imagetoresult.databinding.FragmentHomeBinding
import com.vlsantillan.imagetoresult.ui.camera.CameraViewModel
import com.vlsantillan.imagetoresult.util.ImageUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val cameraViewModel: CameraViewModel by activityViewModels()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Array<String>>

    private var bitmap: Bitmap? = null

    private val launcher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                try {
                    bitmap =
                        ImageUtils.getBitmapFromContentUri(requireContext().contentResolver, it)
                } catch (ex: IOException) {
                    Log.e("HomeScreen", ex.message.toString())
                }
            }
        }

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
            for (granted in result.values) {
                allAreGranted = allAreGranted && granted
            }

            if (allAreGranted) {
                findNavController().navigate(R.id.camera_fragment)
            }
        }

        binding.buttonHomeAddResult.setOnClickListener {
            if (BuildConfig.FLAVOR_functionality == "camera") {
                val permissions = arrayOf(
                    Manifest.permission.CAMERA
                )
                activityResultLauncher.launch(permissions)
            } else {
                launcher.launch("image/*")
                bitmap = null
                cameraViewModel.clearResult()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            lifecycleScope.launch {
                cameraViewModel.calculationResult.collect {
                    if (it?.result != null) {
                        binding.imageViewHome.isVisible = true
                        binding.groupHomeEquation.isVisible = true
                        binding.textViewHomeInstruction.isVisible = false
                        binding.textViewHomeInput.text =
                            "Input: ${it.input1} ${it.operation} ${it.input2}"
                        binding.textViewHomeResult.text = "Result: ${it.result}"
                    } else {
                        binding.imageViewHome.isVisible = false
                        binding.groupHomeEquation.isVisible = false
                        binding.textViewHomeInstruction.isVisible = true
                        binding.textViewHomeInstruction.text =
                            getString(R.string.home_error_reading)
                    }
                }
            }
            lifecycleScope.launch {
                cameraViewModel.currentSource.collect {
                    val source = bitmap ?: it
                    if (source != null) {
                        binding.imageViewHome.isVisible = true
                        cameraViewModel.readImage(source)
                        binding.imageViewHome.setImageBitmap(source)
                    } else {
                        binding.groupHomeEquation.isVisible = false
                        binding.imageViewHome.isVisible = false
                        binding.textViewHomeInstruction.isVisible = true
                        binding.textViewHomeInstruction.text = getString(R.string.home_instruction)
                    }
                }
            }
        }
    }
}