package com.example.retrofit_test.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.retrofit_test.Model.Networking.ModelObject.Answer;
import com.example.retrofit_test.Model.Networking.ModelObject.Comment;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.Model.Networking.ModelObject.QuestionResponse;
import com.example.retrofit_test.R;
import com.example.retrofit_test.View.Adapter.RecAnswerAdapter;
import com.example.retrofit_test.View.Adapter.RecCommentAdapter;
import com.example.retrofit_test.View.Adapter.RecTagQuestionActivityAdapter;
import com.example.retrofit_test.ViewModel.QuestionActivityViewModel;
import com.example.retrofit_test.databinding.ActivityQuestionBinding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import io.noties.markwon.Markwon;
import io.noties.markwon.html.HtmlPlugin;
import io.noties.markwon.image.glide.GlideImagesPlugin;

public class QuestionActivity extends AppCompatActivity {

    private Map<String, String> specialChars;
    private static final String TAG = "QuestionActivity";
    private Markwon markwon;
    private ActivityQuestionBinding binding;
    private QuestionActivityViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();

        String questionID = getQuestionId();

        viewModel = new ViewModelProvider(this).get(QuestionActivityViewModel.class);
        viewModel.getQuestion(questionID, getLifecycle(), e -> {
            runOnUiThread(() -> {
                binding.tvBodyQuestionActivity.setText(e.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            });
        }).observe(this,observer);

    }

    private void init() {
        fillUpTheSpecialCharsHashMap();
        markwon = Markwon.builder(this)
                .usePlugin(HtmlPlugin.create())
                .usePlugin(GlideImagesPlugin.create(this))
                .build();
        swipeRefreshLayout = binding.swipeRefreshQuestionActivity;
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(refreshListener);
        binding.arrowBackQuestionActivity.setOnClickListener(v -> {
            finish();
            overridePendingTransition(0, 0);
        });
    }

    private void updateUi(Question question) {
        binding.tvToolbarQuestionActivity.setText(question.getUpVoteCount() + " votes");
        markwon.setMarkdown(binding.tvTitleQuestionActivity, question.getTitle());
        binding.tvAskDateQuestionActivity.setText("Asked : " + getCreationTime(question.getCreationDate()));
        binding.tvViewsCountQuestionActivity.setText("Viewed " + question.getViewCount() + " times");
        markwon.setMarkdown(binding.tvBodyQuestionActivity, handleSpecialChars(question.getBodyMarkdown()));
        binding.recTagsQuestionActivity.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.recTagsQuestionActivity.setAdapter(new RecTagQuestionActivityAdapter((ArrayList<String>) question.getTags()));
        binding.tvUserNameQuestionActivity.setText(question.getOwner().getDisplayName());
        Glide.with(this)
                .load(question.getOwner().getProfileImage())
                .placeholder(ResourcesCompat.getDrawable(getResources(), R.drawable.account_circle_24, getTheme()))
                .into(binding.ivUserAvatarQuestionActivity);
        binding.goldenBadgeCircle.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.circle_golden_background,getTheme()));
        binding.silverBadgeCircle.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.circle_silver_background,getTheme()));
        binding.bronzeBadgeCircle.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.circle_bronze_background,getTheme()));
        binding.tvUserGoldenBadgeQuestionActivity.setText(question.getOwner().getBadgeCounts().getGold() + "");
        binding.tvUserSilverBadgeQuestionActivity.setText(question.getOwner().getBadgeCounts().getSilver() + "");
        binding.tvUserBronzeBadgeQuestionActivity.setText(question.getOwner().getBadgeCounts().getBronze() + "");
        binding.tvNumberOfAnswersQuestionActivity.setText(question.getAnswerCount() + " answers");
        if (question.getComments() != null) {
            binding.recCommentsQuestionActivity.setNestedScrollingEnabled(false);
            binding.recCommentsQuestionActivity.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            binding.recCommentsQuestionActivity.setAdapter(new RecCommentAdapter((ArrayList<Comment>) question.getComments(),markwon));
        }
        if (question.getAnswers() != null) {
            binding.recAnswersQuestionActivity.setNestedScrollingEnabled(false);
            binding.recAnswersQuestionActivity.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            binding.recAnswersQuestionActivity.setAdapter(new RecAnswerAdapter
                    ((ArrayList<Answer>) question.getAnswers(),
                            markwon, ResourcesCompat.getDrawable(getResources(),
                            R.drawable.account_circle_24, getTheme())));
        }
    }

    private String getCreationTime(Integer creationDate) {
        LocalDateTime localDateTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        LocalDateTime localDateTime1 = localDateTime.plusSeconds(creationDate);
        LocalDateTime now = LocalDateTime.now();
        if (now.getYear() == localDateTime1.getYear()) {
            if (now.getMonth() == localDateTime1.getMonth()) {
                if (now.getDayOfMonth() == localDateTime1.getDayOfMonth()) {
                    return "Today";
                } else {
                    return (now.getDayOfMonth() - localDateTime1.getDayOfMonth()) + " Days ago";
                }
            } else {
                return (now.getMonth().getValue() - localDateTime1.getMonth().getValue()) + " Months ago";
            }
        } else {
            return (now.getYear() - localDateTime1.getYear()) + " Years ago";
        }
    }

    private void fillUpTheSpecialCharsHashMap() {
        specialChars = new HashMap<>();
        specialChars.put("&lt;", "<");
        specialChars.put("&gt;", ">");
        specialChars.put("&quot;", "\"");
        specialChars.put("&nbsp;", " ");
        specialChars.put("&amp;", "&");
        specialChars.put("&apos;", "'");
        specialChars.put("&#39;", "'");
        specialChars.put("&#40;", "(");
        specialChars.put("&#41;", ")");
        specialChars.put("&#215;", "x");
    }

    private String handleSpecialChars(String text) {
        Set set = specialChars.entrySet();//Converting to Set so that we can traverse
        for (Object o : set) {
            //Converting to Map.Entry so that we can get key and value separately
            Map.Entry entry = (Map.Entry) o;
            text = text.replace(entry.getKey().toString(), entry.getValue().toString());
        }
        return text;
    }


    private String getQuestionId() {
        Intent intent = getIntent();
        return intent.getStringExtra("questionId");
    }

    private final androidx.lifecycle.Observer<QuestionResponse> observer = questionResponse -> {
        Question question = questionResponse.getQuestions().get(0);
        runOnUiThread(() -> {
            updateUi(question);
            swipeRefreshLayout.setRefreshing(false);
        });
    };

    private final SwipeRefreshLayout.OnRefreshListener refreshListener = () -> {
        viewModel.retryQuestionFetch(getLifecycle())
                .observe(this,observer);
    };

}