package com.revaldoputra.movieapp.model;

import androidx.annotation.NonNull;

import java.util.List;

public class MovieModel {
    private Integer totalResults;
    private List<Result> results;

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public static class Result {
        private  Integer id;
        private String title;
        private String overview;
        private String poster_path;
        private String backdrop_path;
        private String releaseDate;
        private Double voteAverage;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }
        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }
        public void setTitle(String title) {
            this.title = title;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public Double getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(Double voteAverage) {
            this.voteAverage = voteAverage;
        }

        @NonNull
        @Override
        public String toString() {
            return "Result{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", overview='" + overview + '\'' +
                    ", poster_path='" + poster_path + '\'' +
                    ", backdrop_path='" + backdrop_path + '\'' +
                    ", release_date='" + releaseDate + '\'' +
                    ", voteAverage=" + voteAverage +
                    '}';
        }
    }
}
