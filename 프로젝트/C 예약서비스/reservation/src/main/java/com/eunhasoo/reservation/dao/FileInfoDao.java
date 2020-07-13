package com.eunhasoo.reservation.dao;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class FileInfoDao {

	private final String INSERT_FILE = "insert into file_info ("
		+ "file_name, save_file_name, content_type, delete_flag, create_date, modify_date) "
		+ "values ("
		+ ":fileName, :saveFileName, :contentType, :deleteFlag, :createDate, :modifyDate)";
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public FileInfoDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public Integer insertFile(File file) {
		String fileName = file.getName();
		String saveFileName = file.getParentFile().getName() + "/" + fileName;
		String contentType = "image/" + file.getName().split("\\.")[1];
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource()
											.addValue("fileName", fileName)
											.addValue("saveFileName", saveFileName)
											.addValue("contentType", contentType)
											.addValue("deleteFlag", false)
											.addValue("createDate", date)
											.addValue("modifyDate", date);
		jdbcTemplate.update(INSERT_FILE, parameters, keyHolder);
		return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
	}
	
}
