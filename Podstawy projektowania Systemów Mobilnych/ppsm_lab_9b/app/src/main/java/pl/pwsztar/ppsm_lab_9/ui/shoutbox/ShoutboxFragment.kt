package pl.pwsztar.ppsm_lab_9.ui.shoutbox

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_shoutbox.*
import pl.pwsztar.ppsm_lab_9.R
import pl.pwsztar.ppsm_lab_9.adapter.ShoutboxAdapter
import pl.pwsztar.ppsm_lab_9.model.ShoutboxItemsHolder
import pl.pwsztar.ppsm_lab_9.remote.JsonPlaceholderAPI
import pl.pwsztar.ppsm_lab_9.remote.JsonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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

        getMessages();


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nickname.text = getSavedLogin()
        swipeRefresh.setOnRefreshListener() {
            refreshContent()
        }
        sendButton.setOnClickListener() {
            sendData();
        }
    }

    private fun getSavedLogin(): String {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return "Error 1"
        val nick = sharedPref.getString("USERNAME", "Error 2");
        if( nick != null ) {
            return nick;
        } else {
            return "Error"
        }
    }

    private fun sendData() {
        val jsonService = JsonService()
        val call = jsonService.sendData(getSavedLogin(), editText.text.toString())

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.i("TEST", t.toString());
            }
        })
    }

    private fun getMessages() {
        val jsonService = JsonService()
        val call = jsonService.fetchQuestion()

        call.enqueue(object : Callback<ShoutboxItemsHolder> {
            override fun onResponse(call: Call<ShoutboxItemsHolder>, response: Response<ShoutboxItemsHolder>) {
                if (response.isSuccessful) {
                    showData(response.body()!!)
                }
            }
            override fun onFailure(call: Call<ShoutboxItemsHolder>, t: Throwable) {
                Log.i("TEST", t.toString());
            }
        })
    }


    private fun refreshContent() {
        Handler().postDelayed({
            swipeRefresh.isRefreshing = false
            activity?.let { recreate(it) }
        }, 2500)
    }


    private fun showData(dataList: ShoutboxItemsHolder) {
        dataList.reverse()
        shoutboxAdapter.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ShoutboxAdapter(dataList)
        }

    }
}
