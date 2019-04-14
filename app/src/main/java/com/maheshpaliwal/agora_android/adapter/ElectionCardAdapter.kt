package com.maheshpaliwal.agora_android.adapter

import android.app.Dialog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.maheshpaliwal.agora_android.model.Election_info
import android.support.v7.app.AppCompatActivity
import android.view.Window

import android.widget.ImageButton
import com.maheshpaliwal.agora_android.fragments.mainActivityFragments.ElectionDetails
import com.maheshpaliwal.agora_android.R


// election card adapter to fill election information ( Array list) like election name etc in recycler view
class ElectionCardAdapter (private val election_info:ArrayList<Election_info>): RecyclerView.Adapter<ElectionCardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        // defining election card layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.election_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = election_info.size

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        // setting information to election using recieved data by volley
        // set title text
        holder.title.text = election_info[p1].title
        // aet description text
        holder.description.text = election_info[p1].description
        // set candidates text
        holder.candidates.text = election_info[p1].candidates
        // set start text
        holder.start.text = election_info[p1].start
        // set end text
        holder.end.text = election_info[p1].end
        // set status
        holder.status.text = election_info[p1].status
        // click handling on view button
        holder.viewButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val manager= (p0!!.context as AppCompatActivity).supportFragmentManager
                val transaction = manager.beginTransaction()
                // redirect to election details tabbed activity
                val fragment= ElectionDetails()
                transaction.replace(R.id.fragmentholder,fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })
        // click handling on editbutton
        holder.editButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                // showing pop up overlay
                var  dialog =  Dialog(p0!!.context)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.custom_layout)
                dialog.setCanceledOnTouchOutside(false)
                dialog.setCancelable(true)
                dialog.show()
                // dismiss dialog
                val close_button: ImageButton =dialog.findViewById<ImageButton>(R.id.close)
                close_button.setOnClickListener{
                    dialog.dismiss()
                }
            }
        })
        // click handling on delete button
        holder.deleteButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                // showing pop up overlay
                var  dialog =  Dialog(p0!!.context)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.custom_layout_delete)
                dialog.setCanceledOnTouchOutside(false)
                dialog.setCancelable(true)
                dialog.show()
                //dismiss dialog
                val close_button: ImageButton =dialog.findViewById<ImageButton>(R.id.close)
                close_button.setOnClickListener{
                    dialog.dismiss()
                }
            }
        })
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
        // view button
        val viewButton:TextView=itemView.findViewById(R.id.view)
        // edit button
        val editButton:TextView=itemView.findViewById(R.id.edit)
        // delete Button
        val deleteButton:TextView=itemView.findViewById(R.id.delete)
    }
}
