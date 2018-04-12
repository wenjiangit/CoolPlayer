package com.wenjian.videoplay;

import com.wenjian.base.entity.Clarity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: ClarityAdapter
 * Date: 2018/4/9
 *
 * @author jian.wen@ubtrobot.com
 */

public class ClarityAdapter {


    public static List<com.xiao.nicevideoplayer.Clarity> convert(List<Clarity> clarities) {
        List<com.xiao.nicevideoplayer.Clarity> result = new ArrayList<>();
        for (Clarity clarity : clarities) {
            result.add(new com.xiao.nicevideoplayer.Clarity(clarity.grade, clarity.p, clarity.videoUrl));
        }

        return result;

    }
}
