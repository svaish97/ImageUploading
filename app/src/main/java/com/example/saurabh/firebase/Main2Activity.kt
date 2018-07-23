package com.example.saurabh.firebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {

//    val uploads:ArrayList<String>
//    val mDatabase: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        mDatabase=FirebaseDatabase.getInstance().getReference("Upload Data")
//
//        mDatabase=object :ValueEventListener{
//            override fun onCancelled(p0: DatabaseError?) {
//
//            }
//
//            override fun onDataChange(p0: DataSnapshot?) {
//               val upload=p0.getValue()
//            }
//
//        }
//
//
//        val fireBaseAdapter=FireBaseAdapter(uploads)
//
//        rvFirebase.layoutManager=LinearLayoutManager
//        rvFirebase.adapter=fireBaseAdapter

    }
}
