package com.example.saurabh.firebase

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*

import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

     var storageRefernce: StorageReference? = null
     var filePath: Uri? =null
     var mstorage:DatabaseReference ?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE),112)

        storageRefernce=FirebaseStorage.getInstance().getReference()

        mstorage=FirebaseDatabase.getInstance().getReference()

        Log.d("Error","Activity Started")
        btnChoose.setOnClickListener({
            chooseFile()


        })

        btnUpload.setOnClickListener({
            if(ivUploaded.drawable==null)
            {
                Toast.makeText(this,"Please select an image",Toast.LENGTH_SHORT).show()
            }
            else {
                upload()
            }
        })

        viewUploads.setOnClickListener({
            val intent=Intent(this,Main2Activity::class.java)
            startActivity(intent)
        })
    }

    fun upload()
    {
        val riversRef = storageRefernce!!.child("images/"+UUID.randomUUID().toString())
        val progressDial=ProgressDialog(this)
        progressDial.setTitle("Uploading...")
        progressDial.show()
        riversRef.putFile(filePath!!)
                .addOnSuccessListener( { taskSnapshot ->
                    progressDial.dismiss()
                    Toast.makeText(this,"Successfully uploaded",Toast.LENGTH_SHORT).show()

                    taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                        val result = it.toString()
                        mstorage!!.push().setValue(result)

                    }

                })
                .addOnFailureListener( {
                    Toast.makeText(this,"Upload Failed",Toast.LENGTH_SHORT).show()

                })
                .addOnProgressListener {
                    val progress = (100.0*it.bytesTransferred/it.totalByteCount)
                    progressDial.setMessage("Uploaded "+progress+"%")
                    progressDial.setCancelable(false)
                }

    }



    fun chooseFile()
    {
        val intent=Intent()
        intent.type = "image/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Select an image"),123)
        Log.d("Error","File Selected")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (data != null) {
            Log.d("Error",data.data.toString()
            )
            filePath= data.data!!
        }
        Picasso.get().load(filePath).resize(500,500).into(ivUploaded)

    }
}
