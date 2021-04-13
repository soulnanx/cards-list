package com.hivecode.hearthstonecards.ui.githubRepos.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hivecode.domain.model.GitRepo
import com.hivecode.hearthstonecards.databinding.ItemGitRepoBinding

class GitReposAdapter: RecyclerView.Adapter<GitReposAdapter.ViewHolder>() {

    private val gitRepoList : MutableList<GitRepo> = mutableListOf()
    private var onClick: () -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemGitRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = gitRepoList[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int {
        return gitRepoList.size
    }

    fun setOnClick(onClick: () -> Unit){
        this.onClick = onClick
    }

    fun setList(items : List<GitRepo>){
        this.gitRepoList.clear()
        this.gitRepoList.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemGitRepoBinding,
        private val onClick: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(gitRepo: GitRepo) {
            binding.title.text = gitRepo.fullName
            binding.isPrivate.text = gitRepo.privateLabel()
        }
    }
}