package com.stevemd.feature.repolist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.squareup.picasso.Picasso
import com.stevemd.common.adapter.DataBoundListAdapter
import com.stevemd.common.model.RepoItemEntity
import com.stevemd.feature.repolist.databinding.RepoItemListRowBinding

class RepoListAdapter(
    private val onClick:(View.OnClickListener)
) : DataBoundListAdapter<RepoItemEntity, RepoItemListRowBinding>(
    diffCallback = object : DiffUtil.ItemCallback<RepoItemEntity>(){
        override fun areItemsTheSame(oldItem: RepoItemEntity, newItem: RepoItemEntity): Boolean {
            return oldItem.repoFullName == newItem.repoFullName
        }

        override fun areContentsTheSame(oldItem: RepoItemEntity, newItem: RepoItemEntity): Boolean {
            return oldItem.repoFullName == newItem.repoFullName
        }
    }
){
    override fun createBinding(parent: ViewGroup): RepoItemListRowBinding  =
        RepoItemListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

    @SuppressLint("SetTextI18n")
    override fun bind(binding: RepoItemListRowBinding, item: RepoItemEntity, position: Int) {
        binding.root.setOnClickListener(onClick)
        Picasso.get().load(item.userAvatarUrl).into(binding.imageView3)
        binding.textViewUsername.text = item.userName
        binding.repositoryNameTextView.text = item.repoFullName
        binding.textViewRepositoryDescription.text = item.repoDescription
        binding.textViewLanguage.text = item.language
        binding.starsTextView.text = "${item.stargazers_count} Stars"
        binding.textViewForks.text = "${item.stargazers_count} Forked"
    }
}