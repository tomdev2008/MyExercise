package models;

import java.util.Date;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

import play.modules.morphia.Model;

@Entity
public class ScheduleInfo extends Model {

	@Reference
	public Tag tag;
	
	public int score;
	
	public Date createAt;
	
	public Date UpdateAt;
}
