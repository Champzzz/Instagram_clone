package com.ayush.instagram_clone.adapters

import android.content.Context import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayush.instagram_clone.Models.Post
import com.ayush.instagram_clone.Models.Reels
import com.ayush.instagram_clone.R
import com.ayush.instagram_clone.databinding.MyPostRvDesignBinding
import com.ayush.instagram_clone.databinding.ReelDgBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.squareup.picasso.Picasso


class Reel_Adapter(var context: Context, var reels_list:ArrayList<Reels>): RecyclerView.Adapter<Reel_Adapter.ViewHolder>() {

    inner class ViewHolder(var binding: ReelDgBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ReelDgBinding.inflate(LayoutInflater.from(context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Reel_Adapter.ViewHolder, position: Int) {
        Picasso.get().load(reels_list.get(position).profile_link).placeholder(R.drawable.add_profile).into(holder.binding.reelDGProfilPic)
        holder.binding.reelDGCaption.setText(reels_list.get(position).caption)
        holder.binding.videoView.setVideoPath(reels_list.get(position).ReelUrl)
        holder.binding.videoView.setOnPreparedListener{
            holder.binding.ReelWaitProgressBar.visibility = View.GONE
            holder.binding.videoView.start()
        }
    }

    override fun getItemCount(): Int {
        return reels_list.size
    }

}