package com.eunhasoo.diexam01;

/*
 * Bean의 세 가지 특징!
 * 1) 기본 생성자를 가진다
 * 2) 필드는 private으로 선언한다
 * 3) getter와 setter 메소드를 가지며 이들을 프로퍼티 property라고 부른다
 */
public class UserBean {

	private String name;
	private int age;
	private boolean male;
	
	// 기본 생성자 
	public UserBean() { }
	
	public UserBean(String name, int age, boolean male) {
		super();
		this.name = name;
		this.age = age;
		this.male = male;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}
	
}
