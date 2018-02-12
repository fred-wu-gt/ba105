package com.act_like.model;

import java.util.List;

public interface Act_likeDAO_interface {
	 public void insert(Act_likeVO act_likeVO);
     public Act_likeVO findByPrimaryKey(String act_no, String mem_no);
     //查看已按讚活動
     public List<Act_likeVO> findByMemNo(String mem_no);
     //查看單一活動按讚數
     public int getOneActCountLike(String act_no);
     public List<Act_likeVO> getAll();
     public void delete(String act_no, String mem_no);
}
