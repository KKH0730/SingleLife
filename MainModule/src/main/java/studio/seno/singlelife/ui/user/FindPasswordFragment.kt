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
import studio.seno.commonmodule.CustomToast
import studio.seno.singlelife.R
import studio.seno.singlelife.databinding.FragmentFindPasswordBinding
import studio.seno.singlelife.module.CommonFunction
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
            CommonFunction.closeKeyboard(requireContext(), binding.emailInput)
            var emailAddress = binding.emailInput.text.toString().trim()

            if(emailAddress.isEmpty()) {
                CustomToast(requireContext(), getString(R.string.find_password_announcement1)).show()
            } else {
                viewModel.sendFindEmail(emailAddress)
                viewModel.getFindPasswordListData().observe(requireActivity(), {
                    if (it) {
                        progressGenerator.start(binding.sendEmail)
                        binding.emailInput.isEnabled = false
                        binding.sendEmail.isEnabled = false
                    } else
                        CustomToast(requireContext(), getString(R.string.find_password_announcement1)).show()
                })
            }
        }
    }




    override fun onComplete() {
        CustomToast(requireContext(), getString(R.string.find_password_announcement2)).show()
        findNavController().navigate(R.id.action_findPasswordFragment_to_loginFragment)
    }
}