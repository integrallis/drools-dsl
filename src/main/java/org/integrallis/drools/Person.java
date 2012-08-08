package org.integrallis.drools;

public class Person {
	private String name;
	private String location;
	private Integer age;

	public Person(String name, String location, Integer age) {
		this.name = name;
		this.location = location;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public Integer getAge() {
		return age;
	}

}
