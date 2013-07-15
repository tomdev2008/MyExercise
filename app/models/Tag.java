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
	
	/**
	 * 
	 * 根据标签名称获取标签，如果没有此标签则创建一个并返回
	 * @param tagName2
	 * @return
	 */
	public static Tag getTag(String name,Tag context) {
		MorphiaQuery query = Tag.filter("name", name);
		if(context!=null){
			query = query.filter("context", context);
		}
		Tag tag = query.first();
		if(tag==null){
			tag=new Tag(name,context);
			tag.save();
		}
		return tag;
	}
}
