package com.example.spring_test.Utils;

import com.example.spring_test.bussiness.concreate.RoleService;
import com.example.spring_test.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTToken {

    private RoleService roleService;

    @Autowired
    public JWTToken(RoleService roleService) {
        this.roleService = roleService;
    }

    // hash işlemi yaparken kullanılacak key
    private String SECRET_KEY = "cozef";

    // verilen token a ait kullanıcı adını döndürür.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // verilen token a ait token bitiş süresini verir.
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // verilen token a ait claims bilgisini alır.
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // token ın geçerlilik süre doldu mu?
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // userDetails objesini alır. createToken metoduna gönderir.
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",roleService.getById(user.getRoleId()).get());
        System.out.println(user.getRoleId());
        return createToken(claims, user.getName());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setIssuer(subject)
                .setIssuedAt(new Date(System.currentTimeMillis())) // başlangıç
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000)) // bitiş
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // kullanılan algoritma ve bu algoritma çalışırken kullanılacak hash key değeri
                .compact();
    }

    // token hala geçerli mi? kullanıcı adı doğru ise ve token ın geçerlilik süresi devam ediyorsa true döner.
    public Boolean validateToken(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getName()) && !isTokenExpired(token));
    }
}
