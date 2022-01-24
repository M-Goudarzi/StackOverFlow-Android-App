package com.example.retrofit_test.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.retrofit_test.Common.QuestionSearchFilter;
import com.example.retrofit_test.Common.TagsChipHelper;
import com.example.retrofit_test.Common.ThreadPoolManager;
import com.example.retrofit_test.Model.DB.AppDataBase;
import com.example.retrofit_test.Model.DB.Entity.Search;
import com.example.retrofit_test.Model.DB.SearchDAO;
import com.example.retrofit_test.View.Custom.TagsDialog;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

public class SearchFragmentViewModel extends AndroidViewModel{

    private static final String TAG = "SearchFragmentViewModel";

    private TagsDialog.TagsDialogListener tagsDialogListener;
    private final QuestionSearchFilter searchFilter;
    private final SearchDAO dao;
    private LiveData<List<Search>> searchHistoryLiveData = new MutableLiveData<>();
    private final Executor executor;
    private final TagsChipHelper tagsChipHelper;

    public SearchFragmentViewModel(Application application) {
        super(application);
        AppDataBase dataBase = AppDataBase.getInstance(application);
        tagsChipHelper = new TagsChipHelper(application);
        dao = dataBase.getDao();
        searchFilter = new QuestionSearchFilter();
        executor = ThreadPoolManager.getInstance().executorService;
    }

    public LiveData<List<Search>> getSearchHistory() {
        if (searchHistoryLiveData.getValue() == null || searchHistoryLiveData.getValue().isEmpty()){
            searchHistoryLiveData = dao.getAll();
        }
        return searchHistoryLiveData;
    }

    public void insertSearch(String query) {
        Search search = new Search();
        search.setTags(getTags());
        search.setQuery(query);
        search.setTitleContains(getTitleContains());
        search.setBodyContains(getBodyContains());
        search.setMinimumAnswers(getMinimumAnswers());
        search.setHasAccepted(getHasAccepted());
        search.setClosed(isClosed());
        executor.execute(() -> {
            dao.insert(search);
        });
    }


    public void setTags(String tags) {
        searchFilter.setTags(tags);
    }

    public boolean getHasAccepted() {
        return searchFilter.getHasAccepted();
    }

    public void setHasAccepted(boolean hasAccepted) {
        searchFilter.setHasAccepted(hasAccepted);
    }

    public boolean isClosed() {
        return searchFilter.isClosed();
    }

    public void setClosed(boolean closed) {
        searchFilter.setClosed(closed);
    }

    public int getMinimumAnswers() {
        return searchFilter.getMinimumAnswers();
    }

    public void setMinimumAnswers(int minimumAnswers) {
        searchFilter.setMinimumAnswers(minimumAnswers);
    }

    public String getTitleContains() {
        return (searchFilter.getTitleContains() != null) ? searchFilter.getTitleContains() : "";
    }

    public void setTitleContains(String titleContains) {
        searchFilter.setTitleContains(titleContains);
    }

    public String getBodyContains() {
        return (searchFilter.getBodyContains() != null) ? searchFilter.getBodyContains() : "";
    }

    public void setBodyContains(String bodyContains) {
        searchFilter.setBodyContains(bodyContains);
    }

    public String getTags() {
        return (searchFilter.getTags() != null) ? searchFilter.getTags() : "";
    }

    public void removeATag(String tag) {
        List<String> tags = new LinkedList<>(tagsChipHelper.convertTagsStringToList(searchFilter.getTags()));
        for (Iterator<String> iterator = tags.iterator(); iterator.hasNext(); ) {
            String str = iterator.next();
            if (str.equals(tag)) {
                iterator.remove();
                break;
            }
        }
        String tagsString = tagsChipHelper.convertTagsListToString(tags);
        searchFilter.setTags(tagsString);
    }

    public TagsDialog.TagsDialogListener getTagsDialogListener() {
        return tagsDialogListener;
    }

    public void setTagsDialogListener(TagsDialog.TagsDialogListener tagsDialogListener) {
        this.tagsDialogListener = tagsDialogListener;
    }

}
