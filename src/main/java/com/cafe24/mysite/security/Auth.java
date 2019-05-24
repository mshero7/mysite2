package com.cafe24.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 어디에다가 붙여서 쓸건지 정할 수 있음 (파라미터, 메소드, 클래스, 타입 등)
@Target( {ElementType.METHOD,ElementType.TYPE})
// 유효기간
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	public enum Role {USER, ADMIN}
	public Role role() default Role.USER;
//	String value() default "USER";
//	int test() default 1;
}