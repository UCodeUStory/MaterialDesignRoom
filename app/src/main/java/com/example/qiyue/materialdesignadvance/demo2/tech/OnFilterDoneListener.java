package com.example.qiyue.materialdesignadvance.demo2.tech;

import java.util.List;

/**
 * author: baiiu
 * date: on 16/1/21 23:30
 * description:
 */
public interface OnFilterDoneListener {
    void onFilterDone(int position, String positionTitle, String urlValue);

    void onMultiFilterDone(List<String> selectedTags, String selectedSex, String selectedDistance);
}