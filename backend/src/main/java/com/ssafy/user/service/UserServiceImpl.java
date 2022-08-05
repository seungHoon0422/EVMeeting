package com.ssafy.user.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.ssafy.user.request.UserEditInforPutReq;
import com.ssafy.user.request.UserEditPWPutReq;
import com.ssafy.user.request.UserRegisterPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.user.db.entity.User;
import com.ssafy.user.db.repository.UserRepository;
import com.ssafy.user.db.repository.UserRepositorySupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */

@Slf4j
@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setUserid(userRegisterInfo.getUserid());
		user.setUsername(userRegisterInfo.getUsername());
		user.setHeight(userRegisterInfo.getHeight());
		user.setWeight(userRegisterInfo.getWeight());
		user.setGender(userRegisterInfo.getGender());
		user.setBirth(userRegisterInfo.getBirth());
		user.setHobby(userRegisterInfo.getHobby());
		user.setMbti(userRegisterInfo.getMbti());
		user.setSchool(userRegisterInfo.getSchool());
		user.setEmail(userRegisterInfo.getEmail());
		user.setDrink(userRegisterInfo.getDrink());
		user.setCigarette(userRegisterInfo.getCigarette());
		user.setDescription(userRegisterInfo.getDescription());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword1()));
		return userRepository.save(user);
	}

	@Override
	public User getUserByUserId(String userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepositorySupport.findUserByUserid(userId).get();
		return user;
	}

	@Override
	public void removeUser(String userId){
		User user = getUserByUserId(userId);
		userRepository.delete(user);

	}

	//사용자 프로필 정보 변경
	@Override
	public User editUserInfor(UserEditInforPutReq userEditInfo){
		User user = getUserByUserId(userEditInfo.getUserid());
		//입력값이 없다면 설정하지 않고, 입력값이 있는 경우에만 새롭게 변경한다.
		if(userEditInfo.getUsername() != null){
			user.setUsername(userEditInfo.getUsername());
		}
		if(userEditInfo.getHeight() != null){
			user.setHeight(userEditInfo.getHeight());
		}
		if(userEditInfo.getWeight() != null){
			user.setWeight(userEditInfo.getWeight());
		}
		if(userEditInfo.getGender() != null){
			user.setGender(userEditInfo.getGender());
		}
		if(userEditInfo.getBirth() != null){
			user.setBirth(userEditInfo.getBirth());
		}
		if(userEditInfo.getHobby() != null){
			user.setHobby(userEditInfo.getHobby());
		}
		if(userEditInfo.getMbti() != null){
			user.setMbti(userEditInfo.getMbti());
		}
		if(userEditInfo.getSchool() != null){
			user.setSchool(userEditInfo.getSchool());
		}
		if(userEditInfo.getEmail() != null){
			user.setEmail(userEditInfo.getEmail());
		}
		if(userEditInfo.getDrink() != null){
			user.setDrink(userEditInfo.getDrink());
		}
		if(userEditInfo.getCigarette() != null){
			user.setCigarette(userEditInfo.getCigarette());
		}
		if(userEditInfo.getDescription() != null){
			user.setDescription(userEditInfo.getDescription());
		}
		return userRepository.save(user);
	}

	//사용자 비밀번호 변경
	@Override
	public User editUserPW(UserEditPWPutReq userEditPW){
		User user = getUserByUserId(userEditPW.getUserid());
		user.setPassword(passwordEncoder.encode(userEditPW.getNewpassword1()));
		return userRepository.save(user);
	}

	//사용자 프로필 사진 변경
	private final AmazonS3Client amazonS3Client;

	@Value("${S3.BUCKETNAME}")
	private String bucketName;

	@Value("${S3.URL}")
	private String bucketUrl;



	@Override
	public User uploadPhoto(MultipartFile multipartFile, String userid) {
		//파일 이름을 설정
		int fileExtensionIndex = multipartFile.getOriginalFilename().lastIndexOf(".");
		String fileExtension = multipartFile.getOriginalFilename().substring(fileExtensionIndex);	//확장자명
		String now = String.valueOf(System.currentTimeMillis());	//현재날짜와시각
		String fileName = "profile/" + userid + "_" + now + fileExtension;	//새로운 파일명

		//S3에 파일을 저장하고
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(multipartFile.getContentType());
		try (InputStream inputStream = multipartFile.getInputStream()) {
			byte[] bytes = IOUtils.toByteArray(inputStream);
			objectMetadata.setContentLength(bytes.length);
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, byteArrayInputStream, objectMetadata)
					.withCannedAcl(CannedAccessControlList.PublicRead);
			amazonS3Client.putObject(putObjectRequest);
		} catch (IOException e) {
		}
		//DB에 해당 파일 경로를 저장한다
		User user = getUserByUserId(userid);
		user.setPhoto(bucketUrl + fileName);
		return userRepository.save(user);
	}
	@Override
	public User deletePhoto(String userid) {
		System.out.println("LETS DELETE");
		//경로를 찾기
		User user = getUserByUserId(userid);
		String location = user.getPhoto();
		System.out.println("I FOUND THE LOCATION");
		System.out.println(location.replace(bucketUrl, ""));
		//S3에 저장된 파일을 삭제한다
		amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, location.replace(bucketUrl, "")));
		System.out.println("I ERASED THE S3 FILE");
		//DB에 해당 사진 경로를 삭제한다
		user.setPhoto(null);
		return userRepository.save(user);
	}
}
