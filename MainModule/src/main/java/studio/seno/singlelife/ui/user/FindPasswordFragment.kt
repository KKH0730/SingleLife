package studio.seno.singlelife.ui.user

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import studio.seno.singlelife.R
import studio.seno.singlelife.databinding.FragmentFindPasswordBinding
import studio.seno.singlelife.util.ProgressGenerator
import studio.seno.singlelife.viewmodel.UserViewModel


class FindPasswordFragment : Fragment(), ProgressGenerator.OnCompleteListener {
    private lateinit var binding : FragmentFindPasswordBinding
    private val progressGenerator by lazy{ProgressGenerator(this)}
    private val viewModel : UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_find_password,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.sendEmail.setOnClickListener {
            closeKeyboard(binding.emailInput)
            var emailAddress = binding.emailInput.text.toString().trim()
            viewModel.sendFindEmail(binding.emailInput.text.toString().trim())

            if(emailAddress.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.find_password_announcement1),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                viewModel.getFindPasswordListData().observe(requireActivity(), {
                    if (it) {
                        progressGenerator.start(binding.sendEmail)
                        binding.emailInput.isEnabled = false
                        binding.sendEmail.isEnabled = false
                    } else
                        Toast.makeText(
                            requireContext(),
                            resources.getString(R.string.find_password_announcement1),
                            Toast.LENGTH_LONG
                        ).show()
                })
            }
        }
    }




    override fun onComplete() {
        Toast.makeText(
            requireContext(),
            resources.getString(R.string.find_password_announcement2),
            Toast.LENGTH_LONG
        ).show()

        findNavController().navigate(R.id.action_findPasswordFragment_to_loginFragment)
    }

    private fun closeKeyboard(editText: EditText) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

}