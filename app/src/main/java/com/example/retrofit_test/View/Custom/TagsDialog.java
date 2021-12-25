package com.example.retrofit_test.View.Custom;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.retrofit_test.R;
import com.example.retrofit_test.ViewModel.QuestionFragmentViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class TagsDialog extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "TagsDialog";

    private View view;
    private TagsDialogListener dialogListener;
    private ChipGroup chipGroup;
    private EditText editText;
    private ArrayList<Chip> selectedChips;
    private final ArrayList<String> tags = new ArrayList<>();


    public TagsDialog() {
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity(),R.style.myAlertDialog);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.tags_dialog_layout,null);
        builder.setView(view);
        builder.setPositiveButton("Select", (dialogInterface, i) -> {
            dialogListener.onDialogPositiveClick(getAllSelectedChipsTexts());
            dismiss();
        }).setNeutralButton("Cancel", ((dialogInterface, i) -> dismiss()));

        QuestionFragmentViewModel viewModel = new ViewModelProvider(requireActivity()).get(QuestionFragmentViewModel.class);

        tags.clear();
        tags.addAll(Arrays.asList(viewModel.getQuestionsTags().split(";")));

        if (savedInstanceState != null){
            Log.i(TAG, "onCreateDialog: " + savedInstanceState.getStringArrayList("selectedTags"));
            tags.addAll(savedInstanceState.getStringArrayList("selectedTags"));
        }

        dialogListener = viewModel.getTagsDialogListener();

        init();

        return builder.create();
    }

    private void init() {
        editText = view.findViewById(R.id.edit_text_tags_dialog);
        editText.setOnEditorActionListener(editorActionListener);
        ImageButton imageButton = view.findViewById(R.id.image_button_tags_dialog);
        imageButton.setOnClickListener(imageButtonClickListener);
        selectedChips = new ArrayList<>();
        chipGroup = view.findViewById(R.id.chip_group_tags_dialog);
        Chip pythonChip = view.findViewById(R.id.chip_python_tags_dialog);
        Chip javaChip = view.findViewById(R.id.chip_java_tags_dialog);
        Chip csharpChip = view.findViewById(R.id.chip_csharp_tags_dialog);
        Chip phpChip = view.findViewById(R.id.chip_php_tags_dialog);
        Chip androidChip = view.findViewById(R.id.chip_android_tags_dialog);
        Chip htmlChip = view.findViewById(R.id.chip_html_tags_dialog);
        Chip jqueryChip = view.findViewById(R.id.chip_jquery_tags_dialog);
        Chip cppChip = view.findViewById(R.id.chip_cpp_tags_dialog);
        Chip cssChip = view.findViewById(R.id.chip_css_tags_dialog);
        Chip iosChip = view.findViewById(R.id.chip_ios_tags_dialog);
        Chip mysqlChip = view.findViewById(R.id.chip_mysql_tags_dialog);
        Chip sqlChip = view.findViewById(R.id.chip_sql_tags_dialog);
        Chip rChip = view.findViewById(R.id.chip_r_tags_dialog);
        Chip nodejsChip = view.findViewById(R.id.chip_node_js_tags_dialog);
        Chip arraysChip = view.findViewById(R.id.chip_arrays_tags_dialog);
        Chip cChip = view.findViewById(R.id.chip_c_tags_dialog);
        Chip aspnetChip = view.findViewById(R.id.chip_asp_net_tags_dialog);
        Chip reactjsChip = view.findViewById(R.id.chip_reactjs_tags_dialog);
        Chip rubyonrailsChip = view.findViewById(R.id.chip_ruby_on_rails_tags_dialog);
        Chip javascriptChip = view.findViewById(R.id.chip_javascript_tags_dialog);
        Chip netChip = view.findViewById(R.id.chip_dot_net_tags_dialog);
        Chip sqlserverChip = view.findViewById(R.id.chip_sql_server_tags_dialog);
        Chip swiftChip = view.findViewById(R.id.chip_swift_tags_dialog);
        Chip python3xChip = view.findViewById(R.id.chip_python_3_x_tags_dialog);
        Chip objectiveChip = view.findViewById(R.id.chip_objective_c_tags_dialog);
        Chip djangoChip = view.findViewById(R.id.chip_django_tags_dialog);
        Chip angularChip = view.findViewById(R.id.chip_angular_tags_dialog);
        Chip angularjsChip = view.findViewById(R.id.chip_angularjs_tags_dialog);
        Chip excelChip = view.findViewById(R.id.chip_excel_tags_dialog);
        Chip jsonChip = view.findViewById(R.id.chip_json_tags_dialog);

        pythonChip.setOnClickListener(this);
        javaChip.setOnClickListener(this);
        csharpChip.setOnClickListener(this);
        phpChip.setOnClickListener(this);
        androidChip.setOnClickListener(this);
        htmlChip.setOnClickListener(this);
        jqueryChip.setOnClickListener(this);
        cppChip.setOnClickListener(this);
        cssChip.setOnClickListener(this);
        iosChip.setOnClickListener(this);
        mysqlChip.setOnClickListener(this);
        sqlChip.setOnClickListener(this);
        rChip.setOnClickListener(this);
        nodejsChip.setOnClickListener(this);
        arraysChip.setOnClickListener(this);
        cChip.setOnClickListener(this);
        aspnetChip.setOnClickListener(this);
        reactjsChip.setOnClickListener(this);
        rubyonrailsChip.setOnClickListener(this);
        javascriptChip.setOnClickListener(this);
        netChip.setOnClickListener(this);
        sqlserverChip.setOnClickListener(this);
        swiftChip.setOnClickListener(this);
        python3xChip.setOnClickListener(this);
        objectiveChip.setOnClickListener(this);
        djangoChip.setOnClickListener(this);
        angularChip.setOnClickListener(this);
        angularjsChip.setOnClickListener(this);
        excelChip.setOnClickListener(this);
        jsonChip.setOnClickListener(this);

        addTagsToSelectedList();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.chip_python_tags_dialog) {
            addChipToChipGroup(getString(R.string.python));
        }
        else if (view.getId() == R.id.chip_java_tags_dialog ) {
            addChipToChipGroup(getString(R.string.java));
        }
        else if (view.getId() == R.id.chip_csharp_tags_dialog ) {
            addChipToChipGroup(getString(R.string.csharp));
        }
        else if (view.getId() == R.id.chip_php_tags_dialog) {
            addChipToChipGroup(getString(R.string.php));
        }
        else if (view.getId() == R.id.chip_android_tags_dialog) {
            addChipToChipGroup(getString(R.string.android));
        }
        else if (view.getId() == R.id.chip_html_tags_dialog) {
            addChipToChipGroup(getString(R.string.html));
        }
        else if (view.getId() == R.id.chip_jquery_tags_dialog) {
            addChipToChipGroup(getString(R.string.jquery));
        }
        else if (view.getId() == R.id.chip_cpp_tags_dialog) {
            addChipToChipGroup(getString(R.string.cpp));
        }
        else if (view.getId() == R.id.chip_css_tags_dialog) {
            addChipToChipGroup(getString(R.string.css));
        }
        else if (view.getId() == R.id.chip_ios_tags_dialog) {
            addChipToChipGroup(getString(R.string.ios));
        }
        else if (view.getId() == R.id.chip_mysql_tags_dialog) {
            addChipToChipGroup(getString(R.string.mysql));
        }
        else if (view.getId() == R.id.chip_sql_tags_dialog) {
            addChipToChipGroup(getString(R.string.sql));
        }
        else if ( view.getId() == R.id.chip_r_tags_dialog) {
            addChipToChipGroup(getString(R.string.r));
        }
        else if (view.getId() == R.id.chip_node_js_tags_dialog) {
            addChipToChipGroup(getString(R.string.node_js));
        }
        else if (view.getId() == R.id.chip_arrays_tags_dialog) {
            addChipToChipGroup(getString(R.string.arrays));
        }
        else if (view.getId() == R.id.chip_c_tags_dialog) {
            addChipToChipGroup(getString(R.string.c));
        }
        else if (view.getId() == R.id.chip_asp_net_tags_dialog) {
            addChipToChipGroup(getString(R.string.asp_net));
        }
        else if (view.getId() == R.id.chip_reactjs_tags_dialog) {
            addChipToChipGroup(getString(R.string.reactjs));
        }
        else if (view.getId() == R.id.chip_ruby_on_rails_tags_dialog) {
            addChipToChipGroup(getString(R.string.ruby_on_rails));
        }
        else if (view.getId() == R.id.chip_javascript_tags_dialog) {
            addChipToChipGroup(getString(R.string.javascript));
        }
        else if (view.getId() == R.id.chip_dot_net_tags_dialog) {
            addChipToChipGroup(getString(R.string.net));
        }
        else if (view.getId() == R.id.chip_sql_server_tags_dialog) {
            addChipToChipGroup(getString(R.string.sql_server));
        }
        else if (view.getId() == R.id.chip_swift_tags_dialog) {
            addChipToChipGroup(getString(R.string.swift));
        }
        else if (view.getId() == R.id.chip_python_3_x_tags_dialog) {
            addChipToChipGroup(getString(R.string.python_3_x));
        }
        else if (view.getId() == R.id.chip_objective_c_tags_dialog) {
            addChipToChipGroup(getString(R.string.objective_c));
        }
        else if (view.getId() == R.id.chip_django_tags_dialog) {
            addChipToChipGroup(getString(R.string.django));
        }
        else if (view.getId() == R.id.chip_angular_tags_dialog) {
            addChipToChipGroup(getString(R.string.angular));
        }
        else if (view.getId() == R.id.chip_angularjs_tags_dialog) {
            addChipToChipGroup(getString(R.string.angularjs));
        }
        else if (view.getId() == R.id.chip_excel_tags_dialog) {
            addChipToChipGroup(getString(R.string.excel));
        }
        else if (view.getId() == R.id.chip_json_tags_dialog) {
            addChipToChipGroup(getString(R.string.json));
        }
    }

    private void addChipToChipGroup(String chipText) {
        if (chipIsAlreadyAddedToSelectedListOrChipTextIsEmpty(chipText))
            return;
        chipGroup.addView(createNewChipAndAddToSelectedList(chipText));
    }

    private Boolean chipIsAlreadyAddedToSelectedListOrChipTextIsEmpty(String chipText) {
        if (chipText.equals(""))
            return true;
        for (Chip chip : selectedChips) {
            if (chip.getText().toString().toLowerCase(Locale.ROOT).equals(chipText.toLowerCase(Locale.ROOT)))
                return true;
        }
        return false;
    }

    private Chip createNewChipAndAddToSelectedList(String chipText) {
        Chip chip = new Chip(requireContext());
        ViewGroup.LayoutParams layoutParams =  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        chip.setLayoutParams(layoutParams);
        chip.setText(chipText);
        ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(requireContext(),null,0,R.style.tagsDialogSelectedChip);
        chip.setChipDrawable(chipDrawable);
        int chipId = View.generateViewId();
        chip.setId(chipId);
        chip.setCheckable(false);

        addChipToSelectedListAndSetClickListener(chip);

        return chip;
    }

    private void addChipToSelectedListAndSetClickListener(Chip chip) {
        selectedChips.add(chip);
        chip.setOnClickListener(selectedChipsClickListener);
    }

    private final View.OnClickListener selectedChipsClickListener = view -> {
        for (Chip chip : selectedChips){
            if (chip.getId() == view.getId()){
                selectedChips.remove(chip);
                chipGroup.removeView(chip);
                return;
            }
        }
    };

    private final View.OnClickListener imageButtonClickListener = view -> performEditTextDone();


    private final TextView.OnEditorActionListener editorActionListener = (textView, i, keyEvent) -> {
        if (i == EditorInfo.IME_ACTION_DONE) {
            performEditTextDone();
            return true;
        }
        return false;
    };

    private void performEditTextDone(){
        if (editText.getText().toString().isEmpty())
            return;
        addChipToChipGroup(String.valueOf(editText.getText()));
        editText.setText("");
    }

    private ArrayList<String> getAllSelectedChipsTexts() {
        tags.clear();
        for (Chip chip : selectedChips){
            tags.add(chip.getText().toString());
        }
        return tags;
    }

    public interface TagsDialogListener {
        void onDialogPositiveClick(ArrayList<String> tags);
    }

    private void addTagsToSelectedList() {
        for (String tag : this.tags)
        addChipToChipGroup(tag);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //save selected but not clicked on select button
        //will retrieve back after configuration changes

        ArrayList<String> tags = new ArrayList<>();

        for (int i = 0; i < chipGroup.getChildCount();i++){
            tags.add(String.valueOf(((Chip) chipGroup.getChildAt(i)).getText()));
        }
        outState.putStringArrayList("selectedTags",tags);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (((AlertDialog) getDialog()) != null) {
            //if night mode set dialog buttons color to white unless black
            int nightModeFlags = requireContext().getResources()
                    .getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            int color;
            if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    color = requireContext().getColor(R.color.white);
                else
                    color = ContextCompat.getColor(requireContext(), R.color.white);
            }
            else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    color = requireContext().getColor(R.color.myBlack);
                else
                    color = ContextCompat.getColor(requireContext(), R.color.myBlack);
            }

            ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(color);
            ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_NEUTRAL)
                    .setTextColor(color);


        }
    }
}
