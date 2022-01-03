package pl.pwsztar.ppsm_lab_9.remote

import pl.pwsztar.ppsm_lab_9.model.ShoutboxItemsHolder
import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceholderAPI {
    @GET("shoutbox/messages")
    fun fetchQuestion(): Call<ShoutboxItemsHolder>

    @FormUrlEncoded
    @POST("shoutbox/message")
    fun sendData(@Field("content") nick: String, @Field("login") text: String): Call<Void>
}
