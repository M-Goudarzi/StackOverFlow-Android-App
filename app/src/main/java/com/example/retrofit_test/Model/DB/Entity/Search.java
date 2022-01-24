package com.example.retrofit_test.Model.DB.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Search {

    @PrimaryKey(autoGenerate = true)
    public int uId;
    @ColumnInfo(name = "has_accepted")
    public boolean hasAccepted;
    @ColumnInfo(name = "is_closed")
    public boolean closed;
    @ColumnInfo(name = "minimum_answers")
    public int minimumAnswers;
    @ColumnInfo(name = "title_contains")
    public String titleContains;
    @ColumnInfo(name = "body_contains")
    public String bodyContains;
    @ColumnInfo(name = "tags")
    public String tags;
    @ColumnInfo(name = "search_query")
    public String query;

    public int getuId() {
        return uId;
    }

    public boolean isHasAccepted() {
        return hasAccepted;
    }

    public void setHasAccepted(boolean hasAccepted) {
        this.hasAccepted = hasAccepted;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getMinimumAnswers() {
        return minimumAnswers;
    }

    public void setMinimumAnswers(int minimumAnswers) {
        this.minimumAnswers = minimumAnswers;
    }

    public String getTitleContains() {
        return titleContains;
    }

    public void setTitleContains(String titleContains) {
        this.titleContains = titleContains;
    }

    public String getBodyContains() {
        return bodyContains;
    }

    public void setBodyContains(String bodyContains) {
        this.bodyContains = bodyContains;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Search search = (Search) o;
        return uId == search.uId && hasAccepted == search.hasAccepted && closed == search.closed && minimumAnswers == search.minimumAnswers && Objects.equals(titleContains, search.titleContains) && Objects.equals(bodyContains, search.bodyContains) && Objects.equals(tags, search.tags) && Objects.equals(query, search.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uId, hasAccepted, closed, minimumAnswers, titleContains, bodyContains, tags, query);
    }
}
