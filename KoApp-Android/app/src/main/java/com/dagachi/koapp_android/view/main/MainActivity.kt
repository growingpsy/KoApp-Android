package com.dagachi.koapp_android.view.main

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dagachi.koapp_android.R
import com.dagachi.koapp_android.databinding.ActivityMainBinding
import com.dagachi.koapp_android.view.base.BaseActivity

class MainActivity: BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private var backPressedTime: Long = 0

    override fun beforeSetContentView() {
    }

    override fun initAfterBinding() {
        // 네비게이션 설정
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_frm) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bnvLearnerMain, navController)
    }

    // 학습자의 바텀 네비게이션 바 숨기기
    fun hideLearnerBottomNav(isHide: Boolean) {
        if (isHide) {
            binding.bnvLearnerMain.visibility = View.GONE
        }
        else {
            binding.bnvLearnerMain.visibility = View.VISIBLE
        }
    }

    // 뒤로가기 버튼 이벤트
    fun getBackPressedEvent() {
        for (fragment: Fragment in supportFragmentManager.fragments) {
            if (fragment.isVisible) {
                if (System.currentTimeMillis() > backPressedTime + 2000) {
                    backPressedTime = System.currentTimeMillis()
                    showToast("\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.")
                } else if (System.currentTimeMillis() <= backPressedTime + 2000) {
                    // 홈 화면에서 2초 안에 두번 뒤로가기 누를 경우 앱 종료
                    finish()
                    finishAffinity()
                }
            }
        }
    }
}