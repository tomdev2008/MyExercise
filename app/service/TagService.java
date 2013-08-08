package service;

import org.apache.commons.lang.StringUtils;

import models.Tag;
import play.modules.morphia.Model.MorphiaQuery;

public class TagService {

	/**
	 * 
	 * 根据标签名称获取标签，如果没有此标签则创建一个并返回
	 * @param tagName2
	 * @return
	 */
	public static Tag getTag(String name) {
		if(StringUtils.isBlank(name)){
			return null;
		}
		String[] tag = name.split(":");
		MorphiaQuery query = Tag.filter("name", tag[tag.length-1]);
		
		Tag t = null;
		if(query.count()==0){
			t = addTag(name);
		}else{
			t = query.first();
		}
		return t;
	}
	
	public static Tag addTag(String tagName){
		String[] tag = tagName.split(":");
		Tag t = null;
		for(int i=0;i<tag.length ;i++){
			if(Tag.filter("name", tag[i]).first()== null){
				if(i==0){
					 t  = new Tag(tag[i],null);
					 t.index=0;
					 t.save();
				}else{
					Tag context = Tag.filter("name", tag[i-1]).first();
					Long index = Tag.filter("context", context).countAll();
					t = new Tag(tag[i],context);
					t.index = Integer.parseInt(index.toString())+1;
					t.save();
				}
				
			}
		}
		return t;
	}
	
	public static Tag addTag(String tagName,int index){
		String[] tag = tagName.split(":");
		Tag t = null;
		for(int i=0;i<tag.length ;i++){
			if(Tag.filter("name", tag[i]).first()== null){
				if(i==0){
					 t  = new Tag(tag[i],null);
					 t.index=index;
					 t.save();
				}else{
					Tag context = Tag.filter("name", tag[i-1]).first();
					t = new Tag(tag[i],context);
					t.index = index;
					t.save();
				}
				
			}
		}
		return t;
	}
	
	public static boolean isExist(String tag){
		if(StringUtils.isBlank(tag)){
			return false;
		}
		String[] tags = tag.split(":");
		MorphiaQuery query = Tag.filter("name", tags[tags.length-1]);
		if(query.count() >0){
			return true;
		}
		return false;
	}
	
	public static String getFullTagName(Tag tag){
		String name=tag.name;
		while(tag.context!=null){
			tag = tag.context;
			name = tag.name+":"+name;
		}
		return name;
	}
	
	
}
