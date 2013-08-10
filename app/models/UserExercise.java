package models;

import java.util.Date;
import java.util.List;

import models.enums.UserExerciseStatus;

import com.google.code.morphia.annotations.Reference;

import play.modules.morphia.Model;

public class UserExercise extends Model {

	@Reference
	public User user;
	@Reference
	public Subject subject;
	@Reference
	public List<Option>  userAnswer;
	public UserExerciseStatus status;
	public int completeCount;
	public int correctCount;
	public int mistakeCount;
	public int weight;

	public Date createAt = new Date();

    public Date updateAt = new Date();
	
}
