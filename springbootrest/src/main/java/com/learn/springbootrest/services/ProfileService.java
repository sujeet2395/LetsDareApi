package com.learn.springbootrest.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.springbootrest.model.Output;
import com.learn.springbootrest.model.Profile;
import com.learn.springbootrest.repository.ProfileRepository;

@Service 
public class ProfileService {
	@Autowired
	private ProfileRepository pres;
	
	public Output doRegister(Profile p)
	{
		int res=0;
		Profile registeredP=null;
		try {
			registeredP = pres.save(p);
			res=1;
		}catch(IllegalArgumentException iargex)
		{
			System.out.println(iargex.getMessage());
		}
		Output out=null;
		if(res==1)
		{
			out=new Output(1,"registration successfull", registeredP.getId());
		}else {
			out=new Output(0,"registration unsuccessfull", -1);
		}
		return out;
	}
	public Optional<Profile> getProfile(int id)
	{
		return pres.findById(id);
	}
	public Output update(Profile p)
	{
		int res=0;
		Profile existedP=null;
		try {
			existedP=pres.getOne(p.getId());
			pres.save(p);
			res=1;
		}catch(EntityNotFoundException | IllegalArgumentException notFoundEx)
		{
			System.out.println(notFoundEx.getMessage());
		}
		Output out=null;
		if(res==1)
		{
			out=new Output(1, "updated successfull", existedP.getId());
		}else {
			out=new Output(0, "not updated unsuccessfull", -1);
		}
		return out;
	}
	public Output deletePr(int id)
	{
		int res=0;
		Profile existedP=null;
		try {
			existedP=pres.getOne(id);
			pres.deleteById(id);
			res=1;
		}catch(EntityNotFoundException | IllegalArgumentException notFoundEx)
		{
			System.out.println(notFoundEx.getMessage());
		}
		Output out=null;
		if(res>0)
		{
			out=new Output(1,"deleted successfull", existedP.getId());
		}else {
			out=new Output(0,"deletion unsuccessfull", -1);
		}
		return out;
	}
	public List<Profile> getAllProfiles()
	{
		return pres.findAll();
	}
	public Output getByNameAndPassword(Profile p)
	{
		Profile rsp=null;
		try {
			//Query q=session.createQuery("from profiles where name='"+p.getName()+"' and password='"+p.getPassword()+"'");
			rsp=pres.findByNameAndPassword(p.getName(), p.getPassword());
		}catch(IllegalArgumentException ex)
		{
			System.out.println("Not login: "+ex.getMessage());
			
		}
		Output out=null;
		if(rsp!=null)
		{
			out=new Output(1, "login successfull", rsp.getId());
			return out; 
		}else {
			out=new Output(0, "login unsuccessfull", -1);
		}
		return out;
	}
}
