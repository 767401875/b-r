package com.ISCAS.OneBeltOneRoad.util;

import com.ISCAS.OneBeltOneRoad.entity.SystemUser;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JWTUtil {
    //过期时间字段
    private static final String EXP = "EXP";
    //发布时间
    private static final String IAT = "IAT";
    private static final String PAYLOAD = "payload";
    //发布者字段
    private static final String ISS = "ISS";
    //为每个请求发配一个appId
    private static final String ID = "id";
    /*
     *   过期时间 120分钟
     */
    private static final long EXPIRE_TIME = 120 * 60 * 1000;
    /*
     *token 私钥
     */
    private static final String TOKEN_SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";
    public static <T> String createAccessJwtToken(T object){
        try{
            final JWTSigner signer = new JWTSigner(TOKEN_SECRET);
            final Map<String, Object> claims = new HashMap<String, Object>();
            SystemUser systemUser = (SystemUser)object;
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(ID, systemUser.getId());
            claims.put(ISS, systemUser.getLoginName());
            claims.put(IAT, System.currentTimeMillis());
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + EXPIRE_TIME);
            return signer.sign(claims);
        }catch (Exception e){
            return null;
        }
    }
    public static <T> String createRefreshToken(T object){
        try{
            final JWTSigner signer = new JWTSigner(TOKEN_SECRET);
            final Map<String, Object> claims = new HashMap<String, Object>();
            SystemUser systemUser = (SystemUser)object;
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(ID, UUID.randomUUID().toString());
            claims.put(ISS, systemUser.getLoginName());
            claims.put(IAT, System.currentTimeMillis());
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + 2 * EXPIRE_TIME);
            return signer.sign(claims);
        }catch (Exception e){
            return null;
        }
    }
    public static<T> T unsign(String jwt, Class<T> classT){
        final JWTVerifier verifier = new JWTVerifier(TOKEN_SECRET);
        try {
            final Map<String, Object> claims = verifier.verify(jwt);
            if(claims.containsKey(PAYLOAD)){
                String json = (String) claims.get(PAYLOAD);
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(json, classT);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
    public static Long getExpireTime(String jwt){
        final JWTVerifier verifier = new JWTVerifier(TOKEN_SECRET);
        try{
            final Map<String, Object> claims = verifier.verify(jwt);
            if(claims.containsKey(EXP)){
                Long exp = (Long)claims.get(EXP);
                return exp;
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
    public static boolean containToken(String token, Map<String, Map<String, String>> userTokenMap, String type){
        Map<String, String> tokenMap = new HashMap<>();
        boolean flag = false;
        if(type.equals("refresh")){
            for(String username : userTokenMap.keySet()){
                tokenMap = userTokenMap.get(username);
                if(tokenMap.containsKey(token)){
                    flag = true;
                    break;
                }
            }

        }
        if(type.equals("access")){
            for(String username : userTokenMap.keySet()){
                tokenMap = userTokenMap.get(username);
                if(tokenMap.containsValue(token)){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    public static String findName(String token, Map<String, Map<String, String>> userTokenMap ){
        Map<String, String> tokenMap = new HashMap<>();
        String name = "";
        for(String username : userTokenMap.keySet()){
            tokenMap = userTokenMap.get(username);
            if(tokenMap.containsKey(token)){
                name = username;
                break;
            }
        }
        return name;
    }
}
