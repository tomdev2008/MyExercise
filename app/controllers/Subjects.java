package controllers;

import java.util.List;

import models.Subject;
import play.mvc.Controller;

public class Subjects extends Controller {

	public static void index(){
		List<Subject> list = Subject.findAll();
		render(list);
	}
}
