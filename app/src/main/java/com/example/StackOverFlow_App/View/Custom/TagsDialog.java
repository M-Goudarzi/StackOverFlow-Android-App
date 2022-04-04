package com.example.StackOverFlow_App.View.Custom;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.example.StackOverFlow_App.Other.Constant;
import com.example.StackOverFlow_App.Other.TagsChipHelper;
import com.example.StackOverFlow_App.R;
import com.example.StackOverFlow_App.databinding.TagsDialogLayoutBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class TagsDialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG = "TagsDialog";

    private ChipGroup chipGroup;
    private EditText editText;
    private ArrayList<Chip> selectedChips;
    private final ArrayList<String> tagsList = new ArrayList<>();
    private TagsChipHelper tagsChipHelper;
    private TagsDialogLayoutBinding binding;


    public TagsDialog() {
    }

    protected abstract List<String> fillUpTagsList();
    protected abstract void setUpFragmentResult(Bundle bundle);

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity(),R.style.myAlertDialog);
        binding = TagsDialogLayoutBinding.inflate(LayoutInflater.from(requireContext()));
        View view = binding.getRoot();
        builder.setView(view);
        builder.setPositiveButton("Select", (dialogInterface, i) -> {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(Constant.tagsListBundleKey,getAllSelectedChipsTexts());
            setUpFragmentResult(bundle);
            dismiss();
        }).setNeutralButton("Cancel", ((dialogInterface, i) -> dismiss()));

        tagsList.clear();
        tagsList.addAll(fillUpTagsList());

        if (savedInstanceState != null){
            tagsList.addAll(savedInstanceState.getStringArrayList(Constant.selectedTagsBundleKey));
        }

        init();

        return builder.create();
    }


    private void init() {
        tagsChipHelper = new TagsChipHelper(requireContext());
        editText = binding.editTextTagsDialog;
        editText.setOnEditorActionListener(editorActionListener);
        ImageButton imageButton = binding.imageButtonTagsDialog;
        imageButton.setOnClickListener(imageButtonClickListener);
        selectedChips = new ArrayList<>();
        chipGroup = binding.chipGroupTagsDialog;
        Chip pythonChip = binding.chipPythonTagsDialog;
        Chip javaChip = binding.chipJavaTagsDialog;
        Chip csharpChip = binding.chipCsharpTagsDialog;
        Chip phpChip = binding.chipPhpTagsDialog;
        Chip androidChip = binding.chipAndroidTagsDialog;
        Chip htmlChip = binding.chipHtmlTagsDialog;
        Chip jqueryChip = binding.chipJqueryTagsDialog;
        Chip cppChip = binding.chipCppTagsDialog;
        Chip cssChip = binding.chipCssTagsDialog;
        Chip iosChip = binding.chipIosTagsDialog;
        Chip mysqlChip = binding.chipMysqlTagsDialog;
        Chip sqlChip = binding.chipSqlTagsDialog;
        Chip rChip = binding.chipRTagsDialog;
        Chip nodejsChip = binding.chipNodeJsTagsDialog;
        Chip arraysChip = binding.chipArraysTagsDialog;
        Chip cChip = binding.chipCTagsDialog;
        Chip aspnetChip = binding.chipAspNetTagsDialog;
        Chip reactjsChip = binding.chipReactjsTagsDialog;
        Chip rubyonrailsChip = binding.chipRubyOnRailsTagsDialog;
        Chip javascriptChip = binding.chipJavascriptTagsDialog;
        Chip netChip = binding.chipDotNetTagsDialog;
        Chip sqlserverChip = binding.chipSqlServerTagsDialog;
        Chip swiftChip = binding.chipSwiftTagsDialog;
        Chip python3xChip = binding.chipPython3XTagsDialog;
        Chip objectiveChip = binding.chipObjectiveCTagsDialog;
        Chip djangoChip = binding.chipDjangoTagsDialog;
        Chip angularChip = binding.chipAngularTagsDialog;
        Chip angularjsChip = binding.chipAngularjsTagsDialog;
        Chip excelChip = binding.chipExcelTagsDialog;
        Chip jsonChip = binding.chipJsonTagsDialog;

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
        Chip chip = tagsChipHelper.createChip(chipText);
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
        tagsList.clear();
        for (Chip chip : selectedChips){
            tagsList.add(chip.getText().toString());
        }
        return tagsList;
    }

    public interface TagsDialogListener {
        void onDialogPositiveClick(Bundle tags);
    }

    private void addTagsToSelectedList() {
        for (String tag : this.tagsList)
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
        outState.putStringArrayList(Constant.selectedTagsBundleKey,tags);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
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
