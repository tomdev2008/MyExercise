package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void doExercise(String courseName) {
    	render(courseName);
    }
    
    public static void result() {
    	render();
    }
}