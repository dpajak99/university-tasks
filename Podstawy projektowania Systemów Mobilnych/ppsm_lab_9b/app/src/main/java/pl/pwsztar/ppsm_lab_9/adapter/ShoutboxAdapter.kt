package pl.pwsztar.ppsm_lab_9.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.pwsztar.ppsm_lab_9.R
import pl.pwsztar.ppsm_lab_9.model.ShoutboxItem

class ShoutboxAdapter(private val messageList: MutableList<ShoutboxItem>) : RecyclerView.Adapter<ShoutboxAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.shoutbox_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = messageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messageList[position]
        holder.itemNickname.text = message.login
        holder.itemData.text = message.date
        holder.itemMessage.text = message.content
    }

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        var itemData: TextView
        var itemMessage: TextView
        var itemNickname: TextView

        init {
            itemData = itemView.findViewById(R.id.data)
            itemMessage = itemView.findViewById(R.id.message)
            itemNickname = itemView.findViewById(R.id.nickname)
        }
    }
}
