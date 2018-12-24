package org.yang.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yang.pojo.Admin;
import org.yang.pojo.OperationLogs;
import org.yang.pojo.Users;
import org.yang.service.OperationLogsService;
import org.yang.service.UsersService;
import org.yang.utils.Utils;

@Controller
public class UsersController {
	@Resource
	private UsersService usersServiceImpl;
	@Resource
	private OperationLogsService operationServiceImpl;
	private Logger logger=Logger.getLogger(this.getClass().getName());
	@RequestMapping("{path}page")
	public String jumpPage(@PathVariable String path) {
		return "/WEB-INF/pages/" + path + ".jsp";
	}
	
	@RequestMapping("{path}front")
	public String frontPage(@PathVariable String path) {
		return "/WEB-INF/frontpages/" + path + ".jsp";
	}
	@RequestMapping("register")
	@ResponseBody
	public String registerUsers(Users user) {
		return  usersServiceImpl.registerUser(user);
	}

	@RequestMapping("getCaptcha")
	@ResponseBody
	public String createCaptcha(HttpServletRequest request) {
		StringBuffer captcha = usersServiceImpl.createCaptcha();
		System.out.println(captcha);
		request.getSession().setAttribute("captcha", captcha);
		return "已发送";
	}

	@RequestMapping("verifyCaptcha")
	@ResponseBody
	public String verifyCaptcha(String inputCaptcha, HttpServletRequest request) {
		StringBuffer captcha = (StringBuffer) request.getSession().getAttribute("captcha");
		if (captcha.toString().equals(inputCaptcha)) {
			return "right";
		} else {
			return "验证码错误";
		}
	}

	@RequestMapping("sendEmail")
	@ResponseBody
	public String sendEmail(String emailAddress, HttpServletRequest request) {
		StringBuffer emailCaptcha = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < 4; i++) {
			emailCaptcha.append(rand.nextInt(10));
		}
		String message = "验证码为：";
		String status = usersServiceImpl.sendEmail(emailAddress, request, emailCaptcha.toString(), message);
		return status;
	}

	@RequestMapping("verifyEmailCaptcha")
	@ResponseBody
	public String verifyEmailCaptcha(String inputEmailCaptcha, HttpServletRequest request) {
		String emailCaptcha = (String) request.getSession().getAttribute("emailCaptcha");
		if (inputEmailCaptcha.equals(emailCaptcha)) {
			return "验证成功";
		} else {
			return "验证失败";
		}
	}

	@RequestMapping("verifyPhone")
	@ResponseBody
	public String verifyPhone(String phone) {
		Users user = usersServiceImpl.verifyPhone(phone);
		if (user != null) {
			return "手机号已被注册";
		} else {
			return "可被注册";
		}
	}

	@RequestMapping("verifyUsername")
	@ResponseBody
	public String verifyUsername(String username) {
		Users user = usersServiceImpl.verifyUsername(username);
		if (user != null) {
			return "用户名已被注册";
		} else {
			return "可被注册";
		}
	}
	@RequestMapping("getImgCaptcha")
	@ResponseBody
	public void getImgCaptcha(HttpServletResponse response, HttpServletRequest request) {
		BufferedImage bufferedImage = new BufferedImage(100, 40, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2d = bufferedImage.createGraphics();
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRect(0, 0, 100, 40);
		graphics2d.setFont(new Font("微软雅黑", Font.BOLD | Font.ITALIC, 25));
		Random rand = new Random();
		StringBuffer codeResult = new StringBuffer();
		for (int i = 1; i <= 4; i++) {

			String code = String.valueOf(rand.nextInt(9));
			graphics2d.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
			codeResult.append(code);
			graphics2d.drawString(code, i * 20, 30);
		}
		for (int i = 0; i < 20; i++) {
			graphics2d.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
			graphics2d.drawLine(rand.nextInt(100), rand.nextInt(40), rand.nextInt(100) + 10, rand.nextInt(40) + 10);
		}
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			ImageIO.write(bufferedImage, "PNG", outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("imageCaptcha", codeResult);
	}
	@RequestMapping("manageMenu.do")
	public String manageMenu(HttpServletRequest request) {
		Object admin = request.getSession().getAttribute("admin");
		if(admin==null) {
			return "adminloginpage";
		}else {
			return "managemenupage";
		}
	}
	@RequestMapping("verifyUser")
	@ResponseBody
	public String verifyUser(Users user, String captcha, HttpServletRequest request) {
		String password = user.getPassword();
		String encodePassword = "";
		try {
			encodePassword = Utils.MD5Encode(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setPassword(encodePassword);
		Users users = usersServiceImpl.verifyUser(user);
		StringBuffer captchaCode = (StringBuffer) request.getSession().getAttribute("imageCaptcha");
		if (!(captcha.equals(captchaCode.toString()))) {
			return "验证码错误";
		} else {
			if (users != null) {
				request.getSession().setAttribute("user",users );
				return "登陆成功";
			} else {
				Users userByUsername = usersServiceImpl.verifyUsername(user.getUsername());
				if(userByUsername==null) {
					return "用户名或密码错误";
				}else {
					if(!(userByUsername.getPassword().equals(password))) {
						return "密码错误";
					}else {
						return "您因违规已被封号";
					}
				}
			}
		}
	}

	@RequestMapping("getBackPassword")
	@ResponseBody
	public String getBackPassword(String username, HttpServletRequest request) {
		String email = usersServiceImpl.getUserEmail(username);
		request.getSession().setAttribute("username", username);
		String url = "<a href='http://localhost/changePassword'>修改密码</a>";
		String message = "点下面链接修改密码";
		String status = Utils.sendEmail(email, request, url, message);
		return status;
	}

	@RequestMapping("userManage.do")
	public String userManage(Model model, String currentPage, String condition, HttpServletResponse response,
			HttpServletRequest request) {
		int limit = 20;
		List<Users> allUserList = usersServiceImpl.selectUserByPageAndCondition(currentPage, limit, condition, request);
		int totalPage = usersServiceImpl.getTotalPage(limit, condition);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("allUserList", allUserList);
		return "/WEB-INF/pages/usermanage.jsp";
	}

	@RequestMapping("changePassword")
	public String changePassword() {
		return "/WEB-INF/pages/changepassword.jsp";
	}

	@RequestMapping("updatePassword")
	@ResponseBody
	public String updatePassword(String newPassword, HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("username");
		String encodePassword = "";
		try {
			encodePassword = Utils.MD5Encode(newPassword);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String currentTime=Utils.getCurrentTime();
		int updateStatus = usersServiceImpl.updatePassword(encodePassword, username,currentTime);
		logger.info("用户名为"+username+"在"+currentTime+"修改了密码");
		if (updateStatus == 1) {
			return "修改成功";
		} else {
			return "修改失败";
		}
	}
	@RequestMapping("updateStatus.do")
	@ResponseBody
	public String updateStatus(int id,String status,HttpServletRequest request) {
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin==null) {
			return "请登陆后在操作";
		}
		else {
			String currentTime=Utils.getCurrentTime();
			String updateStatus = usersServiceImpl.updateStatus(id, status,currentTime);
			OperationLogs operationLogs=new OperationLogs();
			operationLogs.setOperation_content("修改了用户id为"+id+"的状态为"+status);
			operationLogs.setOperation_person(admin.getUsername());
			operationLogs.setOperation_status(updateStatus);
			operationLogs.setOperation_time(currentTime);
			operationServiceImpl.addOperationLogs(operationLogs);
			logger.info("管理员为"+admin.getUsername()+"在"+currentTime+"修改了用户id为"+id+"的状态为"+status);
			return updateStatus;
		}
	}
	@RequestMapping("verifyAdmin")
	@ResponseBody
	public String verifyAdmin(Admin admin, String captcha, HttpServletRequest request,HttpServletResponse response) {
		Admin admins = usersServiceImpl.verifyAdmin(admin);
		StringBuffer captchaCode = (StringBuffer) request.getSession().getAttribute("imageCaptcha");
		if (!(captcha.equals(captchaCode.toString()))) {
			return "验证码错误";
		} else {
			if (admins!=null) {
				request.getSession().setAttribute("admin", admins);
				String currentTime=Utils.getCurrentTime();
				logger.info("管理员为"+admins.getUsername()+"在"+currentTime+"登陆了系统");
				return "登陆成功";
			} else {
				return "用户名或密码错误";
			}
		}
	}
}
