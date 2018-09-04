package com.tools.sqlword.mapper;

import com.tools.sqlword.bean.columnBean;
import com.tools.sqlword.bean.tableBean;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface tableMapper {

    @Results({
            @Result(property = "tableName",column = "table_name"),
            @Result(property = "tableComment",column = "table_comment")
    })
    @Select(" select table_name,table_comment from information_schema.tables where table_schema=#{dataSource} and table_type='base table'")
    List<tableBean> selectTableByDataSource(@Param("dataSource")String dataSource);

    @Results({
            @Result(property = "tableName",column = "TABLE_NAME"),
            @Result(property = "columnName",column = "COLUMN_NAME"),
            @Result(property = "coulumType",column = "COLUMN_TYPE"),
            @Result(property = "coulumDefault",column = "COLUMN_DEFAULT"),
            @Result(property = "coulumComment",column = "COLUMN_COMMENT")
    })
    @Select("select COLUMN_NAME,COLUMN_TYPE,COLUMN_DEFAULT,COLUMN_COMMENT,TABLE_NAME from information_schema.COLUMNS where table_schema=#{dataSource} and table_name=#{tableName}")
    List<columnBean> selectColumnByDataSource(@Param("dataSource")String dataSource,@Param("tableName")String tableName);
}
