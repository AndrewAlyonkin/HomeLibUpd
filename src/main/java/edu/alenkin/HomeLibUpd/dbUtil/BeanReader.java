package edu.alenkin.HomeLibUpd.dbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BeanReader<T> {
    T readFromDb(ResultSet resSet) throws SQLException;
}
