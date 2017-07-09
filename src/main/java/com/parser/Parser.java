package com.parser;

import com.dto.SqlDto;

public class Parser {

    public SqlDto parse(String exampleSql){
        SqlDto sqlDto =  new SqlDto();
        sqlDto.setProjections(parseSelect(exampleSql));
        return sqlDto;
    }

    private String parseSelect(String exampleSql) {
        String cutSelect = exampleSql.trim().substring(exampleSql.indexOf("SELECT")+6).trim();
        return cutSelect.substring(0,cutSelect.indexOf(" FROM"));
    }
}
