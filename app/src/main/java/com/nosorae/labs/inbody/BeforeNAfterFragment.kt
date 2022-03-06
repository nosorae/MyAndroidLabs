package com.nosorae.labs.inbody

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.nosorae.labs.R
import com.nosorae.labs.databinding.FragmentBeforeNAfterBinding

class BeforeNAfterFragment : Fragment(R.layout.fragment_before_n_after) {
    private var binding: FragmentBeforeNAfterBinding? = null

    private val openCamera =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            binding?.apply {
                bitmap?.let {
                    ivPicture.setImageBitmap(it)
                    btCamera.isGone = true
                    btGallery.isGone = true
                }

            }
        }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            binding?.apply {
                log("image")
                ivPicture.setImageURI(uri)
                btCamera.isGone = true
                btGallery.isGone = true
            }
        }

    private val requestReadStoragePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                getContent.launch("image/*")
            } else {
                Toast.makeText(context, "권한을 허용해야 갤러리에 접근 가능합니다.", Toast.LENGTH_LONG).show()
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentBeforeNAfterBinding = FragmentBeforeNAfterBinding.bind(view)
        binding = fragmentBeforeNAfterBinding
        log("F onViewCreated")

        initAddButton(fragmentBeforeNAfterBinding)
        initCameraButton(fragmentBeforeNAfterBinding)
        initGalleryButton(fragmentBeforeNAfterBinding)

    }

    private fun initAddButton(fragmentBeforeNAfterBinding: FragmentBeforeNAfterBinding) =
        with(fragmentBeforeNAfterBinding) {
            btAdd.setOnClickListener {
                btAdd.isGone = true
                btCamera.isGone = false
                btGallery.isGone = false
            }
        }

    private fun initCameraButton(fragmentBeforeNAfterBinding: FragmentBeforeNAfterBinding) =
        with(fragmentBeforeNAfterBinding) {
            btCamera.setOnClickListener {
                openCamera.launch()
            }
        }

    private fun initGalleryButton(fragmentBeforeNAfterBinding: FragmentBeforeNAfterBinding) =
        with(fragmentBeforeNAfterBinding) {
            btGallery.setOnClickListener {
                requestReadStoragePermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("F onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        log("F onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        log("F onViewStateRestored")
        super.onViewStateRestored(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        log("F onStart")
    }

    override fun onResume() {
        super.onResume()
        log("F onResume")

    }

    override fun onPause() {
        super.onPause()
        log("F onPause")
    }

    override fun onStop() {
        super.onStop()
        log("F onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        log("F onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        log("F onDestroyView")
        super.onDestroyView()
    }




    override fun onDestroy() {
        super.onDestroy()
        log("F onDestroy")
        binding = null
    }


    fun hasExternalStoragePermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }


    private fun log(text: String) {
        Log.e("lifeCycle tag", text)
    }


}