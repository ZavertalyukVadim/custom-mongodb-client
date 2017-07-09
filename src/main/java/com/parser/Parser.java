package com.parser;

import com.dto.ConditionDto;
import com.dto.SqlDto;

import java.util.Objects;

public class Parser {

    public SqlDto parse(String exampleSql) {
        SqlDto sqlDto = new SqlDto();
        sqlDto.setProjections(parseSelect(exampleSql));
        sqlDto.setTarget(parseTarget(exampleSql));
        sqlDto.setSkip(parseSkip(exampleSql));
        sqlDto.setLimit(parseLimit(exampleSql));
        sqlDto.setConditionDto(parseCondition(exampleSql));
        System.out.println(sqlDto.getConditionDto());
        return sqlDto;
    }

    private ConditionDto parseCondition(String exampleSql) {
        ConditionDto conditionDto = new ConditionDto();
        conditionDto.setExtended(true);
        String cutString = exampleSql.trim().substring(exampleSql.indexOf("WHERE") + 5).trim();

        String firstField = cutString.substring(0, cutString.indexOf(" "));
        String cutFirstField = cutString.substring(cutString.indexOf(firstField) + firstField.length()).trim();
        conditionDto.setFirstField(firstField);

        String firstOperator = cutFirstField.substring(0, 2).trim();
        String cutFirstOperator = cutFirstField.substring(cutFirstField.indexOf(firstOperator) + firstOperator.length()).trim();
        conditionDto.setFirstOperator(firstOperator);

        String firstValue = cutFirstOperator.substring(1, cutFirstOperator.indexOf(" ") - 1).trim();
        String cutFirstValue = cutFirstOperator.substring(cutFirstOperator.indexOf(firstValue) + firstValue.length() + 1).trim();
        conditionDto.setFirstValue(firstValue);
        if (Objects.equals(cutFirstValue.substring(0, cutFirstValue.indexOf(" ")), "GROUP")) {
            conditionDto.setExtended(false);
            return conditionDto;
        }

        String standardLogicalOperation = cutFirstValue.substring(0, cutFirstValue.indexOf(" ")).trim().toUpperCase();
        String cutStandardLogicalOperation = cutFirstValue.substring(cutFirstValue.indexOf(standardLogicalOperation) + standardLogicalOperation.length()).trim();
        conditionDto.setStandardLogicalOperation(standardLogicalOperation);

        String secondField = cutStandardLogicalOperation.substring(0, cutStandardLogicalOperation.indexOf(" ")).trim();
        String cutSecondField = cutStandardLogicalOperation.substring(cutStandardLogicalOperation.indexOf(secondField) + secondField.length()).trim();
        conditionDto.setSecondField(secondField);

        String secondOperator = cutSecondField.substring(0, 2).trim();
        String cutSecondOperator = cutSecondField.substring(cutSecondField.indexOf(secondOperator) + secondOperator.length()).trim();
        conditionDto.setSecondOperator(secondOperator);

        String secondValue = cutSecondOperator.substring(1, cutSecondOperator.indexOf(" ") - 1).trim();
        String cutSecondValue = cutSecondOperator.substring(cutSecondOperator.indexOf(secondValue) + secondValue.length() + 1).trim();
        conditionDto.setSecondValue(secondValue);
        return conditionDto;
    }


    private Integer parseLimit(String exampleSql) {
        String cutString = exampleSql.trim().substring(exampleSql.indexOf("LIMIT") + 5).trim();
        return Integer.parseInt(cutString.substring(0, cutString.indexOf(" OFFSET")));
    }

    private Integer parseSkip(String exampleSql) {
        String cutString = exampleSql.trim().substring(exampleSql.indexOf("OFFSET") + 6).trim();
        return Integer.parseInt(cutString);
    }

    private String parseTarget(String exampleSql) {
        String cutString = exampleSql.trim().substring(exampleSql.indexOf("FROM") + 4).trim();
        return cutString.substring(0, cutString.indexOf(" WHERE"));
    }

    private String parseSelect(String exampleSql) {
        String cutString = exampleSql.trim().substring(exampleSql.indexOf("SELECT") + 6).trim();
        return cutString.substring(0, cutString.indexOf(" FROM"));
    }
}
