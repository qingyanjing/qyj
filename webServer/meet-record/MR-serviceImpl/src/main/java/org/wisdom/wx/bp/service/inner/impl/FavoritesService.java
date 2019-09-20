package org.wisdom.wx.bp.service.inner.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wisdom.wx.bp.dao.crud.FavoritesMapper;
import org.wisdom.wx.bp.model.crud.Favorites;
import org.wisdom.wx.bp.model.crud.FavoritesExample;
import org.wisdom.wx.bp.service.inner.IFavoritesService;

import java.util.List;

@Service
public class FavoritesService implements IFavoritesService {

    @Autowired
    FavoritesMapper favoritesMapper;
    @Override
    public Boolean insertFavorites(Favorites favorites) {
        return favoritesMapper.insert(favorites)>0;
    }

    @Override
    public boolean cancelFavorites(String favoritesId) {
        return favoritesMapper.deleteByPrimaryKey(favoritesId)>0;
    }

    @Override
    public List<Favorites> selectFavorites(FavoritesExample favorites) {
        return favoritesMapper.selectByExample(favorites);
    }
}
