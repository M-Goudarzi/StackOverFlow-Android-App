package com.example.StackOverFlow_App.View.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.bumptech.glide.Glide;
import com.example.StackOverFlow_App.Other.Constant;
import com.example.StackOverFlow_App.Other.DateHelper;
import com.example.StackOverFlow_App.Other.MarkdownHelper;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.Answer;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.Comment;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.Question;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.QuestionResponse;
import com.example.StackOverFlow_App.R;
import com.example.StackOverFlow_App.View.Adapter.RecAnswerAdapter;
import com.example.StackOverFlow_App.View.Adapter.RecCommentAdapter;
import com.example.StackOverFlow_App.View.Adapter.RecTagQuestionActivityAdapter;
import com.example.StackOverFlow_App.ViewModel.QuestionActivityViewModel;
import com.example.StackOverFlow_App.databinding.ActivityQuestionBinding;

import java.util.ArrayList;
import io.noties.markwon.Markwon;
import io.noties.markwon.html.HtmlPlugin;
import io.noties.markwon.image.glide.GlideImagesPlugin;

public class QuestionActivity extends AppCompatActivity {

    private static final String TAG = "QuestionActivity";
    private Markwon markwon;
    private ActivityQuestionBinding binding;
    private QuestionActivityViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Question question;

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
        markwon = Markwon.builder(this)
                .usePlugin(HtmlPlugin.create())
                .usePlugin(GlideImagesPlugin.create(this))
                .build();
        swipeRefreshLayout = binding.swipeRefreshQuestionActivity;
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(refreshListener);
        binding.ivUserAvatarQuestionActivity.setOnClickListener(userProfileClickListener);
        binding.tvUserNameQuestionActivity.setOnClickListener(userProfileClickListener);
        binding.arrowBackQuestionActivity.setOnClickListener(v -> {
            finish();
            overridePendingTransition(0, 0);
        });
        binding.shareLinkQuestionActivity.setOnClickListener(v -> {
            if (question != null && question.getLink() != null) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,question.getLink());
                startActivity(Intent.createChooser(intent,"Share"));
            }
        });
        binding.openInBrowserQuestionActivity.setOnClickListener(v -> {
            if (question != null && question.getLink() != null) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(question.getLink()));
                startActivity(intent);
            }

        });
    }

    private void updateUi(Question question) {
        binding.tvToolbarQuestionActivity.setText(question.getUpVoteCount() + " votes");
        markwon.setMarkdown(binding.tvTitleQuestionActivity, question.getTitle());
        binding.tvAskDateQuestionActivity.setText("Asked : " + DateHelper.getCreationDate(question.getCreationDate()));
        binding.tvViewsCountQuestionActivity.setText("Viewed " + question.getViewCount() + " times");
        markwon.setMarkdown(binding.tvBodyQuestionActivity,new MarkdownHelper().handleSpecialChars(question.getBodyMarkdown()));
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

    private String getQuestionId() {
        Intent intent = getIntent();
        return intent.getStringExtra(Constant.questionIdIntentExtraName);
    }

    private final androidx.lifecycle.Observer<QuestionResponse> observer = questionResponse -> {
        runOnUiThread(() -> {
            question = questionResponse.getQuestions().get(0);
            updateUi(question);
            swipeRefreshLayout.setRefreshing(false);
        });
    };

    private final SwipeRefreshLayout.OnRefreshListener refreshListener = () -> {
        viewModel.retryQuestionFetch(getLifecycle())
                .observe(this,observer);
    };

    private final View.OnClickListener userProfileClickListener = v -> {
        if (question != null) {
            Intent intent = new Intent(this,UserProfileActivity.class);
            intent.putExtra(Constant.userIdIntentExtraName,question.getOwner().getUserId());
            startActivity(intent);
        }
    };

}