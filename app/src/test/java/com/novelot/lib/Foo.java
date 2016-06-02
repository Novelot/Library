package com.novelot.lib;

public class Foo {

	public Foo() {
	}

	public Foo(String info) {
		super();
		this.info = info;
	}

	private String info;

	private String outInfo() {
		return info;
	}

	private void setMsg(String info) {
		this.info = info;
	}
}