package com.parser;

import com.dto.SqlDto;

public class Parser {

    public SqlDto parse(String exampleSql){
        SqlDto sqlDto =  new SqlDto();
        sqlDto.setProjections(parseSelect(exampleSql));
        sqlDto.setTarget(parseTarget(exampleSql));
        sqlDto.setSkip(parseSkip(exampleSql));
        sqlDto.setLimit(parseLimit(exampleSql));
        return sqlDto;
    }

    private Integer parseLimit(String exampleSql) {
        String cutString = exampleSql.trim().substring(exampleSql.indexOf("LIMIT")+5).trim();
        return Integer.parseInt(cutString.substring(0,cutString.indexOf(" OFFSET")));
    }

    private Integer parseSkip(String exampleSql) {
        String cutString = exampleSql.trim().substring(exampleSql.indexOf("OFFSET")+6).trim();
        return Integer.parseInt(cutString);
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
