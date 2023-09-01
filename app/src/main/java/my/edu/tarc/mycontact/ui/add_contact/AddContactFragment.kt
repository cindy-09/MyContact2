package my.edu.tarc.mycontact.ui.add_contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import my.edu.tarc.mycontact.R
import my.edu.tarc.mycontact.databinding.FragmentAddContactBinding
import my.edu.tarc.mycontact.ui.contact_list.Contact
import my.edu.tarc.mycontact.ui.contact_list.ContactViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddContactFragment : Fragment(), MenuProvider {

    private var _binding: FragmentAddContactBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // ask activity to get view model
    private val contactViewModel: ContactViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {
            val newContact = Contact(
                binding.editTextName.text.toString(),
                binding.editTextPhone.text.toString(),
                binding.editTextEmailAddress.text.toString()
            )
            //Insert a new contact to the View Model
            //contactViewModel.contactList.value!!.add(newContact)
            contactViewModel.insert(newContact)
            //findNavController().navigate(R.id.action_AddContactFragment_to_ContactListFragment)
            findNavController().navigateUp()
        }

        //Add support for menu
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.findItem(R.id.action_settings).setVisible(false)
        menu.findItem(R.id.action_profile).setVisible(false)
        menu.findItem(R.id.action_about_us).setVisible(false)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == android.R.id.home){
            findNavController().navigateUp()
        }

        return true
    }
}