package com.leeeyou.wanandroid

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leeeyou.R
import com.leeeyou.manager.BaseFragment
import com.leeeyou.util.inflate
import kotlinx.android.synthetic.main.fragment_wan_android.*
import timber.log.Timber


/**
 * ClassName:   WanAndroidFragment
 * Description: Play Android
 *
 * Author:      leeeyou
 * Date:        2017/4/24 13:46
 */
private const val DOUBLE_CLICK_TIME = 500 //ms

class WanAndroidFragment : BaseFragment() {
    private lateinit var mViewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_wan_android)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val wanAndroidRecommendFragment = WanAndroidRecommendFragment()
        val fragmentList = arrayOf(wanAndroidRecommendFragment, WanAndroidSystemFragment(), WanAndroidProjectFragment())
        val titleList = arrayOf("推荐", "体系", "项目")

        mViewPagerAdapter = ViewPagerAdapter(activity!!.supportFragmentManager, fragmentList, titleList)

//        wanAndroidViewPager.offscreenPageLimit = 3
        wanAndroidViewPager.adapter = mViewPagerAdapter
        wanAndroidTabLayout.setupWithViewPager(wanAndroidViewPager)
        wanAndroidTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            var lastPressTime: Long = 0
            override fun onTabReselected(p0: TabLayout.Tab?) {
                if (System.currentTimeMillis() - lastPressTime < DOUBLE_CLICK_TIME) {
                    Timber.d("Invalid double click event , click position is  %s", p0?.position.toString())
                } else {
                    wanAndroidRecommendFragment.gotoFirstPage()
                }
                lastPressTime = System.currentTimeMillis()
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
            }
        })
    }
}

private class ViewPagerAdapter(fm: FragmentManager, private val mFragmentList: Array<BaseFragment>, private val titleList: Array<String>)
    : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = mFragmentList[position]

    override fun getCount(): Int = mFragmentList.size

    override fun getPageTitle(position: Int): CharSequence = titleList[position]
}