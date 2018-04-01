package com.zhuwenshen.model;

import javax.persistence.*;

public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String passworld;

    private Boolean sex;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return passworld
     */
    public String getPassworld() {
        return passworld;
    }

    /**
     * @param passworld
     */
    public void setPassworld(String passworld) {
        this.passworld = passworld == null ? null : passworld.trim();
    }

    /**
     * @return sex
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + ", passworld=" + passworld + ", sex=" + sex + "]";
	}
    
    
}