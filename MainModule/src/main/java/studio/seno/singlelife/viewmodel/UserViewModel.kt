package studio.seno.singlelife.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import studio.seno.singlelife.module.UserViewModelModule

class UserViewModel : ViewModel() {
    private val memberViewModelModule = UserViewModelModule(this)

    private val loginLiveData : MutableLiveData<Boolean> = MutableLiveData()
    private val findPasswordListData : MutableLiveData<Boolean> = MutableLiveData()
    private val registerLiveData : MutableLiveData<Boolean> = MutableLiveData()



    fun getLoginLiveDate() : MutableLiveData<Boolean>{
        return loginLiveData
    }

    fun setLoginLiveData(bool : Boolean){
        this.loginLiveData.value = bool
    }

    fun getFindPasswordListData() : MutableLiveData<Boolean> {
        return findPasswordListData
    }

    fun setFindPasswordListData(bool : Boolean) {
        this.findPasswordListData.value = bool
    }

    fun getRegisterLiveData() : MutableLiveData<Boolean> {
        return registerLiveData
    }

    fun setRegisterLiveData(bool : Boolean) {
        this.registerLiveData.value = bool
    }

    fun enableLogin(email : String, password : String)  {
        memberViewModelModule.enableLogin(email, password)
    }

    fun sendFindEmail(emailAddress : String){
        memberViewModelModule.sendFindEmail(emailAddress)
    }

    fun registerUser(email : String, password : String){
        memberViewModelModule.registerUser(email, password)
    }
}