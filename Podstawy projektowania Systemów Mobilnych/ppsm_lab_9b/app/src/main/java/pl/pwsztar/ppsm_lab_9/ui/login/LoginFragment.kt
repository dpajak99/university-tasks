package pl.pwsztar.ppsm_lab_9.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.pwsztar.ppsm_lab_9.R

class LoginFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginField: EditText;
    private lateinit var root: View;

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        loginViewModel =
                ViewModelProvider(this).get(LoginViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_login, container, false)
        loginField = root.findViewById(R.id.edit_text_login)
        val setUpLoginBtn: Button = root.findViewById(R.id.btn_setup_login)

        setUpLoginBtn.setOnClickListener {
            setupLogin();
        }
        return root
    }

    private fun setupLogin() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString( "USERNAME", loginField.text.toString() )
            apply()
        }
    }
}
