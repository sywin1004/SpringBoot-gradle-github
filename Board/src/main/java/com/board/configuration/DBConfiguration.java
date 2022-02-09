package com.board.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
//		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
		return factoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

}

//package com.board.configuration;
//
//import javax.sql.DataSource;
//
//import org.apache.catalina.core.ApplicationContext;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//@Configuration // 현재 DBConfiguration 클래스 파일을 자바 기반의 설정 파일로 인식시키는 어노테이션
//@PropertySource("classpath:/application.properties") // 해당 클래스에서 참조할 properties 파일의 위치를 지정
//public class DBConfiguration {
//	
//	@Autowired // Bean으로 등록된 인스턴스(객체) 를 클래스에 주입하는데 사용 @resource, @inject 등이 존재
//	private ApplicationContext applicationContext; // 스프링 컨테이너 중 하나 Bean의 생성,사용,관계,생명 주기 등을 관리
//	@Bean
//	@ConfigurationProperties(prefix = "spring.datasource.hikari") // 인자에 prefix 속성 지정 가능 @propertySource에 지정된 application.properties에서 prefix에 해당하는 spring.dataSource.hikari로 시작하는 설정을 모두 읽어 해당 메소드에 매핑한다
//	public HikariConfig hikariConfig() { // hikari CP 객체 생성 Connection Pool 라이브러리 중 하나
//		return new HikariConfig();
//	}
//	
//	@Bean // Configuration 클래스의 메소드 래벨에만 지정 가능 @Bean이 지정된 객체는 컨테이너에 의해 관리되는 Bean 으로 등록 인자로 속성 등록 가능
//	public DataSource dataSource() {
//		/*
//		 * 데이터 소스 객체 생성
//		 * 커넥션 풀은 커넥션 객체를 생성해 두고 데이터 베이스에 접근하는 사용자에게 미리 생성한 커넥션을 제공한 뒤 다시 돌려받는다
//		 * 데이터 소스는 커넥션 풀을 지원하기 위한 인터페이스
//		 */
//		return new HikariDataSource(hikariConfig());
//	}
//	
//	@Bean
//	public SqlSessionFactory sqlSessionFactory() throws Exception {
//		/*
//		 * SqlSessionFactory 데이터베이스의 커넥션과 SQL 실행에 대한 모든 것을 가진 중요한 역할
//		 * SqlSessionBean은 마이바티스와 스프링의 연동 모듈로 사용
//		 * 마이바티스 XML MAPPER, 설정 파일 위치 등을 지정
//		 * SqlSessionFactoryBean 자체가 아닌 getObject 메서드가 리턴하는 SqlSessionFactory를 생성
//		 */
//		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//		factoryBean.setDataSource(dataSource());
////		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
//		return factoryBean.getObject();
//	}
//	
//	@Bean
//	public SqlSessionTemplate sqlSession() throws Exception {
//		/*
//		 * 1. SqlSessionTemplate은 마이바티스 스프링 연동 모듈의 핵심이다.
//
//			2. SqlSessionTemplate은 SqlSession을 구현하고,
//			    코드에서 SqlSession을 대체하는 역할을 한다.
//			
//			3. SqlSessionTemplate은 쓰레드에 안전하고,
//			    여러 개의 DAO나 Mapper에서 공유할 수 있다.
//			
//			4. 필요한 시점에 세션을 닫고, 커밋 또는 롤백하는 것을 포함한
//			    세션의 생명주기를 관리한다.
//		 */
//		return new SqlSessionTemplate(sqlSessionFactory());
//	}
//}
