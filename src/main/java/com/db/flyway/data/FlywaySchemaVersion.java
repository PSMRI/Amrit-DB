package com.db.flyway.data;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class FlywaySchemaVersion {
	private int installedRank;
    private String version;
    private String description;
    private String type;
    private String script;
    private Integer checksum;
    private String installedBy;
    private Timestamp installedOn;
    private int executionTime;
    private boolean success;
}
