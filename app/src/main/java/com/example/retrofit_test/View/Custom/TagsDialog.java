package com.example.retrofit_test.View.Custom;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.example.retrofit_test.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;

public class TagsDialog extends DialogFragment implements View.OnClickListener {

    private View view;
    private final TagsDialogListener dialogListener;
    private ChipGroup chipGroup;
    private EditText editText;
    private ArrayList<Chip> selectedChips;

    public TagsDialog(TagsDialogListener listener) {
        dialogListener = listener;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.tags_dialog_layout,null);
        builder.setView(view);

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
        Button selectButton = view.findViewById(R.id.button_select_tags_dialog);
        Button cancelButton = view.findViewById(R.id.button_cancel_tags_dialog);
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

        cancelButton.setOnClickListener(this);
        selectButton.setOnClickListener(this);
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
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_cancel_tags_dialog) {
            dismiss();
        }
        else if (view.getId() == R.id.button_select_tags_dialog){
            dialogListener.onDialogPositiveClick(getAllSelectedChipsTexts());
            dismiss();
        }
        else if (view.getId() == R.id.chip_python_tags_dialog) {
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

    void addChipToChipGroup(String chipText) {
        if (chipIsAlreadyAddedToSelectedList(chipText))
            return;
        chipGroup.addView(createNewChipAndAddToSelectedList(chipText));
    }

    Boolean chipIsAlreadyAddedToSelectedList(String chipText) {
        for (Chip chip : selectedChips) {
            if (chip.getText().equals(chipText))
                return true;
        }
        return false;
    }

    Chip createNewChipAndAddToSelectedList(String chipText) {
        Chip chip = new Chip(getContext());
        ViewGroup.LayoutParams layoutParams =  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        chip.setLayoutParams(layoutParams);
        chip.setText(chipText);
        ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(getContext(),null,0,R.style.Widget_MaterialComponents_Chip_Entry);
        chip.setChipDrawable(chipDrawable);
        int chipId = View.generateViewId();
        chip.setId(chipId);
        chip.setCheckable(false);

        addChipToSelectedListAndSetClickListener(chip);

        return chip;
    }

    void addChipToSelectedListAndSetClickListener(Chip chip) {
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

    void performEditTextDone(){
        if (editText.getText().toString().isEmpty())
            return;
        addChipToChipGroup(String.valueOf(editText.getText()));
        editText.setText("");
    }

    ArrayList<String> getAllSelectedChipsTexts() {
        ArrayList<String> tags = new ArrayList<>();
        for (Chip chip : selectedChips){
            tags.add(chip.getText().toString());
        }
        return tags;
    }

    public interface TagsDialogListener {
        void onDialogPositiveClick(ArrayList<String> tags);
    }

}
