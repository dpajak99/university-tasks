package pl.pwsztar.ppsm_lab_9.remote

import pl.pwsztar.ppsm_lab_9.model.ShoutboxItemsHolder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsonService () {
    private val jsonPlaceholderAPI: JsonPlaceholderAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://tgryl.pl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI::class.java)
    }

    fun fetchQuestion(): Call<ShoutboxItemsHolder> {
        return jsonPlaceholderAPI.fetchQuestion();
    }

    fun sendData(nick: String, text: String): Call<Void> {
        return jsonPlaceholderAPI.sendData(nick, text);
    }
}
