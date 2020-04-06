package org.sid.web;

import org.sid.dao.categoryRepository;
import org.sid.dao.productRepesotory;
import org.sid.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin("*")
public class RestControllerAll {

    @Autowired
    private productRepesotory productRepesotory;
    
    @Autowired
    private categoryRepository catgReposity;
    

    @GetMapping("/getAllProduct")
    public List<Product> getAllListProduct()
    {
    	List<Product> lts =   productRepesotory.findAll();
    	return lts ;
    }
	
	@RequestMapping(value="/getOneProduct/{id}" , method=RequestMethod.GET)
    public Product getOneProduct(@PathVariable("id") Long id)
    {
    	Product lts =   productRepesotory.getOne(id);
    	return lts ;
    }
	
	@RequestMapping(value="/updateProduct" , method=RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product)
    {
		if(product.getId() != null)
		{
			Product lts =   productRepesotory.getOne(product.getId());
			lts.setAvailabe(product.isAvailabe());
			lts.setPromotion(product.isPromotion());
			lts.setSelected(product.isSelected());
			lts.setCurrentprice(product.getCurrentprice());
			lts.setDescription(product.getDescription());
			lts.setName(product.getName());
			
			productRepesotory.save(product);
			
		}
		return product;
    	
    }
    
    
	@RequestMapping(value="/getAllCategorie" , method=RequestMethod.GET)
    public List<Category> getAllCategorie()
    {
    	return catgReposity.findAll(); 
    }
	
	@RequestMapping(value="/getProductByCategories/{id}" , method=RequestMethod.GET)
    public List<Product> getProductByCategories(@PathVariable("id") Long id)
    {
    	return productRepesotory.getListProductByCategorie(id); 
    }
	
	@RequestMapping(value="/getProductPromotion" , method=RequestMethod.GET)
    public List<Product> getProductPromotion()
    {
    	return productRepesotory.getListProductPromotion(); 
    }
	
	@RequestMapping(value="/getProductDisponible" , method=RequestMethod.GET)
    public List<Product> getProductDisponible()
    {
    	return productRepesotory.getListProductDisponible(); 
    }
	
	@GetMapping(path="/photoProduct/{id}", produces=org.springframework.http.MediaType.IMAGE_PNG_VALUE)
	public byte[] getPhoto(@PathVariable("id") Long id) throws IOException
    {
		Product p=  productRepesotory.findById(id).get();
		return Files.readAllBytes(Paths.get((System.getProperty("user.home")+"/ecom/product/"+p.getPhotoName())));
    }
	@CrossOrigin("*")
	@PostMapping(path="/uploadPhoto/{id}")
	public void uploadPhoto(MultipartFile file , @PathVariable("id") Long id) throws Exception
	{
		Product p = productRepesotory.findById(id).get();
		String pe = file.getOriginalFilename();
		if(p != null)
		{
		p.setPhotoName(file.getOriginalFilename());
		
		
		Files.write(Paths.get(System.getProperty("user.home")+"/ecom/product/"+p.getPhotoName()),file.getBytes());
		productRepesotory.save(p);
		}
	}



}
