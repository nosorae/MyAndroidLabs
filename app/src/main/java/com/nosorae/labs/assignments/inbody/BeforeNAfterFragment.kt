package com.nosorae.labs.assignments.inbody

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nosorae.labs.R
import com.nosorae.labs.databinding.FragmentBeforeNAfterBinding

// TODO 스테이트 패턴 적용
class BeforeNAfterFragment : Fragment(R.layout.fragment_before_n_after) {
    private var binding: FragmentBeforeNAfterBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentBeforeNAfterBinding = FragmentBeforeNAfterBinding.bind(view)
        binding = fragmentBeforeNAfterBinding

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
            val openCamera =
                registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
                    handleSelectedState(bitmap, null)
                    notifyToParentActivity()
                }

            btCamera.setOnClickListener {
                openCamera.launch()
            }
        }

    private fun initGalleryButton(fragmentBeforeNAfterBinding: FragmentBeforeNAfterBinding) =
        with(fragmentBeforeNAfterBinding) {
            val getContent =
                registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                    handleSelectedState(null, uri)
                    notifyToParentActivity()
                }

            val requestReadStoragePermission =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                    if (result) {
                        getContent.launch("image/*")
                    } else {
                        Toast.makeText(context, "권한을 허용해야 갤러리에 접근 가능합니다.", Toast.LENGTH_LONG).show()
                    }
                }

            btGallery.setOnClickListener {
                requestReadStoragePermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }

    private fun handleSelectedState(bitmap: Bitmap?, uri: Uri?) {
        binding?.apply {
            bitmap?.let { ivPicture.setImageBitmap(it) }
            uri?.let { ivPicture.setImageURI(it) }
            btCamera.isGone = true
            btGallery.isGone = true
            tvBeforeOrAfter.text = tag
            tvBeforeOrAfter.isGone = false
        }
    }

    private fun notifyToParentActivity() {
        if (tag == "전") {
            requireActivity().supportFragmentManager.setFragmentResult(BeforeAfterActivity.PARAM_BEFORE_RESULT,
                Bundle())
        } else {
            requireActivity().supportFragmentManager.setFragmentResult(BeforeAfterActivity.PARAM_AFTER_RESULT,
                Bundle())
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}