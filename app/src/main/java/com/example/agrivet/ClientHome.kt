package com.example.agrivet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agrivet.Adapter.MyAdapter
import com.example.agrivet.Models.VeterinarianViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ClientHome.newInstance] factory method to
 * create an instance of this fragment.
 */

private lateinit var viewModel : VeterinarianViewModel
private lateinit var veterinarianRecyclerView: RecyclerView
lateinit var cadapter: MyAdapter


class ClientHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_client_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ClientHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ClientHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        veterinarianRecyclerView = view.findViewById(R.id.recyclerView)
        veterinarianRecyclerView.layoutManager = LinearLayoutManager(context)
        veterinarianRecyclerView.setHasFixedSize(true)
        cadapter = MyAdapter()
        veterinarianRecyclerView.adapter = cadapter

        viewModel = ViewModelProvider(this).get(VeterinarianViewModel::class.java)

        viewModel.allUsers.observe(viewLifecycleOwner, Observer{

            cadapter.updateUserList(it)
        })
    }


}
