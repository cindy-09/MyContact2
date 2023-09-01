package my.edu.tarc.mycontact.ui.contact_list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import my.edu.tarc.mycontact.ContactAdapter
import my.edu.tarc.mycontact.databinding.FragmentContactListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ContactListFragment : Fragment() {

    private var _binding: FragmentContactListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val contactViewModel: ContactViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Create an instance of RecycleView adapter
        val adapter = ContactAdapter()

        //Adapter an observer to the Contact List (Live Data)
        contactViewModel.contactList.observe(viewLifecycleOwner) {
            if (it.isEmpty()){
                Toast.makeText(context,"No record", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(context, "Record Count : " + it.size, Toast.LENGTH_SHORT).show()
            }
            //update the adapter
            adapter.setContact(it)
        }

        //Attach the adapter to the Recycler View
        binding.recycleViewContact.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}