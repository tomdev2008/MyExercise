package models;

import java.util.*;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;

import play.modules.morphia.Model;
import utils.SubjctStatus;

/**
 * Created with IntelliJ IDEA.
 * User: wji
 * Date: 13-7-11
 * Time: 下午8:25
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Subject extends Model{

    public SubjctStatus status;

    public int type =0;

    @Reference
    public Set<Tag> tags;

    public String title;
 
    @Reference
    public List<Option> options = new ArrayList();
   
    @Reference
    public Option answer;

    public String solution;

    public int frequency = 14;

    public double accuracyAll =0;

    public double accuracyOnce =0;

    public double accuracyTwice =0;

    @Reference
    public User owner;
    
    public Date createAt = new Date();

    public Date updateAt = new Date();
   
}


