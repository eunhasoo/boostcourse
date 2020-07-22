package com.eunhasoo.roleapp.dao;

import static com.eunhasoo.roleapp.dao.RoleDaoSqls.SELECT_ALL;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eunhasoo.roleapp.dto.Role;

@Repository
public class RoleDao {

	private NamedParameterJdbcTemplate template;
	private RowMapper<Role> rowMapper = BeanPropertyRowMapper.newInstance(Role.class);

	public RoleDao(DataSource dataSource) {
		this.template = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Role> selectAll() {
		return template.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
	}
}
