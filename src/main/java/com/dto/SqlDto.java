package com.dto;

public class SqlDto {
    private String projections;
    private String target;
    private Integer skip;
    private Integer limit;
    private ConditionDto conditionDto;
    private GroupByDto groupByDto;
    private SortDto sortDto;

    public SortDto getSortDto() {
        return sortDto;
    }

    public void setSortDto(SortDto sortDto) {
        this.sortDto = sortDto;
    }

    public GroupByDto getGroupByDto() {
        return groupByDto;
    }

    public void setGroupByDto(GroupByDto groupByDto) {
        this.groupByDto = groupByDto;
    }

    public ConditionDto getConditionDto() {
        return conditionDto;
    }

    public void setConditionDto(ConditionDto conditionDto) {
        this.conditionDto = conditionDto;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getProjections() {
        return projections;
    }

    public void setProjections(String projections) {
        this.projections = projections;
    }

    @Override
    public String toString() {
        return "SqlDto{" +
                "projections='" + projections + '\'' +
                ", target='" + target + '\'' +
                ", skip=" + skip +
                ", limit=" + limit +
                ", conditionDto=" + conditionDto +
                ", groupByDto=" + groupByDto +
                ", sortDto=" + sortDto +
                '}';
    }
}
