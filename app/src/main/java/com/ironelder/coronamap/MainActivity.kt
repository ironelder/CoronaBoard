package com.ironelder.coronamap

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ironelder.coronamap.model.CoronaBoardModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebaseReference = FirebaseDatabase.getInstance().getReference("TestCollection")
        firebaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val boardList = arrayListOf<CoronaBoardModel>()
                val category = dataSnapshot.value as HashMap<*, *>
                category.keys.forEach {
                    boardList.add(CoronaBoardModel(it.toString(), -1, BOARD_TYPE_HEADER))
                    (category[it] as HashMap<*, *>).let { categoryMap ->
                        categoryMap.forEach { countryMap ->
                            boardList.add(
                                CoronaBoardModel(
                                    resources.getString(
                                        resIdByName(
                                            countryMap.key.toString(),
                                            "string"
                                        )
                                    ), countryMap.value.toString().toInt(), BOARD_TYPE_ITEM
                                )
                            )
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) { // Failed to read value
                Log.w("ironelder", "Failed to read value.", error.toException())
            }
        })
    }
}
