package com.cops.ntsf.util;

import com.cops.ntsf.constants.AuthType;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class JwtUtils {
    private AuthType authType = AuthType.USER;

    private final JSONArray loginResponse;

    public JwtUtils(AuthType authType, JSONArray loginResponse) {
        this.authType = authType;
        this.loginResponse = loginResponse;
    }

    /**
     * Generate JWT with authorization payloads
     */
    public String generateJwt() {
        String base64UrlHeader = this.generateJwtHeader();
        String base64UrlPayload = this.generateJwtPayload();

        // Concatenate jwt header and payload
        String base64UrlHeaderAndPayload = base64UrlHeader + "." + base64UrlPayload;

        String signature = generateJwtSignature(base64UrlHeaderAndPayload);

        //Concatenate the encoded header, payload and signature
        String jwt = base64UrlHeaderAndPayload + "." + signature;
        System.out.println("JWT token is generated\n");
        System.out.println(jwt);

        return jwt;
    }

    /**
     * Generates jwt header.
     *
     * @return Base64 encoded jwt header
     */
    private String generateJwtHeader() {
        // Creating the header
        JSONObject header = new JSONObject();
        header.put("alg", "HS256");
        header.put("typ", "JWT");  // abc2.s23d.isg3

        return Base64.getUrlEncoder().encodeToString(header.toString().getBytes());
    }

    /**
     * Generates jwt payload.
     *
     * @return Base64 encoded jwt payload
     */
    private String generateJwtPayload() {
        // Creating the payload
        JSONObject payload = new JSONObject();
        payload.put("authType", authType.name());

        if (authType == AuthType.POLICEMAN) {
            // TODO REFACTOR!! Using JSONArrays are not ideal in java code, use ArrayList and a proper java model
            payload.put("police_id", loginResponse.getJSONObject(0).getString("police_id"));
            payload.put("rank", loginResponse.getJSONObject(0).getString("rank"));
            payload.put("position", loginResponse.getJSONObject(0).getString("position"));
            payload.put("police_station", loginResponse.getJSONObject(0).getString("police_station"));
        }

        return Base64.getUrlEncoder().encodeToString(payload.toString().getBytes());
    }

    /**
     * Generates jwt signature.
     *
     * @param base64UrlHeaderAndPayload base64 encoded jwt header and payload.
     * @return jwt signature
     */
    private String generateJwtSignature(String base64UrlHeaderAndPayload) {
        try {
            // Sign the jwt with the secret key
            String hmacSecretKey = "mysecret";  // TODO Refactor hardcoded secrets
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

            SecretKeySpec secret_key = new SecretKeySpec(hmacSecretKey.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            return Base64.getUrlEncoder().encodeToString(sha256_HMAC.doFinal(base64UrlHeaderAndPayload.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}