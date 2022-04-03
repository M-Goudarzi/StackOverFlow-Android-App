package com.example.StackOverFlow_App.Common;

// a class to add,remove and control chips inside chip group

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.StackOverFlow_App.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;

import java.util.Arrays;
import java.util.List;

public class TagsChipHelper {


    private final Context context;

    public TagsChipHelper(Context context) {
        this.context = context;
    }

    public String convertTagsListToString(List<String> tagsList) {
        StringBuilder tagsString = new StringBuilder();
        for (String tag : tagsList) {
            if (tagsList.get(0).equals(tag))
                tagsString = new StringBuilder(tag);
            else {
                tagsString.append(";").append(tag);
            }
        }
        return tagsString.toString();
    }

    public List<String> convertTagsStringToList(String tagsString) {
        return Arrays.asList(tagsString.split(";"));
    }

    public Chip createChip(String chipText) {
        Chip chip = new Chip(context);
        ViewGroup.LayoutParams layoutParams =  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        chip.setLayoutParams(layoutParams);
        chip.setText(chipText);
        ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(context,null,0,R.style.tagsDialogSelectedChip);
        chip.setChipDrawable(chipDrawable);
        int chipId = View.generateViewId();
        chip.setId(chipId);
        chip.setCheckable(false);

        return chip;
    }


}
