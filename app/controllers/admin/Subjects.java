package controllers.admin;

import models.Subject;
import play.mvc.Controller;

public class Subjects extends  Controller{


	public static void index() {
        render();
    }
	
	public static void list() {
        render();
    }
	
	public static void add() {
        render();
    }
	
	public static void create() {
		
		
		Subject sb = new Subject();
		
        redirect("/admin/subject/index");
    }
}
