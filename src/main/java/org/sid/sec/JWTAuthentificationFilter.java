package org.sid.sec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sid.model.AppUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthentificationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;

	public JWTAuthentificationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	// methode for recupprer user connected and verify si all god pass to methode successfulAuthentication
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
		AppUser appUser = null ;
		// get object request.getInputStream() == (json format) and desealiser to class appuser (java)
		try {
			 appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
			 return authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);

		}
			}
	
	/********** structure jwt ***********
	
	 JWT : contient 3 partie == header - payload signature 
	 structure jwt === header.payload.signature
	 
	 * ************************/
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	// get utilisqteur connect 
		User user = (User)authResult.getPrincipal();
		// get all autority and add to list like role
		List<String> roles = new ArrayList<String>();
		authResult.getAuthorities().forEach(au -> {
			roles.add(au.getAuthority());
		});
		// create jwt 
		// add dependensie jwt aut0  to pom
		String jwt = JWT.create()
				.withIssuer(request.getRequestURI())
				.withSubject(user.getUsername())
				// convert list to arry and pass like array roles 
				.withArrayClaim("roles", roles.toArray(new String [roles.size()]))
				// date expration date current + 10 day
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurtyParams.EXPERATION))
				// le secrete  
				.sign(Algorithm.HMAC256(SecurtyParams.SECRETE));
		System.out.println(jwt);
		// pass jwt in header with prefix Bearer
		response.addHeader(SecurtyParams.HEADER_NAME, SecurtyParams.HEADER_PREFIX +jwt);
	}
	
	
}
