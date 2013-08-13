package models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.enums.UserExerciseStatus;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

import play.modules.morphia.Model;
import play.modules.morphia.validation.Unique;

@Entity
public class UserExercise extends Model {

	@Reference
	public User user;
	
	@Reference
	public Set<Tag> tags = new HashSet();
	   
	@Reference
	@Unique
	public Subject subject;
	@Reference
	public List<Option>  userAnswer;
	public UserExerciseStatus status;
	
	public int currentScore;
	public int displayCount;
	public int completeCount;
	public int correctCount;
	public int mistakeCount;
	public int weight;

	public Date nextDate = new Date();
	public Date createAt = new Date();

    public Date updateAt = new Date();
	
}
