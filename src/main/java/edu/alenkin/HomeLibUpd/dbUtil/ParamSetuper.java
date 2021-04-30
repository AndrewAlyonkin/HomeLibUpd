package edu.alenkin.HomeLibUpd.dbUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParamSetuper {
    void setParams(PreparedStatement prepst) throws SQLException;
}
