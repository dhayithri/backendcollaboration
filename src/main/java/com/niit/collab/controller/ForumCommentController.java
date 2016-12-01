package com.niit.collab.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collab.dao.ForumCommentDAO;
import com.niit.collab.model.ForumComment;

@RestController
public class ForumCommentController {

	@Autowired
	ForumCommentDAO forumDAO;
	@PostMapping("/createforum")
	public ResponseEntity<ForumComment> createforum(@RequestBody ForumComment forum){
		forum.setDoc(new Date());
		forumDAO.saveOrUpdate(forum);
		return new ResponseEntity<ForumComment>(forum ,HttpStatus.OK);
	}
	
	@GetMapping(value="/forum")
	public ResponseEntity<List<ForumComment>> listforum(){
		System.out.println("list of blog");
		List<ForumComment> forum =forumDAO.list();
		return new ResponseEntity<List<ForumComment>>(forum,HttpStatus.OK);
	}
	@DeleteMapping(value="/deleteforum/{forumid}")
	public ResponseEntity<ForumComment> deleteforum(ForumComment forum,@PathVariable("forumid") int forumid){
		ForumComment forum1=forumDAO.getforum(forumid);
		forumDAO.delete(forum1);
		return new ResponseEntity<ForumComment>(forum,HttpStatus.OK);
	}
}