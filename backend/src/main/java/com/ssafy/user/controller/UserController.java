package com.ssafy.user.controller;

import com.ssafy.user.db.repository.UserRepository;
import com.ssafy.user.request.*;
import com.ssafy.user.response.UserLoginPostRes;
import com.ssafy.user.response.UserRes;
import com.ssafy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.user.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/v1/accounts/")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepository;


	@PostMapping("login/")
	@ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
	})
	public ResponseEntity<UserLoginPostRes> login(@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginPostReq loginInfo) {
		String userId = loginInfo.getUserid();
		String password = loginInfo.getPassword();

		User user = userService.getUserByUserId(userId);
		// 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
		if(passwordEncoder.matches(password, user.getPassword())) {
			// 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
			return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(userId)));
		}
		// 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
		return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "Invalid Password", null));
	}

	@PostMapping("logout/")
	@ApiOperation(value = "로그아웃", notes = "토큰을 삭제한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> logout(HttpServletRequest req) {
		//세션을 받아오기
		HttpSession session = req.getSession();
		session.invalidate();
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	@GetMapping("user/")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		return ResponseEntity.status(200).body(UserRes.of(user));
	}

	@PostMapping("signup/")
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드, 이름</strong>를 통해 회원가입 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value = "회원가입 정보", required = true) UserRegisterPostReq registerInfo) {
		//새로 가입하려는 아이디가 이미 존재하는 아이디와 일치하는지 확인
		if (userRepository.existsByUserid(registerInfo.getUserid())) {
			return ResponseEntity.status(405).body(BaseResponseBody.of(405, "ID already exists"));
		}
		//두 비밀번호가 일치하는지, 아닌지 확인
		else if(!registerInfo.getPassword1().equals(registerInfo.getPassword2())){
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Passwords are not same"));
		}
		//모두 괜찮다면 회원가입 실행
		else {
			User user = userService.createUser(registerInfo);
			System.out.println("@@@@@ USER ID : " + user.getUserid());
			System.out.println("@@@@@ TOKEN : " + JwtTokenUtil.getToken(user.getUserid()));
			return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(user.getUserid())));
		}
	}

	@PostMapping("checkduplicateid/")
	@ApiOperation(value = "회원 가입 중 아이디 중복검사", notes = "아이디 중복 체크 여부를 확인한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> registerIdCheck(
			@RequestBody @ApiParam(value = "회원가입 정보", required = true) String userid) {
		//새로 가입하려는 아이디가 이미 존재하는 아이디와 일치하는지 확인
		System.out.println("@@@@@@ : " + userid);
		//userid에 =이 함께 들어옴.
		//=가 있다면 삭제하고, 판별해보기
		userid = userid.replace("=", "");
		if (userRepository.existsByUserid(userid)) {
			System.out.println("ID ALREADY EXISTS!");
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "false"));
		}
		else{
			System.out.println("YOU CAN USE THIS ID!");
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "true"));
		}
	}


//	@PostMapping(value = "uploadphoto/{userid}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//	@ApiOperation(value = "회원 사진 추가/수정", notes = "회원 정보 중 사진을 수정한다.")
//	@ApiResponses({
//			@ApiResponse(code = 200, message = "성공"),
//			@ApiResponse(code = 401, message = "인증 실패"),
//			@ApiResponse(code = 404, message = "사용자 없음"),
//			@ApiResponse(code = 500, message = "서버 오류")
//	})
//	public ResponseEntity<? extends BaseResponseBody> editImage(
//			@RequestParam("imgUpload1") MultipartFile file, @PathVariable String userid) {
//		System.out.println("ACCESS!!!");
//		System.out.println("userid : " + userid);
//		System.out.println("ACCESS!!!");
//		//해당 유저의 정보들 변경하기
//		userService.editUserPhoto(file, userid);
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
//	}

//	@PostMapping(value = "showphoto/{userid}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//	@ApiOperation(value = "회원 사진 추가/수정", notes = "회원 정보 중 사진을 수정한다.")
//	@ApiResponses({
//			@ApiResponse(code = 200, message = "성공"),
//			@ApiResponse(code = 401, message = "인증 실패"),
//			@ApiResponse(code = 404, message = "사용자 없음"),
//			@ApiResponse(code = 500, message = "서버 오류")
//	})
//	public ResponseEntity<? extends BaseResponseBody> showImage(
//			@RequestParam("imgUpload1") MultipartFile file, @PathVariable String userid) {
//		Map<String,Object> resultMap = Service.selectImg();
//
//		byte[] arr = (byte[]) resultMap.get("base64");
//		String base64ToString = new String(arr);
//
//		model.addAttribute("imgSrc",base64ToString);
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
//	}

	@PostMapping("editprofile/")
	@ApiOperation(value = "회원 정보 수정", notes = "회원정보 중 정보들을 수정한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> editprofile(
			@RequestBody @ApiParam(value = "회원수정 정보 - 프로필", required = true) UserEditInforPutReq editInfo) {
		//해당 유저의 정보들 변경하기
		userService.editUserInfor(editInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SuccessChange"));
	}

	@PostMapping("editpwd/")
	@ApiOperation(value = "회원 비밀번호 수정", notes = "회원정보 중 비밀번호를 수정한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> editPW(
			@RequestBody @ApiParam(value = "회원수정 정보 - 비밀번호", required = true) UserEditPWPutReq editInfo) {
		String userId = editInfo.getUserid();
		String password = editInfo.getPassword();
		String passwordnew1 = editInfo.getNewpassword1();
		String passwordnew2 = editInfo.getNewspassword2();

		User user = userService.getUserByUserId(userId);

		// 해당 아이디의 패스워드가 맞는지 확인
		if(passwordEncoder.matches(password, user.getPassword())) {
			// 맞다면, 변경할 두 패스워드가 일치하는지 확인
			if(passwordnew1.equals(passwordnew2)){
				//두 패스워드가 일치하다면, 비밀번호 변경하기
				userService.editUserPW(editInfo);
				return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
			}
			else {
				return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Invalid New Password"));
			}
		}
		// 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
		return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Invalid Password"));
	}

	@PostMapping("deleteprofile/")
	@ApiOperation(value = "회원 탈퇴", notes = "패스워드 입력을 통해 회원탈퇴를 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> delete(
			@RequestBody @ApiParam(value = "회원탈퇴 정보", required = true) UserRemoveDeleteReq removeInfo, HttpServletRequest req) {
		String userId = removeInfo.getUserid();
		String password1 = removeInfo.getPassword1();
		String password2 = removeInfo.getPassword2();
		User user = userService.getUserByUserId(userId);
		// 두 비밀번호가 일치하는지 확인
		if(password1.equals(password2)) {
			// 해당 ID의 비밀번호와 방금 입력한 비밀번호가 일치하는지 확인
			if (passwordEncoder.matches(password1, user.getPassword())) {
				// 패스워드가 일치한다면 회원탈퇴를 진행한다.
				userService.removeUser(userId);
				// 세션 초기화 진행
				HttpSession session = req.getSession();
				session.invalidate();
				return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SuccessRemove"));
			}
			// 패스워드가 실제 유저 패스워드가 아니라면 진행하지 않는다.
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "password is not correct"));
		}
		// 두 패스워드가 서로 일치하지 않는다면 진행하지 않는다.
		return ResponseEntity.status(401).body(BaseResponseBody.of(401, "passwords are not same"));
	}
}

