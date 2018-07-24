package com.example.saurabh.firebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {

    var mstorage: DatabaseReference? = null
        val urls=ArrayList<uploaded>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mstorage=FirebaseDatabase.getInstance().getReference()


        mstorage!!.addValueEventListener( object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {

                for(i in p0.children) {
                    val imageUrl = i.getValue(String::class.java)
                    val data=uploaded(imageUrl!!)
                    urls.add(data)


                    Log.d("upload test", "url=" + imageUrl)
                }

            }

        })
        val fireBaseAdapter=FireBaseAdapter(urls)

        rvFirebase.layoutManager= LinearLayoutManager(this@Main2Activity)
        rvFirebase.adapter=fireBaseAdapter

    }
}
