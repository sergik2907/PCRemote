package com.pccontroll.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.pccontroll.model.Button;
import java.util.List;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/08
 */

@Dao
public interface ButtonDao {

	@Query("SELECT * FROM button")
	List<Button> getAll();

	@Insert
	void insert(Button button);

	@Delete
	void delete(Button button);
}
