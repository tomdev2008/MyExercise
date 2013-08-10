package models;

import java.util.Date;
import java.util.List;

import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity
public class StudentPofile extends Model{
	
	@Reference
	public User user;
	
	@Reference
	public List<Tag> grades;
	
	@Reference
	public List<Tag> courses;
	
	@Reference
	public List<ScheduleInfo> schedule;
	
	public Date createAt;
	
	public Date UpdateAt;
	
}
