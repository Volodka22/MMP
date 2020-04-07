package com.example.mmp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.fragment_menu.*
import androidx.fragment.app.FragmentActivity
import android.app.Activity
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator


class MenuFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pager.adapter = PagerAdapter(this)
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
    }
}