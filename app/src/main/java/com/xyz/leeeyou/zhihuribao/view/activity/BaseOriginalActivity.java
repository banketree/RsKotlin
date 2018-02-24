package com.xyz.leeeyou.zhihuribao.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xyz.leeeyou.zhihuribao.R;
import com.xyz.leeeyou.zhihuribao.utils.ToolbarHelper;

/**
 * ClassName:   BaseOriginalActivity
 * Description: Toolbar native style
 *
 * Author:      leeeyou
 * Date:        2017/8/17 09:19
 */
public abstract class BaseOriginalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_original);

        setupActivityComponent();

        ToolbarHelper.checkSdkVersionToTranslucentFlag(getWindow());
    }

    abstract void setupActivityComponent();

    @Override
    public void setContentView(int layoutResID) {
        ToolbarHelper.setContentView(this, layoutResID);
    }

    @Override
    public void setContentView(View view) {
        ToolbarHelper.setContentView(this, view);
    }

    public void setLeftTitleAndDoNotDisplayHomeAsUp(String title) {
        ToolbarHelper.setLeftTitle(getSupportActionBar(), title);
        ToolbarHelper.hideHomeAsUp(getSupportActionBar());
    }

    public void setLeftTitleAndDisplayHomeAsUp(String title) {
        ToolbarHelper.setLeftTitle(getSupportActionBar(), title);
        ToolbarHelper.showHomeAsUp(getSupportActionBar());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
