package com.codingpp.myandroid.myviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codingpp.myandroid.databinding.FragmentPageBinding;
import com.codingpp.myandroid.myviews.constant.Constants;


/**
 * 页面Fragment
 *
 * @author sunpan
 * @date 2019/2/21
 */
public class PageFragment extends Fragment {

    @LayoutRes
    int sampleLayoutRes;
    @LayoutRes
    int practiceLayoutRes;

    private FragmentPageBinding binding;

    public static PageFragment newInstance(@LayoutRes int sampleLayoutRes, @LayoutRes int practiceLayoutRes) {
        PageFragment pageFragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.SAMPLE_LAYOUT_RES, sampleLayoutRes);
        args.putInt(Constants.PRACTICE_LAYOUT_RES, practiceLayoutRes);
        pageFragment.setArguments(args);
        return pageFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPageBinding.inflate(inflater, container, false);
        //ViewStub，是一个大小为0,默认不可见的控件,设置成View.Visible或调用了它的inflate()之后才会填充布局资源，占用资源少
        binding.sampleStub.setLayoutResource(sampleLayoutRes);
        binding.sampleStub.inflate();

        binding.practiceStub.setLayoutResource(practiceLayoutRes);
        binding.practiceStub.inflate();

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            sampleLayoutRes = args.getInt(Constants.SAMPLE_LAYOUT_RES);
            practiceLayoutRes = args.getInt(Constants.PRACTICE_LAYOUT_RES);
        }
    }
}
