package com.parser;

import com.dto.SqlDto;

public class Parser {

    public SqlDto parse(String exampleSql){
        SqlDto sqlDto =  new SqlDto();
        sqlDto.setProjections(parseSelect(exampleSql));
        sqlDto.setTarget(parseTarget(exampleSql));
        return sqlDto;
    }

    private String parseTarget(String exampleSql) {
        String cutString = exampleSql.trim().substring(exampleSql.indexOf("FROM")+4).trim();
        return cutString.substring(0,cutString.indexOf(" WHERE"));
    }

    private String parseSelect(String exampleSql) {
        String cutString = exampleSql.trim().substring(exampleSql.indexOf("SELECT")+6).trim();
        return cutString.substring(0,cutString.indexOf(" FROM"));
    }
}
