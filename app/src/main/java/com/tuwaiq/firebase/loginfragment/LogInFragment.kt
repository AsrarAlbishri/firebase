package com.tuwaiq.firebase.loginfragment



import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.tuwaiq.firebase.R
import com.tuwaiq.firebase.registerfragment.RegisterFragment

class LogInFragment : Fragment() {

    private lateinit var emailET :EditText
    private lateinit var passwordET : EditText
    private lateinit var loginBtn : Button

    private val viewModel: LogInViewModel by lazy { ViewModelProvider(this).get(LogInViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.log_in_fragment, container, false)

        emailET = view.findViewById(R.id.log_in_email_et)
        passwordET = view.findViewById(R.id.log_in_password_et)
        loginBtn = view.findViewById(R.id.log_in_btn)

        return view
    }

    override fun onStart() {
        super.onStart()

        loginBtn.setOnClickListener {
            RegisterFragment.auth.signInWithEmailAndPassword(emailET.text.toString(),passwordET.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        showToast("login successful")
                    }else{
                        showToast("login failed")
                    }
                }
        }
    }
    private fun showToast(message:String){
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }

}