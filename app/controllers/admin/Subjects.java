package controllers.admin;

import java.util.List;

import models.Option;
import models.Subject;
import play.mvc.Controller;

public class Subjects extends  Controller{

	public static void index() {
		Subject.findAll();
		render();
    }
	
	public static void list() {
		Subject.findAll();
		render();
    }
	
	public static void add() {
        render();
    }
	
	public static void create(String title,String solution) {
		
		String[] options = params.getAll("option");
		System.out.println(options.toString());
		Subject sb = new Subject();
		sb.title =title;
		int cnt =1;
		for(String option : options){
			System.out.println(option);
			Option o = new Option();
			
			o.content=option;
			o.save();
			sb.options.add(o);
			if(cnt ==1){
				sb.answer = o;
			}
			cnt++;
		}
		sb.save();
        redirect("/admin/subject");
    }
}
