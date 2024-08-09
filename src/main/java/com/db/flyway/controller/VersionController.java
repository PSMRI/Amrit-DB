
package com.db.flyway.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.flyway.data.FlywaySchemaVersion;
import com.db.flyway.service.VersionService;
import com.google.gson.Gson;

@RestController

@RequestMapping("/db/migration")
public class VersionController {
	private Logger logger = LoggerFactory.getLogger(VersionController.class);
	@Autowired
	private VersionService service;

	@GetMapping("/version")
	public String getLatestDBMigrationVersion() {
		String resp = null;
		try {
			List<FlywaySchemaVersion> latestVersion = service.getLatestVersion();
			Gson gson = new Gson();
			if (null != latestVersion && CollectionUtils.isEmpty(latestVersion)) {
				resp = gson.toJson(latestVersion);
			}
		} catch (Exception e) {
			logger.error("Error while getting DB Migration version :" + e);
		}
		return resp;
	}

}
