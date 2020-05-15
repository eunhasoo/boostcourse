package com.eunhasoo.mvcexam.dto;

public class User {
	/*
	 * Spring이 알아서 처리하도록 해주려면 Request Parameter와 매핑되도록 이름을 동일하게 맞춰줘야 하고, 
	 * setter 메소드(프로퍼티)를 작성해줘야 함
	 */

	private String name;
	private String email;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", age=" + age + "]";
	}
}
