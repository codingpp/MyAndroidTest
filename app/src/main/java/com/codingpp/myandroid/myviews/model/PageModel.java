package com.codingpp.myandroid.myviews.model;


import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;

/**
 * 页面数据Model
 *
 * @author sunpan
 * @date 2019/2/21
 */
public class PageModel {

    @LayoutRes
    private
    int sampleLayoutRes;
    @StringRes
    private
    int titleRes;
    @LayoutRes
    private
    int practiceLayoutRes;

    public PageModel(@LayoutRes int sampleLayoutRes, @StringRes int titleRes, @LayoutRes int practiceLayoutRes) {
        this.sampleLayoutRes = sampleLayoutRes;
        this.titleRes = titleRes;
        this.practiceLayoutRes = practiceLayoutRes;
    }

    public int getSampleLayoutRes() {
        return sampleLayoutRes;
    }

    public void setSampleLayoutRes(int sampleLayoutRes) {
        this.sampleLayoutRes = sampleLayoutRes;
    }

    public int getTitleRes() {
        return titleRes;
    }

    public void setTitleRes(int titleRes) {
        this.titleRes = titleRes;
    }

    public int getPracticeLayoutRes() {
        return practiceLayoutRes;
    }

    public void setPracticeLayoutRes(int practiceLayoutRes) {
        this.practiceLayoutRes = practiceLayoutRes;
    }
}
