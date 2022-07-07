package com.jdk_to_lsi.userlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jdk_to_lsi.userlist.R
import com.jdk_to_lsi.userlist.databinding.FragmentUserListBinding
import com.jdk_to_lsi.userlist.model.User
import com.jdk_to_lsi.userlist.view.adapter.UserAdapter
import com.jdk_to_lsi.userlist.viewmodel.UserViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserListFragment : Fragment() {
    private lateinit var adapter: UserAdapter
    private lateinit var binding: FragmentUserListBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: UserViewModel

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
        viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        binding = FragmentUserListBinding.inflate(LayoutInflater.from(requireContext()))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserAdapter(::onClickAction)
        binding.recycler.adapter = adapter
        initObservable()
    }

    private fun initObservable() {
        viewModel.getUsers().observe(viewLifecycleOwner) { users ->
            if (users?.isNotEmpty() == true) {
                adapter.setUsers(users)
            }
        }
    }

    private fun onClickAction(user:User){
        viewModel.setViewedUser(user)
        requireActivity().supportFragmentManager.beginTransaction().add(R.id.container,UserDetailsFragment()).addToBackStack(UserListFragment::class.java.simpleName).commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}