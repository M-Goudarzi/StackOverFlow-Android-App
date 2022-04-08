package com.example.StackOverFlow_App.Other;

public class Constant {

    public static final String stackExchangeApiBaseUrl = "https://api.stackexchange.com/2.3/";
    public static final String sourceCodeGithubUrl = "https://github.com/M-Goudarzi/StackOverFlow-Android-App";
    public static final String stackExchangeApiWebSiteUrl = "https://api.stackexchange.com/";

    public static final String searchDataBaseName = "search_database";

    public static final String getNewestQuestionsWithPagingEndPointUrl = "questions?order=desc&sort=activity&site=stackoverflow&pagesize=20&filter=!)rp_N76JL2.c(stRx_hf";
    public static final String getBountiedQuestionsWithPagingEndPointUrl = "questions/featured?order=desc&sort=activity&site=stackoverflow&pagesize=20&filter=!)rp_N76JL2.c(stRx_hf";
    public static final String getUnAnsweredQuestionsWithPagingEndPointUrl = "questions/unanswered?order=desc&sort=activity&site=stackoverflow&pagesize=20&filter=!)rp_N76JL2.c(stRx_hf";
    public static final String searchEndPointUrl = "/2.3/search/advanced?pagesize=20&order=desc&sort=activity&site=stackoverflow&filter=!)rp_N76JL2.c(stRx_hf";
    public static final String getQuestionByIdEndPointUrl = "questions/{id}?order=desc&sort=activity&site=stackoverflow&filter=!)OS2treGqbOpL7cr-eYBTAD2yY5iompxkGw(P6g1NqtXqOKXr0Lvg81GKU-YuPAHfUoygj";
    public static final String getUserByIdEndPointUrl = "users/{id}?order=desc&sort=reputation&site=stackoverflow&filter=!Ln3laW6gn3E)ssLpSyl9Ir";
    public static final String getQuestionsOfUserByIdEndPointUrl = "users/{id}/questions?order=desc&sort=activity&site=stackoverflow&filter=!4)Lbteely(YAdQsz8";

    public static final String questionFragmentTag = "Question";
    public static final String searchFragmentTag = "Search";

    public static final String activeFragmentBundleKey = "ActiveFragment";
    public static final String tagsListBundleKey = "tagsList";
    public static final String selectedTagsBundleKey = "selectedTags";
    public static final String CheckedChipBundleKey = "CheckedChip";
    public static final String isFilterLayoutVisibleBundleKey = "isFilterLayoutVisible";
    public static final String selectedChipsStaringBundleKey = "selectedChipsStaring";

    public static final String themeDialogTag = "themeDialog";
    public static final String tagsDialogTag = "tagsDialog";

    public static final String questionIdIntentExtraName = "questionId";
    public static final String userIdIntentExtraName = "userId";
    public static final String searchQueryIntentExtraName = "searchQuery";
    public static final String searchTagsIntentExtraName = "searchTags";
    public static final String searchOnlyOneTagIntentExtraName = "searchOnlyOneTag";
    public static final String searchIsAcceptedBoolIntentExtraName = "searchIsAcceptedBool";
    public static final String searchIsClosedBoolIntentExtraName = "searchIsClosedBool";
    public static final String searchNumberOfAnswersIntentExtraName = "searchNumberOfAnswers";
    public static final String searchTitleContainsIntentExtraName = "searchTitleContains";
    public static final String searchBodyContainsIntentExtraName = "searchBodyContains";

    public static final String applicationSharedPreferencesKey = "com.example.StackOverFlow_App.PREFERENCE_FILE_KEY";
    public static final String darkThemeSharedPreferencesKey = "darkTheme";

}
