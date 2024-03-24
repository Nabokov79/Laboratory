package ru.nabokovsg.result.models.builders;

public class SearchParametersBuilder {

    private final Long taskJournalId;
    private final Long equipmentId;
    private final Boolean full;

    public SearchParametersBuilder(SearchParameters parameters) {
        this.taskJournalId = parameters.taskJournalId;
        this.equipmentId = parameters.equipmentId;
        this.full = parameters.full;
    }

    public Long getTaskJournalId() {
        return taskJournalId;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public Boolean getFull() {
        return full;
    }

    public static class SearchParameters {

        private Long taskJournalId;
        private Long equipmentId;
        private Boolean full;

        public SearchParameters taskJournalId(Long taskJournalId) {
            this.taskJournalId = taskJournalId;
            return this;
        }

        public SearchParameters equipmentId(Long equipmentId) {
            this.equipmentId = equipmentId;
            return this;
        }

        public SearchParameters full(Boolean full) {
            this.full = full;
            return this;
        }

        public SearchParametersBuilder build() {
            return new SearchParametersBuilder(this);
        }
    }
}
