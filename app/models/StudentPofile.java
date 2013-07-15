package models;

import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity
public class StudentPofile extends Model{
	
	@Reference
	public User user;
	
	@Reference
	public Tag tag;
	
	public int score;
	
}
