package com.fruit.model;

import java.util.List;

public interface FruitDAO_Interface {
	void add(FruitVO fruitVO);
	void update(FruitVO fruitVO);
	void delete(String fru_no);
	FruitVO findByFru_no(String fru_no);
	FruitVO findByFru_name(String fru_name);
	List<FruitVO> getAll();
}
