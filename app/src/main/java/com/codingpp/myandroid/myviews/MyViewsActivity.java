package com.codingpp.myandroid.myviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.codingpp.myandroid.R;
import com.codingpp.myandroid.databinding.ActivityMyViewsBinding;
import com.codingpp.myandroid.myviews.model.PageModel;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页
 *
 * @author sunpan
 * @date 2019/2/20
 */
public class MyViewsActivity extends AppCompatActivity {

    public static void jump(Context context) {
        Intent intent = new Intent(context, MyViewsActivity.class);
        context.startActivity(intent);
    }

    private List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.layout.sample_color, R.string.title_draw_color, R.layout.practice_color));
        pageModels.add(new PageModel(R.layout.sample_circle, R.string.title_draw_circle, R.layout.practice_circle));
        pageModels.add(new PageModel(R.layout.sample_rect, R.string.title_draw_rect, R.layout.practice_rect));
        pageModels.add(new PageModel(R.layout.sample_point, R.string.title_draw_point, R.layout.practice_point));
        pageModels.add(new PageModel(R.layout.sample_oval, R.string.title_draw_oval, R.layout.practice_oval));
        pageModels.add(new PageModel(R.layout.sample_line, R.string.title_draw_line, R.layout.practice_line));
        pageModels.add(new PageModel(R.layout.sample_round_rect, R.string.title_draw_round_rect, R.layout.practice_round_rect));
        pageModels.add(new PageModel(R.layout.sample_arc, R.string.title_draw_arc, R.layout.practice_arc));
        pageModels.add(new PageModel(R.layout.sample_path, R.string.title_draw_path, R.layout.practice_path));
        pageModels.add(new PageModel(R.layout.sample_histogram, R.string.title_draw_histogram, R.layout.practice_histogram));
        pageModels.add(new PageModel(R.layout.sample_pie_chart, R.string.title_draw_pie_chart, R.layout.practice_pie_chart));
        pageModels.add(new PageModel(R.layout.sample_circular_diagram, R.string.title_draw_circular_diagram, R.layout.practice_circular_diagram));
    }

    private ActivityMyViewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyViewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        binding.pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                PageModel pageModel = pageModels.get(position);
                return PageFragment.newInstance(pageModel.getSampleLayoutRes(), pageModel.getPracticeLayoutRes());
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModels.get(position).getTitleRes());
            }
        });

        //与viewpager绑定
        binding.tabLayout.setupWithViewPager(binding.pager);
    }
}
