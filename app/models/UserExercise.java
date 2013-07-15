package models;

import java.util.Date;

import com.google.code.morphia.annotations.Reference;

import play.modules.morphia.Model;
import utils.UserExerciseStatus;

public class UserExercise extends Model {

	@Reference
	public User user;
	@Reference
	public Subject subject;
    
	public UserExerciseStatus status;
	public int completeCount;
	public int correctCount;
	public int mistakeCount;
	public int weight;

	public Date createAt = new Date();

    public Date updateAt = new Date();
	
}
