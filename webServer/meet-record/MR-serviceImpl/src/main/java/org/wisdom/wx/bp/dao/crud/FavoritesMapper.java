package org.wisdom.wx.bp.dao.crud;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.wisdom.wx.bp.model.crud.Favorites;
import org.wisdom.wx.bp.model.crud.FavoritesExample;
@Repository
public interface FavoritesMapper {
    int deleteByExample(FavoritesExample example);

    int deleteByPrimaryKey(String favoritesId);

    int insert(Favorites record);

    int insertSelective(Favorites record);

    List<Favorites> selectByExample(FavoritesExample example);

    Favorites selectByPrimaryKey(String favoritesId);

    int updateByPrimaryKeySelective(Favorites record);

    int updateByPrimaryKey(Favorites record);
}