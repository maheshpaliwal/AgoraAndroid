package com.maheshpaliwal.agora_android

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.maheshpaliwal.agora_android.model.Election_info


// election card adapter to fill election information like election name etc in recyclerview
class ElectionCardAdapter (private val election_info:ArrayList<Election_info>): RecyclerView.Adapter<ElectionCardAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.election_card, parent, false)



        return ViewHolder(view)

    }

    override fun getItemCount() = election_info.size

    override fun onBindViewHolder(holder: ElectionCardAdapter.ViewHolder, p1: Int) {



          // setting information to election using recieved data by volley
        holder.title.text = election_info[p1].title
        holder.description.text = election_info[p1].description
        holder.candidates.text = election_info[p1].candidates
        holder.start.text = election_info[p1].start
        holder.end.text = election_info[p1].end
        holder.status.text = election_info[p1].status


    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       //initializing title
        val title: TextView = itemView.findViewById(R.id.election_title)
        // initializing description
        val description: TextView = itemView.findViewById(R.id.description)
        // candidates
        val candidates: TextView = itemView.findViewById(R.id.candidates)
        // start time
        val start: TextView = itemView.findViewById(R.id.start_time)
        // end time
        val end: TextView = itemView.findViewById(R.id.end_time)
            // status
        val status: TextView = itemView.findViewById(R.id.status)

    }
}
