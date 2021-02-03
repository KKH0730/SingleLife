package studio.seno.singlelife.ui.user

import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.royrodriguez.transitionbutton.TransitionButton
import org.jetbrains.anko.support.v4.startActivity
import studio.seno.commonmodule.CustomToast
import studio.seno.singlelife.MainActivity
import studio.seno.singlelife.R
import studio.seno.singlelife.util.ViewControlListener
import studio.seno.singlelife.databinding.FragmentRegisterBinding
import studio.seno.singlelife.module.CommonFunction
import studio.seno.singlelife.util.TextUtils
import studio.seno.singlelife.viewmodel.UserViewModel


class RegisterFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: UserViewModel by viewModels()
    private lateinit var viewControlListener : ViewControlListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is ViewControlListener)
            viewControlListener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_register,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        var ssb = SpannableStringBuilder(binding.moveLoginBtn.text.toString())
        ssb = TextUtils.setTextColorBold(ssb, requireContext(), R.color.red_error, 14, 19).apply {
            binding.moveLoginBtn.text = ssb
        }

        binding.moveLoginBtn.setOnClickListener(this)
        binding.registerBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.move_login_btn) {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        } else if (v?.id == R.id.register_Btn) {
            binding.registerBtn.startAnimation()
            CommonFunction.closeKeyboard(requireContext(), binding.emailInput)

            val email: String = binding.emailInput.text.toString().trim()
            val password: String = binding.passInput.text.toString().trim()
            val nickName: String = binding.nicknameInput.text.toString().trim()



            if (email.isEmpty() || nickName.isEmpty() || password.isEmpty()) {
                binding.registerBtn.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null)
            } else {
                viewModel.registerUser(email, password)
                viewModel.getRegisterLiveData().observe(requireActivity(), {
                    if (it) {
                        binding.registerBtn.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND) {
                            startActivity<MainActivity>()
                            viewControlListener.finishCurrentActivity()
                        }
                    } else {
                        binding.registerBtn.stopAnimation(
                            TransitionButton.StopAnimationStyle.SHAKE,
                            null
                        )
                        CustomToast(requireContext(), getString(R.string.register_error)).show()
                    }
                })
            }


        }
    }
}