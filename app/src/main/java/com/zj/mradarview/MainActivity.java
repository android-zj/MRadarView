package com.zj.mradarview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MRadarView radarView;
    private String tagName[] = new String[]{"金钱", "击杀", "物理", "魔法", "防御", "生存"};
    private int tagPercent[] = new int[]{5,2,3,1,4,6};
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        radarView = findViewById(R.id.radarView);
        addTagList();
    }

    private void addTagList() {
        for (int i = 0; i < tagName.length; i++) {
            list.add(tagName[i]);
        }

        radarView.setCount(list.size()); //设置多边形数量
        //多边形tag
        CharSequence[] csTag = list.toArray(new CharSequence[list.size()]);
        radarView.setTitles(csTag);
        setRegionShader();  //绘制网格

        double max = tagPercent[0];
        for (int i = 0; i < tagPercent.length; i++) {
            if (tagPercent[i] > max) {
                max = tagPercent[i];
            }
        }

        double [] percent = new double[list.size()];
        //  radarView.setValues(tagValues.toArray(new CharSequence[tag.length]));
        if (max == 0) {
            radarView.setPointRadius(0);
        } else {
            for (int i = 0; i < tagPercent.length; i++) {
                 percent [i]  = (tagPercent[i] / max);
                // Log.v("AttributeEFragment", "tagPercent---i="+(tagList.get(i).getVal()/ max));
            }
        }

        radarView.setPercents(percent);
    }

    //设置内圈颜色
    private void setRegionShader() {
        radarView.setColors(null);
        if (radarView.getColors() != null) {
            return;
        }
        radarView.setEnabledRegionShader(true);
    }

}
