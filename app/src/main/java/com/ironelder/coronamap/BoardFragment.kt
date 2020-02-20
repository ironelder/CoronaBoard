package com.ironelder.coronamap


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ironelder.coronamap.model.CoronaBoardModel
import kotlinx.android.synthetic.main.fragment_board.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class BoardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_corona_board.adapter = CoronaBoardRecyclerViewAdapter()

        GlobalScope.launch {
            val firebaseReference = FirebaseDatabase.getInstance().getReference("CoronaBoard")
            firebaseReference.addValueEventListener(object  : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val coronaBoardModelList = arrayListOf<CoronaBoardModel>()
                    snapshot.children.forEach{
                        val coronaBoard = it.getValue(CoronaBoardModel::class.java)
                        coronaBoard?.let {
                            coronaBoardModelList.add(coronaBoard)
                        }
                    }
                    (rv_corona_board.adapter as CoronaBoardRecyclerViewAdapter).setItemList(coronaBoardModelList)
                }

            })
        }
    }


}
