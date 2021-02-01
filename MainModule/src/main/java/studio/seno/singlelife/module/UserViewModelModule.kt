package studio.seno.singlelife.module

import com.google.firebase.auth.FirebaseAuth
import studio.seno.singlelife.viewmodel.UserViewModel

class UserViewModelModule(viewModel : UserViewModel) {
    private val mAuth = FirebaseAuth.getInstance()
    private val memberViewModel = viewModel

    fun enableLogin(email : String, password : String)  {
        if (email.isNotEmpty() && email != ""
            && password.isNotEmpty() && password != "") {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful)
                        memberViewModel.setLoginLiveData(true)
                    else
                        memberViewModel.setLoginLiveData(false)
                }
        }
    }

    fun sendFindEmail(emailAddress : String){
        mAuth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener {
                if(it.isSuccessful)
                    memberViewModel.setFindPasswordListData(true)
                else
                    memberViewModel.setFindPasswordListData(false)
            }
    }

    fun registerUser(email : String, password : String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful)
                    memberViewModel.setRegisterLiveData(true)
                 else
                    memberViewModel.setRegisterLiveData(false)
            }
    }
}