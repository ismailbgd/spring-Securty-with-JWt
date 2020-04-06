package org.sid;

import java.util.Random;
import java.util.stream.Stream;

import org.sid.dao.categoryRepository;
import org.sid.dao.productRepesotory;
import org.sid.model.AppRole;
import org.sid.model.Category;
import org.sid.model.Product;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.bytebuddy.utility.RandomString;

@SpringBootApplication
public class ProjetSpringSecurtyApplication implements CommandLineRunner {

	@Autowired
	private categoryRepository categoryReposity;
	@Autowired
	private productRepesotory productRepesoty;

	public static void main(String[] args) {
		SpringApplication.run(ProjetSpringSecurtyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
				
		/*
		 * categoryReposity.save(new Category( null , "computers" , null));
		 * categoryReposity.save(new Category( null , "printers" , null));
		 * categoryReposity.save(new Category( null , "smart phone " , null));
		 * 
		 * Random r = new Random(); categoryReposity.findAll().forEach(ct -> { Product p
		 * = new Product(); p.setName(RandomString.make(18));
		 * p.setCurrentprice(100+r.nextInt(10000)); p.setAvailabe(r.nextBoolean());
		 * p.setPromotion(r.nextBoolean()); p.setPhotoName("unknow.jpg");
		 * p.setCategory(ct); productRepesoty.save(p); });
		 */
				 
	}

	
	/*
	 * @Bean CommandLineRunner start(AccountService accountService) { return args ->
	 * { accountService.save(new AppRole(null, "ADMIN")); accountService.save(new
	 * AppRole(null, "USER")); Stream.of("ismail", "user2", "user3",
	 * "admin").forEach(un -> { accountService.saveUser(un, "1234", "1234"); }); };
	 * }
	 */
	 

}
