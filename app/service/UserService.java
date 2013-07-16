package service;

import models.User;
import utils.UserRoleType;
import utils.UserStatus;

public class UserService {

	
	public static User getUser(String userName){
		
		if(User.find("userName", userName).count() >0){
			return User.find("userName", userName).first();
		}
		User user = new User();
		user.userName = userName;
		user.password = "12345";
		user.status = UserStatus.NEWBIE;
		user.roleType = UserRoleType.ADMIN;
		user.save();
		return user;
		
	}
}
