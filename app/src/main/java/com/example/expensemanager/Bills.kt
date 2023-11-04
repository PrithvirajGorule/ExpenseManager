@file:Suppress("OverrideDeprecatedMigration")

package com.example.expensemanager

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.camera.camera2.internal.annotation.CameraExecutor
import androidx.camera.core.ImageCapture
import androidx.core.content.ContextCompat
import java.io.File
import java.util.concurrent.ExecutorService
import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.ContactsContract.CommonDataKinds.Im
import android.provider.ContactsContract.CommonDataKinds.Photo
import android.provider.MediaStore
import android.provider.SyncStateContract.Constants
import android.util.Log
import android.view.*
import android.webkit.WebView.FindListener
import android.widget.Adapter
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.appcompat.widget.AppCompatImageView
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.button.MaterialButton
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors


@Suppress("DEPRECATION", "OverrideDeprecatedMigration", "OverrideDeprecatedMigration",
    "OverrideDeprecatedMigration"
)
class Bills : AppCompatActivity() {
    private var currentPhotoPath:String?=null
    companion object {
        private const val PERMISSION_CODE = 1234
        private const val CAPTURE_CODE = 1001
        private const val CAMERA_REQUEST_CODE = 1
    }

    private lateinit var Button: MaterialButton
    private lateinit var imageView: ImageView
    private lateinit var image_uri: Uri
    private lateinit var imageView1: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills)

        Button = findViewById(R.id.btn_camera)
        imageView = findViewById(R.id.camera_bills)
        imageView1 = findViewById(R.id.iv_image)

        Button.setOnClickListener { v: View ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    val permission = arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    openCamera()
                }
            } else {
                openCamera()
            }
        }
    }


    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "new image")
        values.put(MediaStore.Images.Media.DESCRIPTION, "Camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
        val camintent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camintent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(camintent, CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK){
            if(requestCode== CAMERA_REQUEST_CODE){
                imageView1.setImageURI(image_uri)

            }
        }
    }
}