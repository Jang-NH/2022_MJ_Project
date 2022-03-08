package com.jnh.mj.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jnh.mj.dto.UserDetailDTO;
import com.jnh.mj.dto.UserLoginDTO;
import com.jnh.mj.dto.UserSaveDTO;
import com.jnh.mj.entity.UserEntity;
import com.jnh.mj.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository ur;

    @Override
    public long save(UserSaveDTO userSaveDTO) {
        UserEntity userEntity = UserEntity.toSaveUser(userSaveDTO);
        UserEntity emailCheck = ur.findByUserEmail(userSaveDTO.getUserEmail());

        if (emailCheck != null) {
            throw new IllegalStateException("중복된 이메일 입니다!");

        }
        return ur.save(userEntity).getId();
    }

    @Override
    public String emailDuplicate(String userEmail) {
        UserEntity userEntity = ur.findByUserEmail(userEmail);
        if (userEntity == null) {
            return "ok";
        } else {
            return "no";
        }
    }

    @Override
    public String nickDuplicate(String userNick) {
        UserEntity userEntity = ur.findByUserNick(userNick);
        if (userEntity == null) {
            return "ok";
        } else {
            return "no";
        }
    }

    @Override
    public UserDetailDTO findById(Long userId) {
        Optional<UserEntity> userEntityOptional = ur.findById(userId);
        UserEntity userEntity = userEntityOptional.get();
        UserDetailDTO userDetailDTO = UserDetailDTO.toUserDetailDTO(userEntity);
        return null;
    }

    @Override
    public boolean findByUserEmail(UserLoginDTO userLoginDTO) {
        UserEntity userEntity = ur.findByUserEmail(userLoginDTO.getUserEmail());
        if (userEntity != null) {
            if (userEntity.getUserPassword().equals(userLoginDTO.getUserPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Long findByUserId(String userEmail) {
        UserEntity userEntity = ur.findByUserEmail(userEmail);
        Long userId = userEntity.getId();
        return userId;
    }

    @Override
    public String getKaKaoAccessToken(String code) {
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=a6fe055949ad4e767ae35921bda4946a"); // REST API 키 입력
            sb.append("&redirect_uri=http://localhost:8093/user/login"); // kakao 로그인에서 사용할 OAuth Redirect URL 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            // 요청을 통해 얻은 Json타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            // Gson 라이브러리에 포함된 클래스로 Json 파싱 객체 생성
            JsonElement element = JsonParser.parseString(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    @Override
    public HashMap<String, Object> getUserInfo(String access_Token) {

        // 요청하는 클라이언트마다 가진 정보가 다를 수 있어 HashMap 타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            System.out.println("responseBody : " + result);

            JsonElement element = JsonParser.parseString(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String userEmail = kakao_account.getAsJsonObject().get("userEmail").getAsString();
            String userNick= properties.getAsJsonObject().get("userNick").getAsString();
            String userProfile = properties.getAsJsonObject().get("userProfile").getAsString();

            userInfo.put("userEmail", userEmail);
            userInfo.put("userNick", userNick);
            userInfo.put("userProfile", userProfile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;

    }
}
