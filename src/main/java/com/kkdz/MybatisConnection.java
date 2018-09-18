package com.kkdz;

import com.kkdz.domain.User;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConnection {

	public static SqlSessionFactory getSqlSession() throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try (SqlSession openSession = sqlSessionFactory.openSession()) {
			/**
	         * 映射sql的标识字符串，
	         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
	         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
	         */
	        String statement = "com.kkdz.mapping.userMapper.getUser";//映射sql的标识字符串
	        //执行查询返回一个唯一user对象的sql
	        User user = openSession.selectOne(statement, 1);
	        System.out.println(user);

		}
		return sqlSessionFactory;
	}

	public static void main(String[] args) {
		try {
			getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
