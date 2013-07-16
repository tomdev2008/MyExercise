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
			for(int i=0;i<tag.length ;i++){
				if(Tag.filter("name", tag[i]).first()== null){
					if(i==0){
						 t  = new Tag(tag[i],null);
						 t.save();
					}else{
						Tag context = Tag.filter("name", tag[i-1]).first();
						t = new Tag(tag[i],context);
						t.save();
					}
					
				}
			}
		}
		return t;
	}
	
	
}
