package com.ssocio.security;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 인증후 서버
 * @author user@danalssocio.com
 * @date 2017. 6. 2.
 *
 */
@EnableResourceServer
@SpringBootApplication
public class SecurityApplication extends ResourceServerConfigurerAdapter{
	private static final Logger log = LoggerFactory.getLogger(SecurityApplication.class);
	
	@Bean
	public ResourceServerConfigurerAdapter resourceServerConfigurerAdapter() {
		return new ResourceServerConfigurerAdapter() {
			@Override
			public void configure(HttpSecurity http) throws Exception {
				http.headers().frameOptions().disable();
				/*
				http.authorizeRequests()
						.antMatchers("/members", "/members/**").access("#oauth2.hasScope('read')")
						.anyRequest().authenticated();
				*/
				http.authorizeRequests()
				.antMatchers("/","/h2-console/*", "/lib/*", "/images/*", "/css/*", "/swagger-ui.js","/swagger-ui.min.js", "/api-docs", "/fonts/*", "/api-docs/*", "/api-docs/default/*", "/o2c.html","index.html","/webjars/**","/hystrix/**").permitAll()
				.antMatchers("/members", "/members/**")
				.access("#oauth2.hasScope('read')")
				.anyRequest()
				.authenticated();
			}
		};
	}

	/**
	 * API를 조회시 출력될 테스트 데이터
	 * @param memberRepository
	 * @return
	 */
	@Bean
	public CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
		return args -> {
			memberRepository.save(new Member("이철수", "chulsoo", "test111"));
			memberRepository.save(new Member("김정인", "jungin11", "test222"));
			memberRepository.save(new Member("류정우", "jwryu991", "test333"));
			
			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Member customer : memberRepository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Member customer = memberRepository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");
	
			// fetch customers by last name
			log.info("Customer found with findByLastName('류정우'):");
			log.info("--------------------------------------------");
			for (Member bauer : memberRepository.findByName("류정우")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
}


@RepositoryRestResource
interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
	List<Member> findByName(String name);
}

@Data
@Entity
@AllArgsConstructor //추가 모든 필드가 있는 생성자를 만든다.
@NoArgsConstructor  //추가 디폴트 생성자를 만든다. 이놈이 필요한이유는 JPA 덕분 
class Member implements Serializable {
	
	private static final long serialVersionUID = 4168082443745013640L;
	
	@Id
	@GeneratedValue
	Long id;
	String name;
	String username;
	String remark;
	public Member(String name, String username, String remark) {
		this.name = name;
		this.username = username;
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString()).append(" id=").append(id).append(", name=").append(name).append(", username=")
				.append(username).append(", remark=").append(remark);
		return builder.toString();
	}
}

