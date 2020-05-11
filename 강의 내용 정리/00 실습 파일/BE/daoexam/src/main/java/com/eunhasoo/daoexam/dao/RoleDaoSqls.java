package com.eunhasoo.daoexam.dao;

public class RoleDaoSqls {
	/* 사용하고자 하는 쿼리를 상수 형태 (이름 대문자)로 정의한다 */
	public static final String SELECT_ALL = "SELECT role_id, description FROM role ORDER BY role_id";
	
	/* 바인딩 될 부분 앞에 :를 넣어준다 !콜론 뒤에 Java 프로퍼티명 규칙! */
	public static final String UPDATE = "UPDATE role SET description = :description WHERE ROLE_ID = :roleId";
	
	public static final String SELECT_BY_ROLE_ID = "SELECT role_id, description FROM role WHERE role_id = :roleId";
	
	public static final String DELETE_BY_ROLE_ID = "DELETE FROM role WHERE role_id = :roleId";
}
