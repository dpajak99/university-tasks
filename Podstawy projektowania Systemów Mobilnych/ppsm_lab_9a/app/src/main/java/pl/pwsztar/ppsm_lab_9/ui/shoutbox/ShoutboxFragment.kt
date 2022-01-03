package pl.pwsztar.ppsm_lab_9.ui.shoutbox

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pl.pwsztar.ppsm_lab_9.R

class ShoutboxFragment : Fragment() {

    private lateinit var shoutboxViewModel: ShoutboxViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        shoutboxViewModel =
                ViewModelProvider(this).get(ShoutboxViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_shoutbox, container, false)
        val textView: TextView = root.findViewById(R.id.tv_login)
        textView.text = getSavedLogin();
        return root
    }

    private fun getSavedLogin(): String?  {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return "Error 1"

        return sharedPref.getString("USERNAME", "Error 2");
    }
}
