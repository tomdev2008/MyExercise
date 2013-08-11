package models;

import java.util.ArrayList;
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
	public List<Tag> grades = new ArrayList<Tag>();
	
	@Reference
	public List<Tag> courses = new ArrayList<Tag>();
	
	@Reference
	public List<ScheduleInfo> schedule = new ArrayList<ScheduleInfo>();
	
	public Date createAt;
	
	public Date UpdateAt;
	
}
