package models;

import java.util.Date;

import com.google.code.morphia.annotations.Entity;

import play.modules.morphia.Model;
import utils.UserStage;
import utils.UserStatus;

@Entity
public class User extends Model {
	public UserStatus status;
	public UserStage stage;
	public String userName;		
	public String password;	
	public int roleType;
	public int gender;
	public Date birth;
	public String email;
	public String headUrl;
	public String provie;
	public String city;
	public String mobile;
	
}
