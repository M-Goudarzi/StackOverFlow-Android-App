package com.example.StackOverFlow_App.View.Custom;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.StackOverFlow_App.ViewModel.QuestionFragmentViewModel;
import java.util.Arrays;
import java.util.List;

public class QuestionTagsDialog extends TagsDialog {

    public QuestionTagsDialog() {
        super();
    }

    @Override
    protected List<String> fillUpTagsList() {
        QuestionFragmentViewModel viewModel = new ViewModelProvider(requireActivity()).get(QuestionFragmentViewModel.class);
        return Arrays.asList(viewModel.getTags().split(";"));
    }

    @Override
    protected void setUpFragmentResult(Bundle bundle) {
        QuestionFragmentViewModel viewModel = new ViewModelProvider(requireActivity()).get(QuestionFragmentViewModel.class);
        viewModel.getTagsDialogListener().onDialogPositiveClick(bundle);
    }

}
