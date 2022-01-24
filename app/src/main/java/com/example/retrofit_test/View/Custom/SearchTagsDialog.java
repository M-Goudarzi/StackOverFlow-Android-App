package com.example.retrofit_test.View.Custom;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.retrofit_test.ViewModel.SearchFragmentViewModel;
import java.util.Arrays;
import java.util.List;

public class SearchTagsDialog extends TagsDialog{

    public SearchTagsDialog() {
        super();
    }

    @Override
    protected List<String> fillUpTagsList() {
        SearchFragmentViewModel viewModel = new ViewModelProvider(requireActivity()).get(SearchFragmentViewModel.class);
        return Arrays.asList(viewModel.getTags().split(";"));
    }

    @Override
    protected void setUpFragmentResult(Bundle bundle) {
        SearchFragmentViewModel viewModel = new ViewModelProvider(requireActivity()).get(SearchFragmentViewModel.class);
        viewModel.getTagsDialogListener().onDialogPositiveClick(bundle);
    }
}
