package com.example.StackOverFlow_App.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.StackOverFlow_App.Other.Constant;
import com.example.StackOverFlow_App.Other.DateHelper;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.Owner;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.Question;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.UserWithQuestions;
import com.example.StackOverFlow_App.R;
import com.example.StackOverFlow_App.View.Adapter.RecQuestionAdapter;
import com.example.StackOverFlow_App.ViewModel.UserProfileActivityViewModel;
import com.example.StackOverFlow_App.databinding.ActivityUserProfileBinding;

import java.util.ArrayList;

import io.noties.markwon.Markwon;
import io.noties.markwon.html.HtmlPlugin;

public class UserProfileActivity extends AppCompatActivity {

    private ActivityUserProfileBinding binding;
    private UserProfileActivityViewModel viewModel;
    private Owner owner;
    private final ArrayList<Question> questions = new ArrayList<>();
    private Markwon markwon;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        int userId = getUserId();

        init();
        binding.appBarLayout.setExpanded(false);

        viewModel.getUser(String.valueOf(userId), getLifecycle(), e -> {
            runOnUiThread(() -> {
                swipeRefreshLayout.setRefreshing(false);
                binding.tvErrorMessageUserProfile.setText(e.getMessage());
                binding.tvErrorMessageUserProfile.setVisibility(View.VISIBLE);
            });
        }).observe(this,observer);


    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(UserProfileActivityViewModel.class);
        markwon = Markwon.builder(this)
                .usePlugin(HtmlPlugin.create())
                .build();
        swipeRefreshLayout = binding.swipeRefreshUserProfile;
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(refreshListener);
        binding.arrowBackUserProfile.setOnClickListener(v -> {
            finish();
            overridePendingTransition(0,0);
        });
        binding.shareLinkUserProfile.setOnClickListener(v -> {
            if (owner != null && owner.getLink() != null) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,owner.getLink());
                startActivity(Intent.createChooser(intent,"Share"));
            }
        });
        binding.openInBrowserUserProfile.setOnClickListener(v -> {
            if (owner != null && owner.getLink() != null) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(owner.getLink()));
                startActivity(intent);
            }

        });
    }

    private int getUserId() {
        Intent intent = getIntent();
        return intent.getIntExtra(Constant.userIdIntentExtraName,-1);
    }

    private final Observer<UserWithQuestions> observer = userWithQuestions -> {
        runOnUiThread(() -> {
            owner = userWithQuestions.getUserResponse().getOwners().get(0);
            questions.clear();
            questions.addAll(userWithQuestions.getQuestionResponse().getQuestions());
            updateUi(owner,questions);
            binding.tvErrorMessageUserProfile.setVisibility(View.GONE);
            binding.appBarLayout.setExpanded(true,true);
            swipeRefreshLayout.setRefreshing(false);
        });
    };

    private void updateUi(Owner owner, ArrayList<Question> questions) {
        binding.tvToolbarUserProfile.setText(owner.getDisplayName());
        Glide.with(this).load(owner.getProfileImage())
                .placeholder(ResourcesCompat.getDrawable(getResources(), R.drawable.account_circle_24,getTheme()))
                .into(binding.ivUserAvatarUserProfile);
        binding.goldenBadgeCircle.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.circle_golden_background,getTheme()));
        binding.silverBadgeCircle.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.circle_silver_background,getTheme()));
        binding.bronzeBadgeCircle.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.circle_bronze_background,getTheme()));
        binding.tvUserGoldenBadgeUserProfile.setText(owner.getBadgeCounts().getGold() + "");
        binding.tvUserSilverBadgeUserProfile.setText(owner.getBadgeCounts().getSilver() + "");
        binding.tvUserBronzeBadgeUserProfile.setText(owner.getBadgeCounts().getBronze() + "");
        if (owner.getLocation() == null) {
            binding.tvLocationUserProfile.setVisibility(View.GONE);
        }
        else {
            binding.tvLocationUserProfile.setVisibility(View.VISIBLE);
            binding.tvLocationUserProfile.setText(owner.getLocation());
        }
        binding.tvDateUserProfile.setText("Joined " + DateHelper.getCreationDate(owner.getCreationDate()));
        if (owner.getWebSiteUrl() == null) {
            binding.tvWebsiteUserProfile.setVisibility(View.GONE);
        }
        else {
            binding.tvWebsiteUserProfile.setVisibility(View.VISIBLE);
            binding.tvWebsiteUserProfile.setText(owner.getWebSiteUrl());
        }
        if (owner.getAboutMe() == null) {
            binding.tvAboutMeLabelUserProfile.setVisibility(View.GONE);
            binding.tvAboutMeUserProfile.setVisibility(View.GONE);
        }
        else {
            binding.tvAboutMeLabelUserProfile.setVisibility(View.VISIBLE);
            binding.tvAboutMeUserProfile.setVisibility(View.VISIBLE);
            markwon.setMarkdown(binding.tvAboutMeUserProfile,owner.getAboutMe());
        }
        if (questions.isEmpty()) {
            binding.tvQuestionsLabelUserProfile.setVisibility(View.GONE);
            binding.recQuestionUserProfile.setVisibility(View.GONE);
        }
        else {
            binding.tvQuestionsLabelUserProfile.setVisibility(View.VISIBLE);
            binding.recQuestionUserProfile.setVisibility(View.VISIBLE);
            binding.recQuestionUserProfile.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
            binding.recQuestionUserProfile.setAdapter(new RecQuestionAdapter(questions,markwon,question -> {
                Intent intent = new Intent(this, QuestionActivity.class);
                intent.putExtra(Constant.questionIdIntentExtraName,question.getQuestionId());
                startActivity(intent);
            }));

        }

    }

    private final SwipeRefreshLayout.OnRefreshListener refreshListener = () -> {
        viewModel.retry(getLifecycle())
                .observe(this,observer);
    };

}