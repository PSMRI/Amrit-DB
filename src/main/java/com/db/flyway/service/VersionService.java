package com.db.flyway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.db.flyway.data.FlywaySchemaVersion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VersionService {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	public List<FlywaySchemaVersion> getLatestVersion() {
		String sql = "SELECT * FROM flyway_schema_history order by 1 desc limit 1";
        return jdbcTemplate.query(sql, new RowMapper<FlywaySchemaVersion>() {
            @Override
            public FlywaySchemaVersion mapRow(ResultSet rs, int rowNum) throws SQLException {
            	FlywaySchemaVersion history = new FlywaySchemaVersion();
                history.setInstalledRank(rs.getInt("installed_rank"));
                history.setVersion(rs.getString("version"));
                history.setDescription(rs.getString("description"));
                history.setType(rs.getString("type"));
                history.setScript(rs.getString("script"));
                history.setChecksum(rs.getInt("checksum"));
                history.setInstalledBy(rs.getString("installed_by"));
                history.setInstalledOn(rs.getTimestamp("installed_on"));
                history.setExecutionTime(rs.getInt("execution_time"));
                history.setSuccess(rs.getBoolean("success"));
                return history;
            }
        });
    }
}
