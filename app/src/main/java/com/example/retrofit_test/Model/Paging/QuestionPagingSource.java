package com.example.retrofit_test.Model.Paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.Model.Networking.ModelObject.QuestionResponse;
import com.example.retrofit_test.Model.Networking.StackExchangeApi;
import com.example.retrofit_test.ViewModel.QuestionFragmentViewModel;
import com.example.retrofit_test.Common.QuestionsState;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class QuestionPagingSource extends RxPagingSource<Integer, Question> {

    private Integer page;
    private final StackExchangeApi api;
    private final QuestionFragmentViewModel viewModel;

    public QuestionPagingSource(StackExchangeApi api, ViewModelStoreOwner viewModelStoreOwner) {
        this.api = api;
        this.viewModel = new ViewModelProvider(viewModelStoreOwner).get(QuestionFragmentViewModel.class);
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, Question>> loadSingle(@NonNull LoadParams<Integer> loadParams) {

        page = loadParams.getKey();
        if (page == null)
            page = 1;

        Single<QuestionResponse> questionSingle;
        switch (viewModel.getQuestionsState()) {
            case QuestionsState.BOUNTIED:
                questionSingle = api.getBountiedQuestionsWithPaging(viewModel.getQuestionsTags(),page);
                break;
            case QuestionsState.UNANSWERED:
                questionSingle = api.getUnAnsweredQuestionsWithPaging(viewModel.getQuestionsTags(),page);
                break;
            default: {
                questionSingle = api.getNewestQuestionsWithPaging(viewModel.getQuestionsTags(),page);
            }
        }

        return questionSingle
                .subscribeOn(Schedulers.io())
                .map(this::toLoadResult)
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer,Question> toLoadResult(@NonNull QuestionResponse response) {
        return new LoadResult.Page<>(
                response.getQuestions(),
                null,
                page+1,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED
        );
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Question> pagingState) {
        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, Question> anchorPage = pagingState.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }

        return null;
    }

}
