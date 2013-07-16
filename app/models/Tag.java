package models;

import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity
public class Tag extends Model{

	@Reference
	public Tag context;
	
	public String name;
	
	
	
	
	public Tag(String name,Tag tagContext){
		this.name=name;
		this.context=tagContext;
	}
	
}
