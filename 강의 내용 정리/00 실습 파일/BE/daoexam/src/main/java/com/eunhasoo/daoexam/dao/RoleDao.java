package com.eunhasoo.daoexam.dao;

// SELECT_ALL을 사용하기 위해 static import
import static com.eunhasoo.daoexam.dao.RoleDaoSqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.eunhasoo.daoexam.dto.Role;

/*
 * 저장소 역할을 한다는 의미의 어노테이션을 가진 클래스
 * Config 클래스에서 ComponentScan 어노테이션을 통해 찾아져서 Bean으로 등록됨
 */
@Repository
public class RoleDao {
	
	// 바인딩할 때 파라미터명을 사용하는 JdbcTemplate
	private NamedParameterJdbcTemplate jdbc;
	
	// Inser문을 실행하기 위한 객체
	private SimpleJdbcInsert insertAction;
	
	// Mapper는 DBMS와 Java의 이름 규칙을 맞춰주는 기능도 담당한다.
	private RowMapper<Role> rowMapper = BeanPropertyRowMapper.newInstance(Role.class);

	public RoleDao(DataSource dataSource) { // Bean으로 등록한 dataSource가 파라미터로 전달됨
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("role");
	}

	// 모든 Role을 조회하는 메소드
	public List<Role> selectAll() {
		// 파라미터로 (SQL문, 바인딩용 빈 Map 객체, SELECT 결과 하나하나를 자동으로 DTO에 저장해줄 Mapper)을 지정
		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
		// query 메소드는 결과가 여러건일 때 내부적으로 반복하면서 DTO를 생성하고, 그 생성한 DTO들을 하나의 List에 담아서 전달해준다.
	}
	
	public int insert(Role role) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(role);
		return insertAction.execute(params);
	}
	
	public int update(Role role) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(role);
		return jdbc.update(UPDATE, params);
	}
	
	public Role selectById(Integer id) {
		try {
			Map<String, ?> params = Collections.singletonMap("roleId", id);
			return jdbc.queryForObject(SELECT_BY_ROLE_ID, params, rowMapper);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int deleteById(Integer id) {
		// id 값 하나만을 넣어서 바로 사용하기 위해 singletonMap 메소드를 이용할 수 있음
		Map<String, ?> params = Collections.singletonMap("roleId", id);
		return jdbc.update(DELETE_BY_ROLE_ID, params);
	}
}
