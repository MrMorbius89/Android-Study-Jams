package com.example.firstfm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.firstfm.albums.view.AlbumsFragment
import com.example.firstfm.artists.view.ArtistsFragment
import com.example.firstfm.tracks.view.TracksFragment

class ViewPagerAdapter (fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    private val fragments : List<Fragment> = listOf(ArtistsFragment(), AlbumsFragment(), TracksFragment())

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}