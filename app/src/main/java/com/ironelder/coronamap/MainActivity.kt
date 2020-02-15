package com.ironelder.coronamap

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ironelder.coronamap.model.CoronaBoardModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_corona_board.adapter = CoronaBoardRecyclerViewAdapter()

        GlobalScope.launch {
            val firebaseReference = FirebaseDatabase.getInstance().getReference("TestCollection")
            var list = arrayListOf<CoronaBoardModel>()
            list.add(CoronaBoardModel("KOR", 10,0,10))
            list.add(CoronaBoardModel("JPN", 20,0,0))
            list.add(CoronaBoardModel("TWN", 15,0,10))
            list.add(CoronaBoardModel("THD", 33,0,21))
//            firebaseReference.setValue(list)
            firebaseReference.addValueEventListener(object  : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    list = p0.value as ArrayList<CoronaBoardModel>
                    Log.d("ironelder", "Country = $list")
                }

            })
//            firebaseReference.addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    val boardList = arrayListOf<CoronaBoardModel>()
//                    val category = dataSnapshot.value as HashMap<*, *>
//                    category.keys.forEach {
//                        boardList.add(CoronaBoardModel(it.toString(), -1, BOARD_TYPE_HEADER))
//                        (category[it] as HashMap<*, *>).let { categoryMap ->
//                            Log.d("ironelder", "Country = $categoryMap")
//                            categoryMap.forEach { countryMap ->
//                                Log.d("ironelder", "Country = $countryMap")
//                                boardList.add(
//                                    CoronaBoardModel(
//                                        resources.getString(
//                                            resIdByName(
//                                                countryMap.key.toString(),
//                                                "string"
//                                            )
//                                        ), countryMap.value.toString().toInt(), BOARD_TYPE_ITEM
//                                    )
//                                )
//                            }
//                        }
//                        Log.d("ironelder", "Country = $it")
//                    }
//                    (rv_corona_board.adapter as CoronaBoardRecyclerViewAdapter).setItemList(boardList)
//                }
//
//                override fun onCancelled(error: DatabaseError) { // Failed to read value
//                    Log.w("ironelder", "Failed to read value.", error.toException())
//                }
//            })
        }
    }
}
